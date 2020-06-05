package com.temp.domain;
import com.temp.enums.*;
import com.temp.enums.Mode;
import com.temp.enums.Wind;

public class WindSupplyRequest{
    // 房间ID
    private int roomId;
    // 枚举，风速
    private Wind wind;
    // 枚举，温控模式
    private Mode mode;
    // 目标温度
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

