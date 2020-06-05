package com.temp.controller;

import com.temp.domain.CheckInRecord;
import com.temp.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckInController {
    @Autowired
    private CheckInService checkInService=null;
    // 入住
    @RequestMapping(path="/check_in",method = RequestMethod.GET)
    public CheckInRecord checkIn() {
        return checkInService.checkIn();
    }
    // 离店
    @RequestMapping(path="/check_out",method = RequestMethod.GET)
    public CheckInRecord checkout(@RequestParam(value = "roomId") int roomId) {
        return checkInService.checkOut(roomId);
    }
}
