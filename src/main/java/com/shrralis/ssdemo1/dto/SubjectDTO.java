package com.shrralis.ssdemo1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

	private String subjectName;

	private int groupId;

	private int teacherId;


}
