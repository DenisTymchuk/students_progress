package com.shrralis.ssdemo1.controller;

import com.shrralis.ssdemo1.dto.StudentDTO;
import com.shrralis.ssdemo1.dto.SubjectDTO;
import com.shrralis.ssdemo1.service.SecretaryService;
import com.shrralis.tools.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SecretaryRestController {
	private final SecretaryService secretaryService;

	@Autowired
	public SecretaryRestController(SecretaryService groupService) {
		this.secretaryService = groupService;
	}

	@PostMapping("/students")
	public JsonResponse saveStudent(@RequestBody @Valid StudentDTO studentDTO) {
		return new JsonResponse(secretaryService.saveStudent(studentDTO));
	}

	@GetMapping("/groups")
	public JsonResponse getAllGroups() {
		return new JsonResponse(secretaryService.getAllGroups());
	}

	@GetMapping("/students")
	public JsonResponse getAllStudents() {
		return new JsonResponse(secretaryService.getAllStudents());
	}

	@GetMapping("/teachers")
	public JsonResponse getAllTeachers() {
		return new JsonResponse(secretaryService.getAllTeachers());
	}

	@PostMapping("/subjects")
	public JsonResponse saveTeacher(@RequestBody @Valid SubjectDTO subjectDTO) {
		return new JsonResponse(secretaryService.saveSubject(subjectDTO));
	}

	@GetMapping("/subjects")
	public JsonResponse getAllSubjects() {
		return new JsonResponse(secretaryService.getAllSubjects());
	}

	@GetMapping("/subjectsInfo")
	public JsonResponse getAllSubjectsInfo() {
		return new JsonResponse(secretaryService.getAllSubjectsInfo());
	}

}
