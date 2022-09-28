package com.aelion.suivi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Aelion
 *
 */

@Entity
@Table(name="poe_type")
public class POETypeEntity {
	@Id                                                   //la colonne cle primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)   //strategie en auto increment
	private int id;
	
	@Column(length=50)                                     /// ref à a bd (créera un varchar de 50char)
	private String title;
	




	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	

}
