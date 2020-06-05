package com.temp.domain;
import com.temp.enums.*;
import com.temp.enums.Mode;
import com.temp.enums.RequestType;
import com.temp.enums.Wind;

import javax.persistence.*;
import java.util.Date;
/*
* 实体类，储存某次请求的信息，用于报表生成
*/
@Entity
public class RequestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 房间号
    @Column(nullable = false)
    private int roomId;
    // 枚举，亲求类型，关机或使用空调
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    RequestType requestType;
    @Enumerated(EnumType.ORDINAL)
    private Wind wind;
    @Enumerated(EnumType.ORDINAL)
    private Mode mode;
    private float targetTemperature;
    // 请求到来的时间
    private Date request_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
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

    public Date getRequest_time() {
        return request_time;
    }

    public void setRequest_time(Date request_time) {
        this.request_time = request_time;
    }
}
