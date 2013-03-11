package com.isima.sof

class CommentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def save() {
		def commentInstance = new Comment(params)
		def postInstance = Post.get(params.postId)
		def questionInstance = Post.get(params.questionId)  // For redirection
		
		commentInstance.post = postInstance
		commentInstance.content = params.commentContent
		commentInstance.creationDate = new Date()
		
		if (!commentInstance.save(flush: true)) {
            flash.message ="Comment cannot be created"
			redirect(controller: "question", action: "show", id: questionInstance.id)
			return
		}

		// a changer aussi ci dessus
		redirect(controller: "question", action: "show", id: questionInstance.id)
	}
}
