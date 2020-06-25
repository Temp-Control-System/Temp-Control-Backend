package com.temp.service;

import com.temp.domain.DetailBill;
import com.temp.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BillService {
    @Autowired
    private ServiceRecordRepository serviceRecordRepository=null;
    @Autowired
    private  CheckInService checkInService = null;
    @Autowired
    private  ScheduleService scheduleService = null;
    // 获取详单信息，若未关机则关闭空调，关闭后默认离店
    public DetailBill getDetailBill(int roomId){
        scheduleService.takeOff(roomId);
        DetailBill detailBill =  new DetailBill(serviceRecordRepository.findByRoomId(roomId));
        detailBill.setCheckInDate(checkInService.queryCheckInDate(roomId));
        detailBill.setCheckOutDate(new Date());
        return detailBill;
    }

}
