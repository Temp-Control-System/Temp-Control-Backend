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
    public DetailBill getDetailBill(int roomId){
        DetailBill detailBill =  new DetailBill(serviceRecordRepository.findByRoomId(roomId));
        detailBill.setCheckInDate(checkInService.queryCheckInDate(roomId));
        detailBill.setCheckOutDate(new Date());
        checkInService.checkOut(roomId);
        return detailBill;
    }

}
