package com.temp;

import com.temp.enums.Mode;
import com.temp.enums.Wind;

public class SystemConfigure {
    // 总房间数
    final public static int totalRoomNum = 256;
    // 时间片，单位为秒，每18秒一个周期。设置为18，可以保证所有风速每个时间片的消费均为整数，计费时，不宜使用浮点数
    final public static int timeSlot = 18;
    // 唤醒边缘,即超过目标值margin后重新开始工作
    final public static float margin = (float)1.0;
    // 单位时间片温度变化值
    final public static float MiddleWindTemperatureChangePerSlot = (float)(0.5 / 60*timeSlot);
    final public static float HighWindTemperatureChangePerSlot = (float)(0.5 / 60 * timeSlot * 1.2);
    final public static float LowWindTemperatureChangePerSlot = (float)(0.5 / 60* timeSlot * 0.8);
    final public static float TakeoffTemperatureChangePerSlot = (float)(0.5 / 60* timeSlot);
    // 服务容量
    public static int serviceCapacity = 3;
    // 环境温度
    public static float envTemperature = 25;
    // 温控模式,默认制冷
    public static Mode mode = Mode.REFRIGERATION;
    // 温控范围
    public static float minTemperature = 15;
    public static float maxTemperature = 30;
    // 缺省温度
    public static float defaultTargetTemperature = 25;
    // 缺省风速
    public static Wind DefaultWind = Wind.MIDDLE;
    // 初始等待时间，单位为秒
    public static int InitWaitingTime = 120;
    // 单位时间片消费，单位为 分/timeSlot秒 1元 = 100 分
    public static int HighWindCostPerSlot = 100 * timeSlot / 60;
    public static int MiddleWindCostPerSlot = 100 * timeSlot / 120;
    public static int LowWindCostPerSlot = 100 * timeSlot / 180;
}
