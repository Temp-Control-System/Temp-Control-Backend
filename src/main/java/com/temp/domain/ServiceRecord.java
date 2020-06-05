package com.temp.domain;
import com.temp.enums.*;
import com.temp.enums.Mode;
import com.temp.enums.Wind;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ServiceRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 房间号
    private int roomId;
    @Enumerated(EnumType.ORDINAL)
    // 风速
    private Wind wind;
    // 服务开始时间
    @CreatedDate
    private Date serviceStartTime;
    // 本次服务累计消费
    private int totalCost;
    // 本次服务持续时间
    private int lastTime;
    @Enumerated(EnumType.ORDINAL)
    // 本次服务温控模式
    private Mode mode;
    // 本次服务结束时刻房间温度
    private float endTemperature;
    // 本次服务开始时刻房间温度
    private float startTemperature;
    private float targetTemperature;
    // 本次服务是否取得目标
    boolean isAchievedTarget;
    public ServiceRecord(){

    }
    public ServiceRecord(int roomId, Wind wind, Date serviceStartTime, int totalCost, int lastTime, Mode mode, float endTemperature, float startTemperature, float targetTemperature,boolean isAchievedTarget) {
        this.roomId = roomId;
        this.wind = wind;
        this.serviceStartTime = serviceStartTime;
        this.totalCost = totalCost;
        this.lastTime = lastTime;
        this.mode = mode;
        this.endTemperature = endTemperature;
        this.startTemperature = startTemperature;
        this.targetTemperature = targetTemperature;
        this.isAchievedTarget = isAchievedTarget;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public float getEndTemperature() {
        return endTemperature;
    }

    public void setEndTemperature(float endTemperature) {
        this.endTemperature = endTemperature;
    }

    public float getStartTemperature() {
        return startTemperature;
    }

    public void setStartTemperature(float startTemperature) {
        this.startTemperature = startTemperature;
    }

    public float getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(float targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public boolean isAchievedTarget() {
        return isAchievedTarget;
    }

    public void setAchievedTarget(boolean achievedTarget) {
        isAchievedTarget = achievedTarget;
    }
}
