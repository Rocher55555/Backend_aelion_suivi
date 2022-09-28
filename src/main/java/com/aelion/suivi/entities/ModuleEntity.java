/**
 * 
 */
package com.aelion.suivi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aelion
 *
 */
@Entity
@Table(name="module")
public class ModuleEntity {
	
	@Id 	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, nullable=false, length=150)
	private String title;
	
	private String description;

}
