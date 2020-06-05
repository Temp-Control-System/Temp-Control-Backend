package com.temp.schedule;
import com.temp.SystemConfigure;
import com.temp.domain.RoomStatus;
import com.temp.enums.*;

import com.temp.domain.RequestRecord;
import com.temp.domain.WindSupplyRequest;
import com.temp.enums.Mode;
import com.temp.enums.RequestType;
import com.temp.enums.SUStatus;
import com.temp.repository.RequestRecordRepository;
import com.temp.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

interface TemperatureUpdater {
    public float getChangeValue(ServiceUnit su);
}

interface CostCalculator{
    public int getTimeSlotServiceCost(ServiceUnit su);
}

@Component
public class ServiceUnitManager{
    // 房间总数
    static int totalRoomNum = SystemConfigure.totalRoomNum;
    // 数据库仓库，管理ServiceRecord实体
    @Autowired
    private ServiceRecordRepository serviceRecordRepository = null;
    @Autowired
    private RequestRecordRepository requestRecordRepository =null;
    // 所有服务单元，每个房间对应一个
    private ServiceUnit[] serviceUnits = new ServiceUnit[totalRoomNum];
    // 运行队列
    private TreeSet<ServiceUnit> runningSUs = new TreeSet<>((s1, s2)-> {
        if(s1.getWind().ordinal()<s2.getWind().ordinal())
            return -1;
        else if(s1.getWind().ordinal()>s2.getWind().ordinal())
            return 1;
        int k =  s2.getLastTime() - s1.getLastTime();
        if(k!=0) return k;
        return s1.getRoomStatus().getRoomId() - s2.getRoomStatus().getRoomId();}
    );
    // 等待队列
    private TreeSet<ServiceUnit> waitingSUs = new TreeSet<>((s1,s2)-> {
        if(s1.getWind().ordinal()<s2.getWind().ordinal())
            return 1;
        else if(s1.getWind().ordinal()>s2.getWind().ordinal())
            return -1;
        int k=  (int)(s1.getWaitingTime() - s2.getWaitingTime());
        if(k!=0) return k;
        return s1.getRoomStatus().getRoomId() - s2.getRoomStatus().getRoomId();}
    );
    // 消费计算器，通过对它的修改可以修改计费规则
    private CostCalculator costCalculator = new CostCalculator() {
        @Override
        public int getTimeSlotServiceCost(ServiceUnit su) {
            switch (su.getWind()){
                case LOW:
                    return SystemConfigure.LowWindCostPerSlot;
                case MIDDLE:
                    return SystemConfigure.MiddleWindCostPerSlot;
                case HIGH:
                    return SystemConfigure.HighWindCostPerSlot;
            }
            return 0;
        }
    };
    // 温度变化计算器
    private TemperatureUpdater temperatureUpdater = new TemperatureUpdater() {
        @Override
        public float getChangeValue(ServiceUnit su) {
            float delta = 0;
            switch (su.getStatus()){
                case WAITING:
                case OFF:
                case STANDBY:
                    if(SystemConfigure.envTemperature > su.getTemperature())
                        delta = 1;
                    else
                        delta = -1;
                    return SystemConfigure.TakeoffTemperatureChangePerSlot * delta;
                case RUNNING:
                    if(su.getMode()== Mode.HEATING)
                        delta = 1;
                    else
                        delta = -1;
                    switch (su.getWind()){
                        case LOW:
                            return  delta * SystemConfigure.LowWindTemperatureChangePerSlot;
                        case MIDDLE:
                            return  delta * SystemConfigure.MiddleWindTemperatureChangePerSlot;
                        case HIGH:
                            return  delta * SystemConfigure.HighWindTemperatureChangePerSlot;
                    }
            }
            return 0;
        }
    };

    public ServiceUnitManager(){
        for(int i=0 ;i<serviceUnits.length;i++){
            serviceUnits[i] = new ServiceUnit();
        }
    }

    private void startRunning(ServiceUnit su){
        su.resetService();
        su.setStatus(SUStatus.RUNNING);
        runningSUs.add(su);
    }

    private void stopRunning(ServiceUnit su){
        runningSUs.remove(su);
        serviceRecordRepository.save(su.stopService());
    }

    private void startWaiting(ServiceUnit su){
        su.setStatus(SUStatus.WAITING);
        su.setWaitingTime(SystemConfigure.InitWaitingTime);
        waitingSUs.add(su);
    }

    private void stopWaiting(ServiceUnit su){
        waitingSUs.remove(su);
    }

    private void startStandby(ServiceUnit su){
        su.setStatus(SUStatus.STANDBY);
    }

    private void updateSystemStatus(int interval){
        for(ServiceUnit su : serviceUnits){
            su.setTemperature(su.getTemperature()+temperatureUpdater.getChangeValue(su));
            switch (su.getStatus()){
                case STANDBY:
                    if(su.isWake()){
                        prioritySchedule(su);
                    }
                    break;
                case WAITING:
                    su.setWaitingTime(su.getWaitingTime() - interval);
                    break;
                case RUNNING:
                    su.setLastTime(su.getLastTime() + interval);
                    su.setTotalCost(su.getTotalCost() + costCalculator.getTimeSlotServiceCost(su));
                    su.setCurServiceCost(su.getCurServiceCost() + costCalculator.getTimeSlotServiceCost(su));
                    if(su.isAchievedTarget()){
                        // 停止取得目标的服务
                        su.setAchievedTargetNum(su.getAchievedTargetNum() + 1);
                        stopRunning(su);
                        startStandby(su);
                    }
                    break;
            }
        }
    }

    private void timeSlotSchedule(){
        // 服务数未满
        for(int i=runningSUs.size();i<SystemConfigure.serviceCapacity&&!waitingSUs.isEmpty();i++){
            ServiceUnit su = waitingSUs.first();
            stopWaiting(su);
            startRunning(su);
        }
        // 时间片轮转
        while(!waitingSUs.isEmpty()&&waitingSUs.first().getWaitingTime()<=0){
            ServiceUnit wsu = waitingSUs.first();
            ServiceUnit rsu = runningSUs.first();
            // 此处必然成立，否则调度算法有误
            assert rsu.getWind().ordinal()<=wsu.getWind().ordinal();
            if(rsu.getWind().ordinal()>wsu.getWind().ordinal())
                break;
            // 替换服务时间最长的相等优先级服务,
            stopRunning(rsu);
            startWaiting(rsu);
            stopWaiting(wsu);
            startRunning(wsu);
        }
    }

    private void prioritySchedule(ServiceUnit su){
        // 服务数未满
        if(runningSUs.size()<SystemConfigure.serviceCapacity){
            startRunning(su);
            return;
        }
        // 对服务单元进行优先级调度
        ServiceUnit firstSu = runningSUs.first();
        if(firstSu.getWind().ordinal() < su.getWind().ordinal()){
            // 存在低优先级服务，抢占它
            stopRunning(firstSu);
            startWaiting(firstSu);
            startRunning(su);
        }else{
            startWaiting(su);
        }
    }

    public synchronized void handNewReq(int roomId, WindSupplyRequest wsr){
        assert roomId < serviceUnits.length;
        // 存数据库
        RequestRecord rr = new RequestRecord();
        rr.setRoomId(roomId);
        rr.setMode(wsr.getMode());
        rr.setRequest_time(new Date());
        rr.setRequestType(RequestType.USE_AIR);
        rr.setTargetTemperature(wsr.getTargetTemperature());
        rr.setWind(wsr.getWind());
        requestRecordRepository.save(rr);

        ServiceUnit su = serviceUnits[roomId];
        switch (su.getStatus()){
            case RUNNING:
                stopRunning(su);
                break;
            case WAITING:
                stopWaiting(su);
                break;
        }
        su.setAchievedTargetNum(0);
        su.setMode(wsr.getMode());
        su.setWind(wsr.getWind());
        su.setTargetTemperature(wsr.getTargetTemperature());
        prioritySchedule(su);
    }

    public synchronized void takeOff(int roomId){
        assert roomId < serviceUnits.length;
        RequestRecord rr = new RequestRecord();
        rr.setRoomId(roomId);
        rr.setRequestType(RequestType.TAKE_OFF_AII);
        rr.setRequest_time(new Date());
        requestRecordRepository.save(rr);

        ServiceUnit su = serviceUnits[roomId];
        switch (su.getStatus()){
            case RUNNING:
                stopRunning(su);
                break;
            case WAITING:
                stopWaiting(su);
                break;
        }
        su.setStatus(SUStatus.OFF);
    }

    public synchronized void executePerTimeSlot(){
        updateSystemStatus(SystemConfigure.timeSlot);
        timeSlotSchedule();
    }

    public RoomStatus getRoomStatus(int roomId){
        return serviceUnits[roomId].getRoomStatus();
    }

    public List<RoomStatus> getAllRoomStatus(){
        LinkedList<RoomStatus> roomStatuses = new LinkedList<>();
        for(ServiceUnit su:serviceUnits){
            roomStatuses.add(su.getRoomStatus());
        }
        return roomStatuses;
    }
}