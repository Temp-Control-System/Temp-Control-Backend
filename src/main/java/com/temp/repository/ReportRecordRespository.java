package com.temp.repository;

import com.temp.domain.ReportRecord;
import com.temp.enums.Wind;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRecordRespository extends CrudRepository<ReportRecord, Integer> {

    /**
     * 当日所有发出请求的 roomId
     */
    @Query(nativeQuery = true, value = "select distinct(room_id) from service_record ")
    List<Integer> findDistinctRoom();

    /**
     * 计算一日内空调被关了多少次（统计开关次数）
     */
    @Query(nativeQuery = true, value = "select sum(request_type) from request_record where room_id = ?")
    int countTimesByRoomId(int roomId);

    @Query(nativeQuery = true, value = "select target_temperature from service_record\n" +
            "where room_id = ? group by target_temperature order by sum(last_time) desc limit 1")
    float findMostFreqTempByRoomId(int roomId);

    @Query(nativeQuery = true, value = "select wind from service_record\n" +
            "where room_id = ? group by wind order by sum(last_time) desc limit 1")
    Wind findMostFreqWindByRoomId(int roomId);

    @Query(nativeQuery = true, value = "select sum(is_achieved_target) from service_record where room_id = ? ")
    int findReachTargetTimesByRoomId(int roomId);

    @Query(nativeQuery = true, value = "select count(*) from service_record where room_id = ?")
    int findScheduledTimesByRoomId(int roomId);

    @Query(nativeQuery = true, value = "select sum(total_cost) from service_record where room_id = ?")
    int findTotalCostByRoomId(int roomId);
}
