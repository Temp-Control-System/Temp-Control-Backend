package com.temp.service;

import com.temp.domain.ReportRecord;
import com.temp.domain.RequestRecord;
import com.temp.repository.ReportRecordRespository;
import com.temp.repository.RequestRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRecordRespository reportRecordRespository;

    @Autowired
    RequestRecordRepository requestRecordRepository;

    @Scheduled(cron="0 0 0 * * ?")
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
        requestRecordRepository.deleteAll();
    }

    public List<ReportRecord> getReport(Date startDate, Date endDate) {
        return reportRecordRespository.getReport(startDate, endDate);
    }
}
