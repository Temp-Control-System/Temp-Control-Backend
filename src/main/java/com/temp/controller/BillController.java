package com.temp.controller;

import com.temp.service.BillService;
import com.temp.domain.DetailBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    @Autowired
    private BillService billService = null;
    @GetMapping(path = "/detail_bill")
    public DetailBill getDetailBill(@RequestParam(value = "roomId") int roomId){
        return billService.getDetailBill(roomId);
    }
}
