package com.lee.lesson.mapper;

import java.util.List;

import com.lee.lesson.domain.EnergyDate;
import com.lee.lesson.domain.UserFoodHistory;

public interface UserFoodHistoryMapper {

	public int insert(UserFoodHistory userFoodHistory);
	
	public List<UserFoodHistory> getAll(int userId);
	
	public List<EnergyDate> getSumFoodEnergy(int userId);
}
