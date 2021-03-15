package com.lee.lesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lee.lesson.domain.ResultObject;
import com.lee.lesson.domain.UserRelationship;
import com.lee.lesson.service.UserRelationshipService;

@RestController
@RequestMapping("api/v1/relationship")
public class UserRelationshipController {

	@Autowired
	private UserRelationshipService relationshipService;
	
	@RequestMapping("get")
	public ResultObject getRelationship(@RequestParam int id) {
		UserRelationship relationship = relationshipService.getRelationship(id);
		ResultObject resultObject = new ResultObject(0, "success", relationship);
		return resultObject;
	}
}
