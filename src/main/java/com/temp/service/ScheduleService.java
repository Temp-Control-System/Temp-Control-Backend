package com.temp.service;

import com.temp.domain.RoomStatus;
import com.temp.schedule.ServiceUnitManager;
import com.temp.SystemConfigure;
import com.temp.domain.WindSupplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    private ServiceUnitManager serviceUnitManager  = null;

    @Scheduled(fixedRate = SystemConfigure.timeSlot * 1000)
    public void timeSlotSchedule(){
        serviceUnitManager.executePerTimeSlot();
    }

    public void addRequest(int roomId, WindSupplyRequest wsr){
        serviceUnitManager.handNewReq(roomId,wsr);
    }

    public void takeOff(int roomId){
        serviceUnitManager.takeOff(roomId);
    }

    public RoomStatus getRoomStatus(int roomId){
        return  serviceUnitManager.getRoomStatus(roomId);
    }
}
