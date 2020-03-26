package com.temp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 记录空调的实际运行状态
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunningStatus {

    private int roomId;

    /**
     * 制热：heat
     * 制冷：cool
     */
    private String mode;

    /**
     * cool: 18 - 25
     * heat: 25 - 30
     */
    private int runningTemperature;

    /**
     * 高风: high
     * 中风: medium
     * 低风: low
     */
    private String wind;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 持续时间（s）
     */
    private int lastTime;

    /**
     * 当前消费金额（元）
     */
    private int charge;

    /**
     * 当前该房间空调是否运行在这个状态
     */
    private boolean isCurState;

}
