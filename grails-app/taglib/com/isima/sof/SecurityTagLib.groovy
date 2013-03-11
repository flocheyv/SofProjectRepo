package com.isima.sof


class SecurityTagLib {

	static namespace = "ss"
	
	def userService	
	
	// Adapted from http://www.bobbywarner.com/2011/04/
	
	/**
	 * Renders the body if user has authority (if it's his own question or of he's adminstrator)
	 *
	 * @attr value REQUIRED the field value
	 */
	def ifUserModifyAuth = { attrs, body ->
		// User instance corresponding to the consulted profile
		def userInstance = assertAttribute("value", attrs, "ifUserModifyAuth")
		// If this user instance correspond to the connected user OR if the connected user is an administrator
		if (userService.isCurrentUserOrAdmin(userInstance)) {
			out << body()
		}
	}

	protected assertAttribute(String name, attrs, String tag) {
		if (!attrs.containsKey(name)) {
			throwTagError "Tag [$tag] is missing required attribute [$name]"
		}
		attrs.remove name
	}
}
