package com.temp.service;

import com.temp.SystemConfigure;
import com.temp.domain.CheckInRecord;
import com.temp.enums.CheckStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class CheckInService {
    @Autowired
    private HashMap<Integer, Date> checkInTable = null;
    @Bean
    public HashMap<Integer, Date> integerDateHashMap(){
        return new HashMap<Integer,Date>();
    }

    public CheckInRecord checkIn(){
        for(int i=0;i< SystemConfigure.totalRoomNum;i++){
            if(!checkInTable.containsKey(i)){
                checkInTable.put(i, new Date());
                return new CheckInRecord(i, CheckStatus.CHECK_IN,new Date());
            }
        }
        return null;
    }

    public CheckInRecord checkOut(int roomId){
        checkInTable.remove(roomId);
        return null;
    }

    public Date queryCheckInDate(int roomId){
        return checkInTable.get(roomId);
    }
}
