/**
 * 
 */
package com.aelion.suivi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * @author Aelion
 * POJO: Plain Old Java Object
 */

@Entity
@Table(name="poe")
public class POEEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private Date beginDate;
	private Date endDate;
	
	@ManyToOne()                             //plusieurs POE pour un type
	private POETypeEntity poeType;
	

	@ManyToMany()
    @JoinTable(
    		name = "interns_to_poes",
    		joinColumns = @JoinColumn(name = "poe_id"),
    		inverseJoinColumns = @JoinColumn(name = "intern_id")
    		)
    private List<InternEntity> interns = new ArrayList<>();
	
	
	
	/**
	 * @return the interns
	 */
	public List<InternEntity> getInterns() {
		return interns;
	}


	/**
	 * @param interns the interns to set
	 */
	public void setInterns(List<InternEntity> interns) {
		this.interns = interns;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	//POE ENTITY GETTERS AND SETTERS

	/**
	 * @return the poeType
	 */
	public POETypeEntity getPoeType() {
		return poeType;
	}


	/**
	 * @param poeType the poeType to set
	 */
	public void setPoeType(POETypeEntity poeType) {
		this.poeType = poeType;
	}
	
	
}
