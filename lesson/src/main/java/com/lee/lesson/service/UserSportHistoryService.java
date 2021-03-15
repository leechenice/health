package com.lee.lesson.service;

import java.util.List;

import com.lee.lesson.domain.UserEverydaySportSituation;
import com.lee.lesson.domain.UserSportHistory;

public interface UserSportHistoryService {

	public int insertUserSportHistory(UserSportHistory userSportHistory);
	
	public List<UserEverydaySportSituation> getAllUserSportHistory(int userId);
}
