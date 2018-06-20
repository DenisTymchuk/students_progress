package com.shrralis.ssdemo1.service;

import com.shrralis.ssdemo1.dto.SubjectModuleDTO;
import com.shrralis.ssdemo1.entity.Connector;
import com.shrralis.ssdemo1.entity.Module;
import com.shrralis.ssdemo1.entity.Subject;
import com.shrralis.ssdemo1.repository.ConnectorRepository;
import com.shrralis.ssdemo1.repository.ModuleRepository;
import com.shrralis.ssdemo1.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService {

	private final ModuleRepository moduleRepository;
	private final ConnectorRepository connectorRepository;

	@Autowired
	public TeacherService(ModuleRepository moduleRepository, ConnectorRepository connectorRepository) {
		this.connectorRepository = connectorRepository;
		this.moduleRepository = moduleRepository;
	}

	public List<Module> getAllModules() {
		return moduleRepository.findAll();
	}

	public List<SubjectModuleDTO> getAllSubjectsWithModules() {
		List<SubjectModuleDTO> result = new ArrayList<>();

		List<Connector> subjects = connectorRepository.findAll();
		List<Module> modules = moduleRepository.findAll();

		int countModules = 0;
		int maxScore = 0;
		for (Connector connector : subjects) {
			SubjectModuleDTO subjectModuleDTO = new SubjectModuleDTO();

			subjectModuleDTO.setSubject(connector.getSubject());
			subjectModuleDTO.setTeacher(connector.getTeacher());
			subjectModuleDTO.setGroup(connector.getGroup());

			for (Module module : modules) {

				if (connector.getSubject().getId() == module.getConnector().getSubject().getId()) {
					countModules++;
					maxScore += module.getMaxScores();
				}
			}

			subjectModuleDTO.setCountModules(countModules);
			subjectModuleDTO.setMaxScore(maxScore);
			if (maxScore != 0) {
				subjectModuleDTO.setExamScore(100 - maxScore);
			} else {
				subjectModuleDTO.setExamScore(0);
			}

			countModules = 0;
			maxScore = 0;

			result.add(subjectModuleDTO);
		}

		return result;
	}

}
