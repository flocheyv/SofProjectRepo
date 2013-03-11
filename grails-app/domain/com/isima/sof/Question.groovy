package com.isima.sof

import java.util.ArrayList;

class Question extends Post {

	String title
	int viewsNb
	
	// Relation *..* Question > Tag || Relation 1..* Question > Answer
	static hasMany = [tags: Tag, answers:Answer]
	
    static constraints = {
		title blank: false, nullable: false
		//tags nullable: false, minSize: 1, maxSize: 10
    }
	
	static mapping = {
		answers sort: "votesNb", order: "desc"  // Order answers by number of votes (descending)
	}
}
