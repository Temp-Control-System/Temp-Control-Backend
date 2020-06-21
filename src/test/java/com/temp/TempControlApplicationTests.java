package com.temp;

import com.temp.repository.ReportRecordRespository;
import com.temp.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class TempControlApplicationTests {

	@Autowired
	ReportRecordRespository reportRecordRespository;

	@Autowired
	ReportService reportService;

	@Test
	void contextLoads() throws Exception{
		//System.out.println(LocalDate.now());
		//System.out.println(reportRespository.findMostFreqWindByRoomId(1));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" );
		Date d1 = dateFormat.parse("2020-05-10");
		Date d2 = dateFormat.parse("2020-06-30");
		System.out.println(reportService.getReport(d1, d2));
	}

}
