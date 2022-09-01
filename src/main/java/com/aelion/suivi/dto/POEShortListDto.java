/**
 * 
 */
package com.aelion.suivi.dto;

import com.aelion.suivi.entities.POEEntity;

/**
 * @author Aelion
 * DTO des POES
 */
public class POEShortListDto {
	public int id;
	public String name;
	
	public POEShortListDto transform(POEEntity poe) {
		this.id = poe.getId();
		this.name = poe.getName(); 
		
		return this; // se retourne lui_même après modif
	}
}

