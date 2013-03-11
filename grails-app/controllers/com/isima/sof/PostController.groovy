package com.isima.sof

import org.springframework.dao.DataIntegrityViolationException

class PostController {

    def postService
	def springSecurityService
	
	def incVotes(){
		postService.incVotes(params.id.toLong())
		redirect(controller: "question", action: "show", id:params.questionId.toLong())  // toLong because come from tagLib
	}
	
	def decVotes(){
		postService.decVotes(params.id.toLong())
		redirect(controller: "question", action: "show", id:params.questionId.toLong())
	}
	
	def edit() {
		def postInstance = Post.get(params.postId)
		
		// Should nevers happen because edit button is hidden if necessary but it could happen
		// if an unauthorized user types directly a URL to edit !
		if(!springSecurityService.isLoggedIn()) {
			flash.message = message(code: 'not.authorized')
			redirect(controller: "question", action: "show", id: params.questionId)
			return
		}

		// It is in question folder but it is for edit a post actually
		render(view: "/question/edit", model: [postInstance: postInstance, questionId:params.questionId])
	}

	def update(Long id, Long version) {
		def postInstance = Post.get(params.postId)
		if (!postInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
			redirect(controller: "question", action: "show", id: params.questionId)
			return
		}

		if (version != null) {
			if (postInstance.version > version) {
				postInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'question.label', default: 'Question')] as Object[],
						  "Another user has updated this Question while you were editing")
				render(view: "/question/edit", model: [questionInstance: postInstance])
				return
			}
		}

		postInstance.properties = params

		if (!postInstance.save(flush: true)) {
			render(view: "/question/edit", model: [questionInstance: postInstance])
			return
		}

		flash.message = message(code: 'question.updated.message')
		redirect(controller: "question", action: "show", id: params.questionId)
	}
	
	// questionId pour une bonne redirection
	def delete() {
		def postInstance = Post.get(params.postId)

		if (!postInstance) {
			flash.message = message(code: 'default.not.found.message')
			redirect(controller: "question", action: "show", id: params.questionId)
			return
		}
		
		try {
			postInstance.delete(flush: true)
			flash.message = message(code: 'deleted.message')
			if(params.questionId == params.postId) {
				// If we just delete a question
				redirect(controller: "question", action: "list")
			}
			else {
				// Otherwise we stay on our question page
				redirect(controller: "question", action: "show", id: params.questionId)
			}
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', default: 'Deletion issue')
			redirect(controller: "question", action: "show", id: params.questionId)
		}
	}
}
