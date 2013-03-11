package com.isima.sof

class PostService {

	def userService
	def badgeService
	def springSecurityService
	
    def incVotes(Long id) {
		def post = Post.get(id)
		def idCurrentUser = springSecurityService.getCurrentUser().id
		// Should be always true because we hide the link to this service (in our gsp) if the user has voted
		// But it's a security if the user types direclty the url to this service
		if( ! isUserIdHasVotedUp(post, idCurrentUser) ) {
			
			// If he cancels his downvote for an upvote, we delete his id from vote 
			// otherwise incVotes won't work
			if(isUserIdHasVotedDown(post, idCurrentUser))
				cancelVote(post, idCurrentUser);
			else {
				// If it's the first upvote we inscrease reputation of the asker
				
				// User earns one reputation point
				userService.incReputation(post.user)
				
				// Check if the user deserve a new badge
				badgeService.updateUserBadges(post.user)
			}
			
			post.votesNb++
		
			// Save the user id which juste come to vote
			post.addToVotes( new Vote(userIdHavingVoted: idCurrentUser, value: 1) )
		}
	}
	
	def decVotes(Long id){
		def post = Post.get(id)
		
		def idCurrentUser = springSecurityService.getCurrentUser().id
		// Should be always true because we hide the link to this service (in our gsp) if the user has voted
		// But it's a security if the user types direclty the url to this service
		if( ! isUserIdHasVotedDown(post, idCurrentUser) ) {
			
			// If he cancels his upvote for a downvote, we delete his id from vote
			// otherwise decVotes won't work
			if(isUserIdHasVotedUp(post, idCurrentUser))
				cancelVote(post, idCurrentUser);
			
			post.votesNb--
		
			// Save the user id which juste come to vote
			post.addToVotes( new Vote(userIdHavingVoted: idCurrentUser, value: 0) )
		
			// Cannot loose reputation point, so don't need to check badges
		}
	}
	
	def cancelVote(Post post, Long userId) {
		def v = Vote.createCriteria()
		Vote vote = v.get {
			eq('userIdHavingVoted', userId)
			eq('post.id', post.id)
		}
		post.removeFromVotes(vote)
		vote.delete()
	}

	
	boolean isUserIdHasVotedUp(Post post, Long userId) {
		boolean hasVotedUp = false;
		
		int upValue = 1
		def v = Vote.createCriteria()
		Vote potentialVote = v.get {
			eq('userIdHavingVoted', userId)
			eq('value', upValue)
			eq('post.id', post.id)
		}
		
		if(potentialVote?.userIdHavingVoted == userId && potentialVote?.value == upValue && potentialVote?.post.id == post.id)
			hasVotedUp = true;
			
		return hasVotedUp;
	}
	
	boolean isUserIdHasVotedDown(Post post, Long userId) {
		boolean hasVotedDown = false;
		
		int downValue = 0
		def v = Vote.createCriteria()
		Vote potentialVote = v.get {
			eq('userIdHavingVoted', userId)
			eq('value', downValue)
			eq('post.id', post.id)
		}
		
		if(potentialVote?.userIdHavingVoted == userId && potentialVote?.value == downValue && potentialVote?.post.id == post.id)
			hasVotedDown = true;
			
		return hasVotedDown;
	}
}
