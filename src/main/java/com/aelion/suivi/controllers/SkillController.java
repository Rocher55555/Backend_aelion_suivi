/**
 * 
 */
package com.aelion.suivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.services.SkillService;

/**
 * @author Aelion
 *
 */

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	

}
