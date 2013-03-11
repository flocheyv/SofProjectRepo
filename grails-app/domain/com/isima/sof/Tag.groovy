package com.isima.sof

class Tag {

	String name
	String description
	
	static hasMany = [questions:Question]
	static belongsTo = [Question]
	
    static constraints = {
    }
	
	static mapping = {
		sort 'name'
	}
}
