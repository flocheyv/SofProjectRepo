package com.isima.sof

class Vote {

	Long userIdHavingVoted;
	int value;
	static belongsTo = [post:Post]
	
    static constraints = {
    }
}
