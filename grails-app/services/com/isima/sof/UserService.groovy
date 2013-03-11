package com.isima.sof

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class UserService {

	def springSecurityService
	
	/**
	 * If the user instance correspond to the connected user OR if the connected user is an administrator
	 * @param userInstance
	 * @return
	 */
    boolean isCurrentUserOrAdmin(userInstance) {
		if(userInstance != null && springSecurityService.isLoggedIn()
		   && (SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN") || userInstance?.id == springSecurityService.getCurrentUser().id))	{
			return true
		} else {
			return false
		}
	}
	
	def incReputation (userInstance) {
		userInstance.reputation++;
	}
}
