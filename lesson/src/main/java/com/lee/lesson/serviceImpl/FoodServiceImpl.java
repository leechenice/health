package com.lee.lesson.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.lesson.domain.Food;
import com.lee.lesson.domain.PaginationObject;
import com.lee.lesson.mapper.FoodMapper;
import com.lee.lesson.service.FoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

	@Resource
	private FoodMapper foodMapper;
	
	public Food getFoodById(int id) {
		Food food = foodMapper.getById(id);
		return food;
	}
	
	public int insertFood(Food food) {
		int modifyId = foodMapper.insert(food);
		return modifyId;
	}
	
	public PaginationObject getAllFood(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Food> foods = foodMapper.getAll();
		PageInfo<Food> appsPageInfo = new PageInfo<Food>(foods);
		long total = appsPageInfo.getTotal();
		PaginationObject paginationObject = new PaginationObject(foods, pageNum, pageSize, total);
		return paginationObject;
	}
	
	public int updateFood(Food food) {
		int modifyId = foodMapper.update(food);
		return modifyId;
	} 
	
	public int deleteFood(int id) {
		int modifyId = foodMapper.delete(id);
		return modifyId;
	}
}
