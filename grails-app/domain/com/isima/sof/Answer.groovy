package com.isima.sof

class Answer extends Post {

	static belongsTo = [question:Question]
	
    static constraints = {
    }
}
