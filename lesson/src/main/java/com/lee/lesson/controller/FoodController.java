package com.lee.lesson.controller;

import com.lee.lesson.domain.Food;
import com.lee.lesson.domain.PaginationObject;
import com.lee.lesson.domain.ResultObject;
import com.lee.lesson.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@GetMapping("get")
	public ResultObject getFoodById(@RequestParam int id) {
		Food food = foodService.getFoodById(id);
		ResultObject resultObject = new ResultObject(0, "success", food);
		return resultObject;
	}
	
	@PostMapping("add")
	public ResultObject insertFood(Food food) {
		int modifyId = foodService.insertFood(food);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
	
	@GetMapping("list")
	public ResultObject getAllFood(@RequestParam int pageNum, @RequestParam int pageSize) {
		PaginationObject paginationObj = foodService.getAllFood(pageNum, pageSize);
		ResultObject resultObject = new ResultObject(0, "success", paginationObj);

		return resultObject;
	}
	
	@DeleteMapping("delete")
	public ResultObject deleteUser(@RequestParam int id) {
		int modifyId = foodService.deleteFood(id);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
	
	@PutMapping("edit")
	public ResultObject updateUser(Food food) {
		int modifyId = foodService.updateFood(food);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
}
