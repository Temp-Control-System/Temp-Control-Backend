package com.temp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于记录一个房间退房后总共的消费金额和持续时间
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalStatus {

    private int roomId;

    /**
     * 总共空调使用时间
     */
    private int lastTime;

    /**
     * 总费用
     */
    private int charge;

}
