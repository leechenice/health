package com.lee.lesson.mapper;

import java.util.List;

import com.lee.lesson.domain.UserIndex;

public interface UserIndexMapper {

	List<UserIndex> getById(int userId);
}
