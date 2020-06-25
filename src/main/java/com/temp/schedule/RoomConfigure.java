package com.temp.schedule;

import com.temp.SystemConfigure;

/*
*  房间默认设置配置
* */
public class RoomConfigure {
    public static float getInitRoomTemperature(int roomId){
        switch(roomId){
            case 1:
                return 32;
            case 2:
                return 28;
            case 3:
                return 30;
            case 4:
                return 29;
            case 5:
                return 35;
            default:
                return SystemConfigure.envTemperature;
        }
    }
}
