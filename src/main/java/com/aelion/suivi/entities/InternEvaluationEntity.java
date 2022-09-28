package com.aelion.suivi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="evaluation_intern")
public class InternEvaluationEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private int note;
	
	private String comment;
	
	@ManyToOne()	                  //plusieurs eval pour un intern
	@JoinColumn(name="intern_id")
	private InternEntity intern;
	
	@ManyToOne()                     // plusieurs skil pour une eval
	@JoinColumn(name="skill_id")
	private SkillEntity skill;
}
