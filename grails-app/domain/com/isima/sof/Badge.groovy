package com.isima.sof

enum BadgeType {
	BRONZE, SILVER, GOLD
}

class Badge {

	String name
	String description
	int reputationNeeded
	BadgeType type
	
	// Relation *..* User > Badge
	static hasMany = [users:User]
	static belongsTo = User
	
    static constraints = {
    }
	
	
}
