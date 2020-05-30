package com.temp.service;

import com.temp.domain.ReportRecord;
import com.temp.repository.ReportRecordRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRecordRespository reportRecordRespository;

    public void generateDailyReport() {
        List<Integer> rooms = reportRecordRespository.findDistinctRoom();
        LocalDate now = LocalDate.now();
        for (Integer roomId : rooms) {
            ReportRecord reportRecord = new ReportRecord(roomId, now,
                    reportRecordRespository.countTimesByRoomId(roomId),
                    reportRecordRespository.findMostFreqTempByRoomId(roomId),
                    reportRecordRespository.findMostFreqWindByRoomId(roomId),
                    reportRecordRespository.findReachTargetTimesByRoomId(roomId),
                    reportRecordRespository.findScheduledTimesByRoomId(roomId),
                    reportRecordRespository.findTotalCostByRoomId(roomId));
            reportRecordRespository.save(reportRecord);
        }
        // TODO
    }
}
