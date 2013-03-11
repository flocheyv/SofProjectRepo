package com.isima.sof

import java.util.ArrayList;
import java.util.Date;

abstract class Post {

	String content
	Date creationDate
	int votesNb
	
	static hasMany = [comments: Comment, votes: Vote]
	static belongsTo = [user:User]
	
    static constraints = {
    }
	
	static mapping = {
		comments sort: "creationDate"
	}
}
