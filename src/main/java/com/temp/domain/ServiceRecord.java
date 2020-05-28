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
    private int roomId;
    @Enumerated(EnumType.ORDINAL)
    private Wind wind;
    @CreatedDate
    private Date serviceStartTime;
    private int totalCost;
    private int lastTime;
    @Enumerated(EnumType.ORDINAL)
    private Mode mode;
    private float endTemperature;
    private float startTemperature;
    boolean isAchievedTarget;
    public ServiceRecord(){

    }
    public ServiceRecord(int roomId, Wind wind, Date serviceStartTime, int totalCost, int lastTime, Mode mode, float endTemperature, float startTemperature, boolean isAchievedTarget) {
        this.roomId = roomId;
        this.wind = wind;
        this.serviceStartTime = serviceStartTime;
        this.totalCost = totalCost;
        this.lastTime = lastTime;
        this.mode = mode;
        this.endTemperature = endTemperature;
        this.startTemperature = startTemperature;
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

    public boolean isAchievedTarget() {
        return isAchievedTarget;
    }

    public void setAchievedTarget(boolean achievedTarget) {
        isAchievedTarget = achievedTarget;
    }
}
