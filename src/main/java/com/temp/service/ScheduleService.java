package com.temp.service;

import com.temp.domain.RoomStatus;
import com.temp.schedule.ServiceUnitManager;
import com.temp.SystemConfigure;
import com.temp.domain.WindSupplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*  处理调度控制器的请求
*/
@Service
public class ScheduleService {
    // 服务单元管理器，实际处理请求的组件
    @Autowired
    private ServiceUnitManager serviceUnitManager  = null;

    // 时间片调度，定时执行一次
    @Scheduled(fixedRate = SystemConfigure.timeSlot / 3 * 1000)
    public void timeSlotSchedule(){
        serviceUnitManager.executePerTimeSlot();
    }

    // 处理调温调风请求
    public void addRequest(int roomId, WindSupplyRequest wsr){
        serviceUnitManager.handNewReq(roomId,wsr);
    }

    // 处理关机请求
    public void takeOff(int roomId){
        serviceUnitManager.takeOff(roomId);
    }

    // 处理实时状态查询请求
    public RoomStatus getRoomStatus(int roomId){
        return  serviceUnitManager.getRoomStatus(roomId);
    }

    // 处理返回所有房间状态的请求
    public List<RoomStatus> getAllRoomStatus() {return serviceUnitManager.getAllRoomStatus(); };
}
