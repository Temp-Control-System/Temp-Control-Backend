package com.temp.controller;

import com.temp.domain.RoomStatus;
import com.temp.service.ScheduleService;
import com.temp.domain.WindSupplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
*  调度控制器，可以从此提交修改空调运行状态的请求
* */
@RestController
public class ScheduleController {
    // 调度服务
    @Autowired
    private ScheduleService scheduleService = null;

    // 处理调温调风请求
    @RequestMapping(path="/add",method= RequestMethod.POST)
    public RoomStatus addRequest(WindSupplyRequest wsr){
        scheduleService.addRequest(wsr.getRoomId(),wsr);
        return scheduleService.getRoomStatus(wsr.getRoomId());
    }

    // 处理实时状态查询请求
    @RequestMapping(path="/query",method= RequestMethod.GET)
    public RoomStatus getRoomStatus(@RequestParam(value = "roomId") int roomId){
        return scheduleService.getRoomStatus(roomId);
    }

    // 处理关机请求
    @RequestMapping(path="/takeoff",method = RequestMethod.GET)
    public RoomStatus takeOff(@RequestParam(value = "roomId")int roomId){
        scheduleService.takeOff(roomId);
        return scheduleService.getRoomStatus(roomId);
    }

    // 返回所有房间状态
    @RequestMapping(path="/query_all",method= RequestMethod.GET)
    public List<RoomStatus> getAllRoomStatus(){
        return scheduleService.getAllRoomStatus();
    }

}
