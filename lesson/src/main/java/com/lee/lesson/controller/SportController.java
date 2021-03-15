package com.lee.lesson.controller;

import com.lee.lesson.domain.PaginationObject;
import com.lee.lesson.domain.ResultObject;
import com.lee.lesson.domain.Sport;
import com.lee.lesson.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/sport")
public class SportController {

	@Autowired
	private SportService sportService;
	
	@GetMapping("get")
	public ResultObject getSportById(@RequestParam int id) {
		Sport sport = sportService.getSportById(id);
		ResultObject resultObject = new ResultObject(0, "success", sport);
		return resultObject;
	}
	
	@PostMapping("add")
	public ResultObject insertSport(Sport sport) {
		int modifyId = sportService.insertSport(sport);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
	
	@GetMapping("list")
	public ResultObject getAllSport(@RequestParam int pageNum, @RequestParam int pageSize) {
		PaginationObject paginationObject = sportService.getAllSport(pageNum, pageSize);
		ResultObject resultObject = new ResultObject(0, "success", paginationObject);
		return resultObject;
	}
	
	@DeleteMapping("delete")
	public ResultObject deleteSport(@RequestParam int id) {
		int modifyId = sportService.deleteSport(id);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
	
	@PutMapping("edit")
	public ResultObject updateSport(Sport sport) {
		int modifyId = sportService.updateSport(sport);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
}
