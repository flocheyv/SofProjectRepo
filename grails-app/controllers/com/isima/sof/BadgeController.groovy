package com.isima.sof

class BadgeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
		
    def list() {
        [badgeInstanceList: Badge.getAll()]
    }
}
