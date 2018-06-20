package com.shrralis.ssdemo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.shrralis.ssdemo1.entity.ModuleResult.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleResult {

	public static final String TABLE_NAME = "modules_results";
	public static final String ID_COLUMN_NAME = "id";
	public static final String MODULE_COLUMN_NAME = "module_id";
	public static final String STUDENT_COLUMN_NAME = "student_id";
	public static final String SCORE_COLUMN_NAME = "score";

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modules_results_seq_gen")
	@SequenceGenerator(name = "modules_results_seq_gen", sequenceName = "modules_results_id_seq", allocationSize = 1)
	@Column(name = ID_COLUMN_NAME, nullable = false, unique = true)
	private Integer id;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = MODULE_COLUMN_NAME, nullable = false)
	private Module module;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = STUDENT_COLUMN_NAME, nullable = false)
	private Student student;

	@NotNull
	@NotBlank
	@Column(name = SCORE_COLUMN_NAME, nullable = false)
	private Integer score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
