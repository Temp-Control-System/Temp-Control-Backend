package com.temp.domain;

import com.temp.enums.Wind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * 按天为粒度统计每间房间的数据，用于产生报表
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    int roomId;

    /**
     * 日期，按天为粒度，格式: yyyy-MM-dd
     */
    LocalDate date;

    /**
     * 该房间当日使用空调次数
     */
    int times;

    /**
     * 该房间当日最常用目标温度（该房间使用时间最长的目标温度）
     */
    float mostFreqTemp;

    /**
     * 该房间当日最常用风速（时间最长的风速）
     */
    @Enumerated(EnumType.ORDINAL)
    Wind mostFreqWind;

    /**
     * 达到目标温度次数
     */
    int reachTargetTimes;

    /**
     * 被调度次数（详单记录数）
     */
    int scheduledTimes;

    /**
     * 当日总费用
     */
    int totalCost;

    public ReportRecord(int roomId, LocalDate date, int times, float mostFreqTemp, Wind mostFreqWind, int reachTargetTimes, int scheduledTimes, int totalCost) {
        this.roomId = roomId;
        this.date = date;
        this.times = times;
        this.mostFreqTemp = mostFreqTemp;
        this.mostFreqWind = mostFreqWind;
        this.reachTargetTimes = reachTargetTimes;
        this.scheduledTimes = scheduledTimes;
        this.totalCost = totalCost;
    }

}
