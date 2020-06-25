package com.temp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.temp.SystemConfigure;
import com.temp.enums.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
public class InitController {
    @GetMapping("/init")
    public void initSys(@RequestParam("mode")String mode,
                        @RequestParam("envTemperature")float envTemperature,
                        @RequestParam("serviceCapacity")int serviceCapacity,
                        @RequestParam("defaultTargetTemperature")float defaultTargetTemperature,
                        @RequestParam("minTemperature")float minTemperature,
                        @RequestParam("maxTemperature")float maxTemperature,
                        @RequestParam("InitWaitingTime")int InitWaitingTime,
                        @RequestParam("LowWindCostPerSlot")int LowWindCostPerSlot,
                        @RequestParam("MiddleWindCostPerSlot")int MiddleWindCostPerSlot,
                        @RequestParam("HighWindCostPerSlot")int HighWindCostPerSlot) {
        SystemConfigure.mode = Mode.valueOf(mode);
        SystemConfigure.envTemperature = envTemperature;
        SystemConfigure.serviceCapacity = serviceCapacity;
        SystemConfigure.defaultTargetTemperature = defaultTargetTemperature;
        SystemConfigure.minTemperature = minTemperature;
        SystemConfigure.maxTemperature = maxTemperature;
        SystemConfigure.InitWaitingTime = InitWaitingTime;
        SystemConfigure.LowWindCostPerSlot = LowWindCostPerSlot;
        SystemConfigure.MiddleWindCostPerSlot = MiddleWindCostPerSlot;
        SystemConfigure.HighWindCostPerSlot = HighWindCostPerSlot;
    }
}
