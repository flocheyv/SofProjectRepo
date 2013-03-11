package com.isima.sof

class VoteTagLib {

	static namespace = "vv"
	
	def springSecurityService
	def postService
	
	def voteUp = {attrs, body ->
		
		if(springSecurityService.isLoggedIn()) {
			
			def postInstance = attrs.postInstance
			def questionInstance = attrs.questionInstance  // For redirection (question/show), if postInstance is an answer we need that
			def idCurrentUser = springSecurityService.getCurrentUser().id
			
			// If user has not vote up
		    if(!postService.isUserIdHasVotedUp(postInstance, idCurrentUser)) {

				// Display grey arrow with a link to upvote
				out << g.remoteLink([controller: 'post', action: 'incVotes', params:[id: postInstance.id, questionId: questionInstance.id], update: 'votes' + postInstance.id]) { 
					   g.img(dir: 'images', file: 'up.png') 
				}
			}
		    else {
				// Display yellow arrow
				out << g.img(dir: 'images', file: 'up-voted.png')
		    }
		}
		else {
			// Redirect to login page
			out << g.remoteLink([controller: 'login', action: 'auth']) {
				g.img(dir: 'images', file: 'up.png')
		    }
		}
	}
	
	def voteDown = {attrs, body ->
		
		if(springSecurityService.isLoggedIn()) {
			
			def postInstance = attrs.postInstance
			def questionInstance = attrs.questionInstance
			def idCurrentUser = springSecurityService.getCurrentUser().id
			
			// If user has not vote down
			if(!postService.isUserIdHasVotedDown(postInstance, idCurrentUser)) {
					
				// Idem with downvote
				out << g.remoteLink([controller: 'post', action: 'decVotes', params:[id: postInstance.id, questionId: questionInstance.id], update: 'votes' + postInstance.id]) {
					   g.img(dir: 'images', file: 'down.png')
				}
			}
			else {
				// Display yellow arrow
				out << g.img(dir: 'images', file: 'down-voted.png')
			}
		}
		else {
			// Redirect to login page
			out << g.remoteLink([controller: 'login', action: 'auth']) {
				g.img(dir: 'images', file: 'down.png')
		 }
		}
	}
}
