package com.temp;

public class SystemConfigure {
    // 总房间数
    public static int totalRoomNum = 256;
    // 服务容量
    public static int serviceCapacity = 2;
    // 环境温度
    public static float envTemperature = 25;
    // 初始等待时间
    public static int InitWaitingTime = 30;
    // 时间片，单位为秒
    final public static int timeSlot = 5;
    // 唤醒边缘
    final public static float margin = (float)1.0;
    // 单位时间片温度变化值
    final public static float MiddleWindTemperatureChangePerSlot = (float)(0.5 / 60*timeSlot);
    final public static float HighWindTemperatureChangePerSlot = (float)(0.5 / 60 * timeSlot * 1.2);
    final public static float LowWindTemperatureChangePerSlot = (float)(0.5 / 60* timeSlot * 0.8);
    final public static float TakeoffTemperatureChangePerSlot = (float)(0.5 / 60* timeSlot);

    // 单位时间片消费，单位为 分/timeSlot秒 1元 = 100 分
    final public static int HighWindCostPerSlot = 100 * timeSlot / 60;
    final public static int MiddleWindCostPerSlot = 100 * timeSlot / 120;
    final public static int LowWindCostPerSlot = 100 * timeSlot / 180;
}
