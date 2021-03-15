package com.lee.lesson.serviceImpl;

import com.lee.lesson.domain.EnergyDate;
import com.lee.lesson.domain.UserIndex;
import com.lee.lesson.domain.UserRelationship;
import com.lee.lesson.mapper.UserFoodHistoryMapper;
import com.lee.lesson.mapper.UserIndexMapper;
import com.lee.lesson.mapper.UserSportHistoryMapper;
import com.lee.lesson.service.UserRelationshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRelationshipServiceImpl implements UserRelationshipService {
	
	@Resource
	private UserFoodHistoryMapper userFoodHistoryMapper;
	
	@Resource
	private UserSportHistoryMapper userSportHistoryMapper;

	@Resource
	private UserIndexMapper userIndexMapper;
	
	public UserRelationship getRelationship(int userId) {
		UserRelationship relationship = new UserRelationship();
		List<EnergyDate> foodEnergies = userFoodHistoryMapper.getSumFoodEnergy(userId);
		List<EnergyDate> sportEnergyDates = userSportHistoryMapper.getSumConsumeEnergy(userId);				
		List<UserIndex> userIndexs = userIndexMapper.getById(userId);
		Map<Integer, List<UserIndex>> userIndexes = new HashMap<Integer, List<UserIndex>>();
		
		for(UserIndex userIndex: userIndexs) {
			int indexType = userIndex.getIndexType();
			if (userIndexes.containsKey(indexType)) {
				List<UserIndex> userIndexs2 = userIndexes.get(indexType);
				userIndexs2.add(userIndex);
			} else {
				List<UserIndex> userIndexs2 = new ArrayList<UserIndex>();
				userIndexs2.add(userIndex);
				userIndexes.put(indexType, userIndexs2);
			}
		}
		
		relationship.setUserId(userId);
		relationship.setFoodEnergies(foodEnergies);
		relationship.setSportEnergies(sportEnergyDates);
		relationship.setUserIndexes(userIndexes);
		return relationship;
	}

}
