package com.lee.lesson.service;

import com.lee.lesson.domain.PaginationObject;
import com.lee.lesson.domain.User;

public interface UserService {

	public int insertUser(User user);
	
	public User getUserById(int id);
	
	public PaginationObject getAllUser(int pageNum, int pageSize);
	
	public int updateUser(User user); 
	
	public int deleteUser(int id);
}
