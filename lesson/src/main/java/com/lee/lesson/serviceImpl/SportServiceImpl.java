package com.lee.lesson.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.lesson.domain.PaginationObject;
import com.lee.lesson.domain.Sport;
import com.lee.lesson.mapper.SportMapper;
import com.lee.lesson.service.SportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SportServiceImpl implements SportService{

	@Resource
	private SportMapper sportMapper;
	
	public Sport getSportById(int id) {
		Sport sport = sportMapper.getById(id);
		return sport;
	}
	
	public int insertSport(Sport sport) {
		int modifyId = sportMapper.insert(sport);
		return modifyId;
	}
	
	public PaginationObject getAllSport(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Sport> sports = sportMapper.getAll();
		PageInfo<Sport> appsPageInfo = new PageInfo<Sport>(sports);
		long total = appsPageInfo.getTotal();
		PaginationObject paginationObject = new PaginationObject(sports, pageNum, pageSize, total);
		return paginationObject;	}
	
	public int updateSport(Sport sport) {
		int modifyId = sportMapper.update(sport);
		return modifyId;
	} 
	
	public int deleteSport(int id) {
		int modifyId = sportMapper.delete(id);
		return modifyId;
	}
}
