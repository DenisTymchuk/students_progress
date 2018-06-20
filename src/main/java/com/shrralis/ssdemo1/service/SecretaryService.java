package com.shrralis.ssdemo1.service;

import com.shrralis.ssdemo1.dto.StudentDTO;
import com.shrralis.ssdemo1.dto.SubjectDTO;
import com.shrralis.ssdemo1.entity.Connector;
import com.shrralis.ssdemo1.entity.Group;
import com.shrralis.ssdemo1.entity.Student;
import com.shrralis.ssdemo1.entity.Subject;
import com.shrralis.ssdemo1.entity.Teacher;
import com.shrralis.ssdemo1.repository.ConnectorRepository;
import com.shrralis.ssdemo1.repository.GroupRepository;
import com.shrralis.ssdemo1.repository.StudentRepository;
import com.shrralis.ssdemo1.repository.SubjectRepository;
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
	private final SubjectRepository subjectRepository;
	private final ConnectorRepository connectorRepository;

	@Autowired
	public SecretaryService(StudentRepository studentRepository,
	                        GroupRepository groupRepository,
	                        TeacherRepository teacherRepository,
	                        SubjectRepository subjectRepository,
	                        ConnectorRepository connectorRepository) {
		this.studentRepository = studentRepository;
		this.groupRepository = groupRepository;
		this.teacherRepository = teacherRepository;
		this.subjectRepository = subjectRepository;
		this.connectorRepository = connectorRepository;
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

	public Subject saveSubject(SubjectDTO subjectDTO) {
		Group group = groupRepository.findById(subjectDTO.getGroupId());
		Teacher teacher = teacherRepository.findById(subjectDTO.getTeacherId());
		Subject subject = subjectRepository.save(Subject.builder().name(subjectDTO.getSubjectName()).build());

		connectorRepository.save(Connector.builder()
				.group(group)
				.subject(subject)
				.teacher(teacher).build());

		return subject;
	}

	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	public List<Connector> getAllSubjectsInfo() {
		return connectorRepository.findAll();
	}
}
