package com.temp.repository;

import com.temp.domain.ServiceRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRecordRepository extends CrudRepository<ServiceRecord,Integer> {
    public List<ServiceRecord> findByRoomId(int roomId);
}
