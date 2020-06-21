package com.temp.repository;

import com.temp.domain.ReportRecord;
import com.temp.enums.Wind;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface ReportRecordRespository extends CrudRepository<ReportRecord, Integer> {

    /**
     * 根据起止日期获取报表
     */
    @Query(nativeQuery = true, value = "select room_id,  max(id) id, max(date) date, avg(use_times) use_times, avg(most_freq_temp) most_freq_temp, avg(most_freq_wind) most_freq_wind, avg(reach_target_times) reach_target_times, avg(scheduled_times) scheduled_times, avg(total_cost) total_cost\n" +
            "from report_record \n" +
            "where date between ?1 and ?2 \n" +
            "group by room_id")
    List<ReportRecord> getReport(Date startDate, Date endDate);

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
