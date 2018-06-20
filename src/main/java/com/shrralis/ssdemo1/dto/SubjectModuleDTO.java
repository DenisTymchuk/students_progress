package com.shrralis.ssdemo1.dto;

import com.shrralis.ssdemo1.entity.Group;
import com.shrralis.ssdemo1.entity.Subject;
import com.shrralis.ssdemo1.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectModuleDTO {

	private Subject subject;

	private Teacher teacher;

	private Group group;

	private Integer countModules;

	private Integer maxScore;

	private Integer examScore;

}
