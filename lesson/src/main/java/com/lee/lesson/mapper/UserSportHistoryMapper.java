package com.lee.lesson.mapper;

import com.lee.lesson.domain.EnergyDate;
import com.lee.lesson.domain.UserSportHistory;

import java.util.List;

public interface UserSportHistoryMapper {

	public int insert(UserSportHistory userSportHistory);
	
	public List<UserSportHistory> getAll(int userId);
	
	public List<EnergyDate> getSumConsumeEnergy(int userId);
}
