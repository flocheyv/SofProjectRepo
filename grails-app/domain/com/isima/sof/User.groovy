package com.isima.sof

import javax.imageio.*
import javax.imageio.spi.*
import org.codehaus.groovy.grails.web.context.ServletContextHolder

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String website
	String location
	int age
	int reputation
	byte[] avatar
	String avatarType
	
	// Relation 1..* Question > Answer || Relation *..* User > Badge
	static hasMany = [posts:Post, badges:Badge]

	static constraints = {
		username blank: false, unique: true
		password blank: false
		website nullable: true
		location nullable: true
		age nullable: true
		reputation nullable: true
		avatar nullable:true, maxSize: 3000000 /* 3 Mo */
		avatarType nullable:true
	}

	static mapping = {
		password column: '`password`'
		posts sort: "creationDate"
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
		
		if(!avatar) {
			// Let's put a default avatar
			enabled = true;
			def servletContext = ServletContextHolder.servletContext
			def img = ImageIO.read( servletContext.getResourceAsStream("/images/default_avatar.jpg") );
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( img, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			avatar = imageInByte
			avatarType = "jpg"
		}
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
