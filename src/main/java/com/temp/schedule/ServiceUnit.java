package com.temp.schedule;
import com.temp.SystemConfigure;
import com.temp.domain.RoomStatus;
import com.temp.domain.ServiceRecord;
import com.temp.enums.*;
import com.temp.enums.Mode;
import com.temp.enums.SUStatus;
import com.temp.enums.Wind;

import java.util.Date;

public class ServiceUnit{
    // ID,与房间ID对应
    private static int curId = 0;
    // 唤醒边缘
    final private static float margin = SystemConfigure.margin;
    // 目标温度
    private float startTemperature;
    // 本次服务累计消费
    private int curServiceCost;
    // 本次服务持续时间
    private int lastTime;
    // 本次服务开始时刻
    private Date serviceStartTime;
    // 等待时间（在等待态才有意义）
    private long waitingTime;
    // 房间状态
    private RoomStatus roomStatus;
    // 本次服务是否取得用户目标
    private int achievedTargetNum;
    public ServiceUnit (){
        roomStatus = new RoomStatus();
        roomStatus.setRoomId(curId++);
        roomStatus.setTemperature(SystemConfigure.envTemperature);
        roomStatus.setStatus(SUStatus.OFF);
        roomStatus.setMode(Mode.REFRIGERATION);
    }

    public int getCurServiceCost() {
        return curServiceCost;
    }

    public void setCurServiceCost(int curServiceCost) {
        this.curServiceCost = curServiceCost;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public void resetService(){
        startTemperature = getTemperature();
        serviceStartTime = new Date();
        curServiceCost = 0;
        lastTime = 0;
    }

    public void setTargetTemperature(float targetTemperature) {
        roomStatus.setTargetTemperature(targetTemperature);
    }

    public float getTargetTemperature() {
        return roomStatus.getTargetTemperature();
    }

    public int getTotalCost() {
        return roomStatus.getTotalCost();
    }

    public void setTotalCost(int totalCost) {
        roomStatus.setTotalCost(totalCost);
    }
    public Mode getMode() {
        return roomStatus.getMode();
    }

    public void setMode(Mode mode) {
        roomStatus.setMode(mode);
    }

    public float getTemperature() {
        return roomStatus.getTemperature();
    }

    public void setTemperature(float temperature) {
        roomStatus.setTemperature(temperature);
    }

    public SUStatus getStatus() {
        return roomStatus.getStatus();
    }

    public void setStatus(SUStatus status) {
        roomStatus.setStatus(status);
    }

    public Wind getWind() {
        return roomStatus.getWind();
    }

    public void setWind(Wind wind) {
        roomStatus.setWind(wind);
    }

    public int getAchievedTargetNum() {
        return achievedTargetNum;
    }

    public void setAchievedTargetNum(int achievedTargetNum) {
        this.achievedTargetNum = achievedTargetNum;
    }

    public boolean isAchievedTarget(){
        if(getMode()==Mode.HEATING){
            return getTemperature() > getTargetTemperature();
        }else{
            return getTemperature() < getTargetTemperature();
        }
    }

    public boolean isWake(){
        if(getMode()==Mode.HEATING){
            return getTemperature() < getTargetTemperature() - margin;
        }else{
            return getTemperature() > getTargetTemperature() + margin;
        }
    }

    private ServiceRecord getServiceRecord(){
        ServiceRecord sr = new ServiceRecord(roomStatus.getRoomId(),
                getWind(),
                serviceStartTime,
                curServiceCost,
                lastTime,
                getMode(),
                getTemperature(),
                startTemperature,
                getTargetTemperature(),
                getAchievedTargetNum()==1);
        return sr;
    }

    public ServiceRecord stopService(){
        ServiceRecord sr = getServiceRecord();
        setStatus(SUStatus.WAITING);
        curServiceCost = 0;
        lastTime = 0;
        return sr;
    }

}
