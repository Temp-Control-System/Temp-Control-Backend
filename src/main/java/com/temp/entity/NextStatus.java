package com.temp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于服务端返回前端告知空调下一个时间片的运行状态
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NextStatus {
    /**
     * 制热：heat
     * 制冷：cool
     */
    private String mode = "cool";

    /**
     * cool: 18 - 25
     * heat: 25 - 30
     */
    private int nextTemperature = 25;

    /**
     * 高风: high
     * 中风: medium
     * 低风: low
     */
    private String wind = "low";

    /**
     * 当前已消费金额
     */
    private int charge;
}
