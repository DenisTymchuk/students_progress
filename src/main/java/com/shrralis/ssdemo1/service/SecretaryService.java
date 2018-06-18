package com.shrralis.ssdemo1.service;

import com.shrralis.ssdemo1.dto.StudentDTO;
import com.shrralis.ssdemo1.entity.Group;
import com.shrralis.ssdemo1.entity.Student;
import com.shrralis.ssdemo1.entity.Teacher;
import com.shrralis.ssdemo1.repository.GroupRepository;
import com.shrralis.ssdemo1.repository.StudentRepository;
import com.shrralis.ssdemo1.repository.TeacherRepository;
import com.shrralis.tools.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Transactional
public class SecretaryService {

	private final GroupRepository groupRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	@Autowired
	public SecretaryService(StudentRepository studentRepository, GroupRepository groupRepository, TeacherRepository teacherRepository) {
		this.studentRepository = studentRepository;
		this.groupRepository = groupRepository;
		this.teacherRepository = teacherRepository;
	}

	public Student saveStudent(StudentDTO studentDTO) {
		Group group = groupRepository.findById(studentDTO.getGroupId());

		return studentRepository.save(Student.builder()
				.group(group)
				.middleName(studentDTO.getMiddleName())
				.name(studentDTO.getName())
				.surname(studentDTO.getSurname()).build());
	}

	public List<Group> getAllGroups() {
		return groupRepository.findAll();
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}
}
