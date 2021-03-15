package com.lee.lesson.serviceImpl;

import com.lee.lesson.domain.UserEverydaySportSituation;
import com.lee.lesson.domain.UserSportHistory;
import com.lee.lesson.mapper.UserSportHistoryMapper;
import com.lee.lesson.service.UserSportHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@Service
public class UserSportHistoryServiceImpl implements UserSportHistoryService{

	@Resource
	private UserSportHistoryMapper userSportHistoryMapper;
	
	public int insertUserSportHistory(UserSportHistory userSportHistory) {
		int modifyId = userSportHistoryMapper.insert(userSportHistory);
		return modifyId;
	}
	
	public List<UserEverydaySportSituation> getAllUserSportHistory(int userId) {
		List<UserSportHistory> userSportHistories = userSportHistoryMapper.getAll(userId);
		Map<Date, List<UserSportHistory>> userSportMap = new TreeMap<Date, List<UserSportHistory>>();
		for(UserSportHistory userSportHistory: userSportHistories) {
			Date cDate = userSportHistory.getCollectDate();
			if (userSportMap.containsKey(cDate)) {
				List<UserSportHistory> userSportList = userSportMap.get(cDate);
				userSportList.add(userSportHistory);
			} else {
				List<UserSportHistory> userSportList = new ArrayList<UserSportHistory>();
				userSportList.add(userSportHistory);
				userSportMap.put(cDate, userSportList);
			}
		}
		
		List<UserEverydaySportSituation> userEverydaySportSituations = new ArrayList<UserEverydaySportSituation>();
		
		for(Entry<Date, List<UserSportHistory>> entry: userSportMap.entrySet()) {
			UserEverydaySportSituation userEverydaySportSituation = new UserEverydaySportSituation();
			userEverydaySportSituation.setUserId(userId);
			userEverydaySportSituation.setCollectDate(entry.getKey());
			userEverydaySportSituation.setUserSportHistories(entry.getValue());
			
			int sumConsumeEnergy = 0;
			for(UserSportHistory userSportHistory: entry.getValue()) {
				sumConsumeEnergy += userSportHistory.getSportTime() * userSportHistory.getSport().getConsumeEnergy();
			}
			userEverydaySportSituation.setSumConsumeEnergy(sumConsumeEnergy);
			
			userEverydaySportSituations.add(userEverydaySportSituation);
		}
		
		return userEverydaySportSituations;
	}
}
