package com.lee.lesson.service;

import com.lee.lesson.domain.Food;
import com.lee.lesson.domain.PaginationObject;

public interface FoodService {
	
	public Food getFoodById(int id);
	
	public int insertFood(Food food);
	
	public PaginationObject getAllFood(int pageNum, int pageSize);
	
	public int updateFood(Food food); 
	
	public int deleteFood(int id);
}
