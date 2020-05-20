package com.temp.service;

import com.temp.domain.DetailBill;
import com.temp.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillService {
    @Autowired
    private ServiceRecordRepository serviceRecordRepository=null;

    public DetailBill getDetailBill(int roomId){
        return new DetailBill(serviceRecordRepository.findByRoomId(roomId));
    }

}
