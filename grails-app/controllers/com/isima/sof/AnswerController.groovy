package com.isima.sof

class AnswerController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService
	
    def save() {
		def answerInstance = new Answer(params)
		def questionInstance = Question.get(params.questionId)
		
		answerInstance.question = questionInstance
		answerInstance.user = springSecurityService.currentUser
		answerInstance.content = params.answerContent
		answerInstance.creationDate = new Date()
		
		if (!answerInstance.save(flush: true)) {
            flash.message ="Answer cannot be created"
			redirect(controller: "question", action: "show", id: questionInstance.id)
			return
		}

		redirect(controller: "question", action: "show", id: questionInstance.id)
	}
}
