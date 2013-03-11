package com.isima.sof

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def userService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'user.created.message', args: [userInstance.username])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

		// Retrieve questions of our user
		def questions = Question.getAll().findAll { it.user.id == userInstance.id}
        [userInstance: userInstance, questionInstanceList:questions ]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
		
		// Should nevers happen because edit button is hidden if necessary but it could happen
		// if an unauthorized user types directly a URL to edit !
		if(!userService.isCurrentUserOrAdmin(userInstance)) {
			flash.message = message(code: 'not.authorized')
			redirect(action: "list")
			return
		}
		
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

		// Do not delete current avatar
        userInstance.username = params.username
		userInstance.website = params.website
		userInstance.location = params.location
		if(params.age != "")
			userInstance.age = params.age.toInteger()
		
		// http://grails.org/Simple+Avatar+Uploader
		// Get the avatar file from the multi-part request
		MultipartFile f = request.getFile('avatar')
	  
		// List of OK mime-types
		def okcontents = ['image/png', 'image/jpeg', 'image/gif']
		if(!f.isEmpty())
		{
			if (! okcontents.contains(f.getContentType())) {
			  flash.message = "Avatar must be one of: ${okcontents}"
			  render(view:'edit', model:[userInstance: userInstance])
			  return;
			}
			
			// Save the image and mime type
			userInstance.avatar = f.getBytes()
			userInstance.avatarType = f.getContentType()
		  
			// Validation works, will check if the image is too big
			if (!userInstance.save()) {
				flash.message = "Image too large"
				render(view:'edit', model:[userInstance: userInstance])
			  return;
			}
		}

        flash.message = message(code: 'user.updated.message')
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def retrieveAvatar = {
		def avatarUser = User.get(params.id)
		if (!avatarUser || !avatarUser.avatar || !avatarUser.avatarType) {
		  response.sendError(404)
		  return;
		}
		response.setContentType(avatarUser.avatarType)
		response.setContentLength(avatarUser.avatar.size())
		OutputStream out = response.getOutputStream();
		out.write(avatarUser.avatar);
		out.close();
	}
}
