package com.lee.lesson.controller;

import com.lee.lesson.domain.ResultObject;
import com.lee.lesson.domain.UserEverydayFoodSituation;
import com.lee.lesson.domain.UserFoodHistory;
import com.lee.lesson.service.UserFoodHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user_food_history")
public class UserFoodHistoryController {

	@Autowired
	private UserFoodHistoryService userFoodHistoryService;
	
	@PostMapping("add")
	public ResultObject insertUserFoodHistory(@RequestBody UserFoodHistory userFoodHistory) {
		int modifyId = userFoodHistoryService.insertUserFoodHistory(userFoodHistory);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject();
		resultObject.setResult(map);
		if (modifyId == 1) {
			resultObject.setCode(0);
			resultObject.setMsg("success");
		} else {
			resultObject.setCode(-1);
			resultObject.setMsg("fail");
		}
		
		return resultObject;
	}
	
	@GetMapping("get")
	public ResultObject getAllUserFoodHistory(@RequestParam int userId) {
		List<UserEverydayFoodSituation> userEverydayFoodSituations = userFoodHistoryService.getAllUserFoodHistory(userId);
		ResultObject resultObject = new ResultObject(0, "success", userEverydayFoodSituations);
		return resultObject;
	}	
}
