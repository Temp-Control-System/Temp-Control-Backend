package com.temp.controller;

import com.temp.domain.ReportRecord;
import com.temp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/report")
    public List<ReportRecord> getReport(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return reportService.getReport(dateFormat.parse(startDate), dateFormat.parse(endDate));
    }
}
