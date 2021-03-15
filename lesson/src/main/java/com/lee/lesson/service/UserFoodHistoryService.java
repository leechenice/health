package com.lee.lesson.service;

import com.lee.lesson.domain.UserEverydayFoodSituation;
import com.lee.lesson.domain.UserFoodHistory;

import java.util.List;


public interface UserFoodHistoryService {

	public int insertUserFoodHistory(UserFoodHistory userFoodHistory);
	
	public List<UserEverydayFoodSituation> getAllUserFoodHistory(int userId);
}
