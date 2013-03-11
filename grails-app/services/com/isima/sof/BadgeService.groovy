package com.isima.sof

import com.isima.sof.User;

class BadgeService {

    def updateUserBadges(User userInstance) {
		// Add a badge to a user if he has enough reputation points
		for(badgeInstance in Badge.getAll()) {
			if(   !userInstance.badges.contains(badgeInstance) 
			   && badgeInstance.reputationNeeded <= userInstance.reputation) {
				userInstance.badges.add(badgeInstance)
			}
		}		
		userInstance.save(flush: true)
	}
}
