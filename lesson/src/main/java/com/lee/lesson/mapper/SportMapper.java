package com.lee.lesson.mapper;

import java.util.List;

import com.lee.lesson.domain.Sport;

public interface SportMapper {
	int insert(Sport drug);
	
	Sport getById(int id);
	
	List<Sport> getAll();
	
	int update(Sport drug);
	
	int delete(int id);
}
