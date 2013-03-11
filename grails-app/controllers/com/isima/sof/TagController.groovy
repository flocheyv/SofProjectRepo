package com.isima.sof

class TagController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
		
    def list() {
        [tagInstanceList: Tag.getAll()]
    }
}
