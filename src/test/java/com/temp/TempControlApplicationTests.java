package com.temp;

import com.temp.repository.ReportRecordRespository;
import com.temp.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TempControlApplicationTests {

	@Autowired
	ReportRecordRespository reportRecordRespository;

	@Autowired
	ReportService reportService;

	@Test
	void contextLoads() {
		//System.out.println(LocalDate.now());
		//System.out.println(reportRespository.findMostFreqWindByRoomId(1));
		reportService.generateDailyReport();


	}

}
