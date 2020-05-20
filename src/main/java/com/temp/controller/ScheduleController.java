package com.temp.controller;

import com.temp.domain.RoomStatus;
import com.temp.service.ScheduleService;
import com.temp.domain.WindSupplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService = null;
    @RequestMapping(path="/add",method= RequestMethod.POST)
    public RoomStatus addRequest(WindSupplyRequest wsr){
        scheduleService.addRequest(wsr.getRoomId(),wsr);
        return scheduleService.getRoomStatus(wsr.getRoomId());
    }

    @RequestMapping(path="/query",method= RequestMethod.GET)
    public RoomStatus getRoomStatus(@RequestParam(value = "roomId") int roomId){
        return scheduleService.getRoomStatus(roomId);
    }

    @RequestMapping(path="/takeoff",method = RequestMethod.GET)
    public RoomStatus takeOff(@RequestParam(value = "roomId")int roomId){
        scheduleService.takeOff(roomId);
        return scheduleService.getRoomStatus(roomId);
    }
}
