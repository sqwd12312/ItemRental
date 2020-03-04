package com.qny.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qny.pojo.Schedule;
@Repository
public interface ScheduleMapper {
	public void insertschedule(Schedule schedule);

	public List<Schedule> selectAll();

	public void deleteschedule(Integer id);

	public void updateschedule(Schedule schedule);

	public Schedule selectbyid(Integer id);
}
