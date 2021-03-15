package com.lee.lesson.service;

import com.lee.lesson.domain.PaginationObject;
import com.lee.lesson.domain.Sport;

public interface SportService {
	
	public Sport getSportById(int id);
	
	public int insertSport(Sport sport);
	
	public PaginationObject getAllSport(int pageNum, int pageSize);
	
	public int updateSport(Sport sport); 
	
	public int deleteSport(int id);
}
