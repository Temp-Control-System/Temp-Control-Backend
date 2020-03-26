package com.temp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示空调运作状态的请求
 * 用户的请求和服务端的回应都使用该类
 * 用户的请求表示希望空调的运作状态，服务端的响应表示接下去空调应该如何运作
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetStatus {
    /**
     * 制热：heat
     * 制冷：cool
     */
    private String mode = "cool";

    /**
     * cool: 18 - 25
     * heat: 25 - 30
     */
    private int targetTemperature = 25;

    /**
     * 高风: high
     * 中风: medium
     * 低风: low
     */
    private String wind = "low";

}
