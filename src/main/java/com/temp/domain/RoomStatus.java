package com.temp.domain;
import com.temp.enums.*;
import com.temp.enums.Mode;
import com.temp.enums.SUStatus;
import com.temp.enums.Wind;

public class RoomStatus{
    private int roomId;
    private float temperature;
    private float targetTemperature;
    private SUStatus status = SUStatus.OFF;
    private Wind wind = Wind.LOW;
    private Mode mode = Mode.REFRIGERATION;
    private int totalCost;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public float getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(float targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public SUStatus getStatus() {
        return status;
    }

    public void setStatus(SUStatus status) {
        this.status = status;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}