package com.lee.lesson.mapper;

import com.lee.lesson.domain.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMapper {
	int insert(Food food);
	
	Food getById(int id);
	
	List<Food> getAll();
		
	int update(Food food);
	
	int delete(int id);
}
