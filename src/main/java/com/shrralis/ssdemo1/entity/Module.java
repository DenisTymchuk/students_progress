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

import static com.shrralis.ssdemo1.entity.Module.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Module {

	public static final String TABLE_NAME = "modules";
	public static final String ID_COLUMN_NAME = "id";
	public static final String SUBJECT_COLUMN_NAME = "subject_id";
	public static final String CONNECTOR_COLUMN_NAME = "connector_id";
	public static final String MAX_SCORE_COLUMN_NAME = "max_scores";

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modules_seq_gen")
	@SequenceGenerator(name = "modules_seq_gen", sequenceName = "modules_id_seq", allocationSize = 1)
	@Column(name = ID_COLUMN_NAME, nullable = false, unique = true)
	private Integer id;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = SUBJECT_COLUMN_NAME, nullable = false)
	private Subject subject;

	@NotNull
	@NotBlank
	@Column(name = CONNECTOR_COLUMN_NAME, nullable = false)
	private Connector connector;

	@NotNull
	@NotBlank
	@Column(name = MAX_SCORE_COLUMN_NAME, nullable = false)
	private Integer maxScores;
}
