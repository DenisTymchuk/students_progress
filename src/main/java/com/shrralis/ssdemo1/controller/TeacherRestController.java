package com.shrralis.ssdemo1.controller;

import com.shrralis.ssdemo1.service.TeacherService;
import com.shrralis.tools.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherRestController {

	private final TeacherService teacherService;

	@Autowired
	public TeacherRestController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("/subjects/modules")
	public JsonResponse getAllModules() {
		return new JsonResponse(teacherService.getAllSubjectsWithModules());
	}
}
