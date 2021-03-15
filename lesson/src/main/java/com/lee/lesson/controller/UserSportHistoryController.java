package com.lee.lesson.controller;

import com.lee.lesson.domain.ResultObject;
import com.lee.lesson.domain.UserEverydaySportSituation;
import com.lee.lesson.domain.UserSportHistory;
import com.lee.lesson.service.UserSportHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user_sport_history")
public class UserSportHistoryController {

	@Autowired
	private UserSportHistoryService userSportHistoryService;
	
	@PostMapping("add")
	public ResultObject insertUserSportHistory(@RequestBody UserSportHistory userSportHistory) {
		int modifyId = userSportHistoryService.insertUserSportHistory(userSportHistory);
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
	public ResultObject getAllUserDrugHistory(@RequestParam int userId) {
		List<UserEverydaySportSituation> userEverydaySportSituations = userSportHistoryService.getAllUserSportHistory(userId);
		ResultObject resultObject = new ResultObject(0, "success", userEverydaySportSituations);

		return resultObject;
	}
	
}
