package com.lee.lesson.serviceImpl;

import com.lee.lesson.domain.UserEverydayFoodSituation;
import com.lee.lesson.domain.UserFoodHistory;
import com.lee.lesson.mapper.UserFoodHistoryMapper;
import com.lee.lesson.service.UserFoodHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@Service
public class UserFoodHistoryServiceImpl implements UserFoodHistoryService{

	@Resource
	private UserFoodHistoryMapper userFoodHistoryMapper;
	
	public int insertUserFoodHistory(UserFoodHistory userFoodHistory) {
		int modifyId = userFoodHistoryMapper.insert(userFoodHistory);
		return modifyId;
	}
	
	public List<UserEverydayFoodSituation> getAllUserFoodHistory(int userId) {
		List<UserFoodHistory> userFoodHistories = userFoodHistoryMapper.getAll(userId);
		Map<Date, List<UserFoodHistory>> userFoodMap = new TreeMap<Date, List<UserFoodHistory>>();
		for(UserFoodHistory userFoodHistory: userFoodHistories) {
			Date cDate = userFoodHistory.getCollectDate();
			if (userFoodMap.containsKey(cDate)) {
				List<UserFoodHistory> userFoodList = userFoodMap.get(cDate);
				userFoodList.add(userFoodHistory);
			} else {
				List<UserFoodHistory> userFoodList = new ArrayList<UserFoodHistory>();
				userFoodList.add(userFoodHistory);
				userFoodMap.put(cDate, userFoodList);
			}
		}
		
		List<UserEverydayFoodSituation> userEverydayFoodSituations = new ArrayList<UserEverydayFoodSituation>();
		
		for(Entry<Date, List<UserFoodHistory>> entry: userFoodMap.entrySet()) {
			UserEverydayFoodSituation userEverydayFoodSituation = new UserEverydayFoodSituation();
			userEverydayFoodSituation.setUserId(userId);
			userEverydayFoodSituation.setCollectDate(entry.getKey());
			userEverydayFoodSituation.setUserFoodHistories(entry.getValue());
			
			int sumFoodEnergy = 0;
			for(UserFoodHistory userFoodHistory: entry.getValue()) {
				sumFoodEnergy += userFoodHistory.getFoodQuantity() * userFoodHistory.getFood().getFoodEnergy() / 500;
			}
			userEverydayFoodSituation.setSumFoodEnergy(sumFoodEnergy);
			
			userEverydayFoodSituations.add(userEverydayFoodSituation);
		}
		
		return userEverydayFoodSituations;
	}
}
