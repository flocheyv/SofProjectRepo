package com.isima.sof

class Comment {

	String content
	Date creationDate
	
	static belongsTo = [post:Post]
	
    static constraints = {
    }
}
