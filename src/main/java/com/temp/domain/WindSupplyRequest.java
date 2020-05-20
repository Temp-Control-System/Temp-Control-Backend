package com.temp.domain;
import com.temp.enums.*;
import com.temp.enums.Mode;
import com.temp.enums.Wind;

public class WindSupplyRequest{

    private int roomId;
    private Wind wind;
    private Mode mode;
    private float targetTemperature;

    public WindSupplyRequest(){

    }

    public WindSupplyRequest(Mode mode,Wind wind,float targetTemperature) {
        this.wind = wind;
        this.mode = mode;
        this.targetTemperature = targetTemperature;
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

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public float getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(float targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

}

