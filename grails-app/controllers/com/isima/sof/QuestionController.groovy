package com.isima.sof

import grails.plugins.springsecurity.Secured;

import org.springframework.dao.DataIntegrityViolationException

import com.sun.org.apache.xpath.internal.operations.String;

class QuestionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def questionService
	def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }
	
	/* Our three list actions render to the "list" view but with different questionInstance lists (one for each tab) */

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [questionInstanceList: Question.findAll(params, sort: 'creationDate', order: 'desc'), 
		 questionInstanceListByViews: Question.findAll(params, sort: 'viewsNb', order: 'desc'),
		 questionInstanceListByVotes: Question.findAll(params, sort: 'votesNb', order: 'desc'),
	     questionInstanceTotal: Question.count()]
    }
	
	def listUnanswered(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def questionInstanceList = Question.findAll(params, sort: 'creationDate', order: 'desc')
		def questionInstanceListByViews = Question.findAll(params, sort: 'viewsNb', order: 'desc')
		def questionInstanceListByVotes = Question.findAll(params, sort: 'votesNb', order: 'desc')
		questionInstanceList = questionInstanceList.findAll { e -> e.answers.size() == 0 }
		questionInstanceListByViews = questionInstanceListByViews.findAll { e -> e.answers.size() == 0 }
		questionInstanceListByVotes = questionInstanceListByVotes.findAll { e -> e.answers.size() == 0 }
		
		render(view: "list", 
			   model: [questionInstanceList: questionInstanceList, 
				       questionInstanceListByViews: questionInstanceListByViews,
				       questionInstanceListByVotes: questionInstanceListByVotes,
				       questionInstanceTotal: questionInstanceList.size()])
	}
	
	def listByTag(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def questionInstanceList = questionService.listByTag( Long.parseLong(params.id), 'creationDate' )
		def questionInstanceListByViews = questionService.listByTag( Long.parseLong(params.id), 'viewsNb' )
		def questionInstanceListByVotes = questionService.listByTag( Long.parseLong(params.id), 'votesNb' )
		
		render(view: "list", 
			   model: [questionInstanceList: questionInstanceList, 
				       questionInstanceListByViews: questionInstanceListByViews, 
					   questionInstanceListByVotes: questionInstanceListByVotes,
				       questionInstanceTotal: questionInstanceList.size()])
	}
	
    def create() {
		if(! springSecurityService.isLoggedIn()) {
			redirect(controller: "login", action: "auth")
		}
        
		[questionInstance: new Question(params)]
    }

    def save() {
        def questionInstance = new Question(params)
		
		// Retrieve connected user
		def user = springSecurityService.currentUser
		questionInstance.user = user
		questionInstance.creationDate = new Date()
		
        if (!questionInstance.save(flush: true)) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }

        flash.message = message(code: 'question.created.message')
        redirect(action: "show", id: questionInstance.id)
    }

    def show(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

		questionService.incrementViewsNumber(questionInstance)
		
        [questionInstance: questionInstance]
    }
}
