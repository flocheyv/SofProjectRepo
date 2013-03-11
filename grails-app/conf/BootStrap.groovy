import com.isima.sof.Answer
import com.isima.sof.Post
import com.isima.sof.Question
import com.isima.sof.Role
import com.isima.sof.Tag
import com.isima.sof.User
import com.isima.sof.UserRole
import com.isima.sof.BadgeType
import com.isima.sof.Badge
import java.text.SimpleDateFormat
import javax.imageio.ImageIO

class BootStrap {

    def init = { servletContext ->
		// Test our Spring security plugin
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		Badge noob = new Badge(name: 'Noob', description: 'badge.noob.desc', reputationNeeded: 1, type: BadgeType.BRONZE).save()
		Badge student = new Badge(name: 'Student', description: 'badge.student.desc', reputationNeeded: 10, type: BadgeType.SILVER).save()
		Badge mvp = new Badge(name: 'MVP', description: 'badge.mvp.desc', reputationNeeded: 10000, type: BadgeType.GOLD).save()
		def testUser = new User(username: 'me', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 2)
						.addToBadges(noob)
		def testUser1 = new User(username: 'me1', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 3)
						.addToBadges(noob)
		def testUser2 = new User(username: 'me2', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 4)
						.addToBadges(noob)
		def testUser3 = new User(username: 'me3', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 5)
						.addToBadges(noob)
		def testUser4 = new User(username: 'me4', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 6)
						.addToBadges(noob)
		def testUser5 = new User(username: 'me5', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 7)
						.addToBadges(noob)
		def floUser = new User(username: 'flo', enabled: true, password: 'admin', website: 'www.grails.org', location: 'Le Puy', age: 23, reputation: 20)
						.addToBadges(noob)
						.addToBadges(student)
		User ronanUser = new User(username: 'ronan', enabled: true, password: 'admin', website: 'www.bonjourmadame.fr', location: 'La Ferthe', age: 23, reputation: 10200)
						.addToBadges(noob)
						.addToBadges(student)
						.addToBadges(mvp)
		
		/* Mock avatars */
		def img = ImageIO.read( servletContext.getResourceAsStream("/images/ronan.jpg") );
		def img2 = ImageIO.read( servletContext.getResourceAsStream("/images/flo.jpg") );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		ImageIO.write( img, "jpg", baos );
		ImageIO.write( img2, "jpg", baos2 );
		baos.flush(); baos2.flush();
		byte[] imageInByte = baos.toByteArray();
		byte[] imageInByte2 = baos2.toByteArray();
		baos.close(); baos2.close();
		ronanUser.avatar = imageInByte
		ronanUser.avatarType = "jpg"
		floUser.avatar = imageInByte2
		floUser.avatarType = "jpg"
		
		testUser.save(flush: true)
		testUser1.save(flush: true)
		testUser2.save(flush: true)
		testUser3.save(flush: true)
		testUser4.save(flush: true)
		testUser5.save(flush: true)
		floUser.save(flush: true)
		ronanUser.save(flush: true)
		UserRole.create testUser, userRole, true
		UserRole.create testUser1, userRole, true
		UserRole.create testUser2, userRole, true
		UserRole.create testUser3, userRole, true
		UserRole.create testUser4, userRole, true
		UserRole.create testUser5, userRole, true
		UserRole.create floUser, adminRole, true
		UserRole.create ronanUser, adminRole, true
		assert User.count() == 8
		assert Role.count() == 2
		assert UserRole.count() == 8
		
		// Mock datas
		// Tags description should all be in properties file in a real application (as we did for badges)
		Tag jee = new Tag(name: 'java-ee', description: 'Java Enterprise Edition is a specification defining a collection of Java-based technologies and how they interoperate. Java EE specifies server and client architectures and uses profiles to define technology sets.').save()
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Question q0 = new Question(title: 'What is an ORM ? Which one is the best to start ?', viewsNb: 10, votesNb: 10, 
		                           content: "Please help me I'm lost", creationDate: formatter.parse("2013/01/01"))
						.addToTags(name: 'hibernate', description: 'Hibernate is a collection of related projects enabling developers to utilize POJO-style domain models in their applications in ways extending well beyond Object/Relational Mapping. ')
						.addToTags(name: 'gorm', description: 'GORM is Grails object relational mapping (ORM) implementation.')
						.addToTags(name: 'spring-orm', description: 'ORM of Spring.')
						.addToTags(jee)
						
		q0.save()   // Do not do the save right after the "new" otherwise addToPosts won't work
		Question q1 = new Question(title: 'How can I develop in Python and on which environment ?', viewsNb: 0, votesNb: 7,
								   content: "Please help me I'm lost", creationDate: new Date())
						.addToTags(name: 'python', description: 'Python is a dynamically and strongly typed programming language whose design philosophy emphasizes code readability. Two significantly different versions of Python (2 and 3) are in use. Please mention the version that you are using.')
						.addToTags(name: 'compilation', description: 'Compilation is the process of producing information, typically a program to be executed by a computer, from a source (or sources) typically being text in a computer programming language.')
		q1.save()
	    Question q2 = new Question(title: 'How to use lambda-expressions in Java or C# ?', viewsNb: 3, votesNb: 2,
								   content: "Please help me I'm lost", creationDate: formatter.parse("2012/11/29"))
						.addToTags(name: 'lambda', description: 'Anonymous functions or closures in programming languages such as Lisp, C#, C++ or Python. (Also, lambda expression.)')
						.addToTags(name: 'java', description: 'Java is an object-oriented language and runtime environment (JRE). Java programs are platform independent because the program execution is handled by a Virtual Machine called the Java VM or JVM.')
		q2.save()
	    Question q3 = new Question(title: 'How does Java EE works ? Any examples ?', viewsNb: 4,
								   content: "Please help me I'm lost", creationDate: formatter.parse("2013/03/01"))
						.addToTags(name: 'jsf', description: 'JavaServer Faces (JSF) is a model-view-presenter framework used to create web applications. Using the standard components and render kit, stateful HTML views can be defined using JSP or Facelets tags and wired to model data and application logic.')
						.addToTags(jee)
						.addToTags(name: 'tomcat', description: 'Apache Tomcat is an open source Servlet Container and is developed by the Apache Software Foundation (ASF).')
						.addToTags(name: 'spring', description: 'The Spring Framework is an open source application framework for the Java platform, designed to make Java SE/Java EE technologies easier to use.')
						.addToTags(name: 'mvc', description: 'Model-View-Controller (MVC) is an architectural pattern used in software engineering. Use the more specific [asp.net-mvc] tag (ASP.NET MVC) instead, if applicable.')
		q3.save()
		Answer a1 = new Answer(content: "You're insain", creationDate: formatter.parse("2013/02/03"), votesNb: 5)
		Answer a2 = new Answer(content: "A website", creationDate: formatter.parse("2013/02/25"), votesNb: 10)
		Answer a3 = new Answer(content: "No it's easy. Assholes!", creationDate: formatter.parse("2013/02/14"), votesNb: -20)
	    Question q4 = new Question(title: 'What is ADO.NET ?', viewsNb: 12,
								   content: "Please help me I'm lost", creationDate: new Date())
						.addToAnswers(a1)
						.addToAnswers(a2)
						.addToAnswers(a3)
						.addToTags(name: 'ado-net', description: 'A set of computer software components that programmers can use to access data and data services.')
		q4.save()
	    Question q5 = new Question(title: 'I do not know anything about web service. I need advice.', viewsNb: 18,
								   content: "Please help me I'm lost", creationDate: new Date())
						.addToTags(name: 'web-services', description: 'A "web service" is a software system designed to support interoperable machine-to-machine interaction over the World Wide Web.')
		q5.save()
	    Question q6 = new Question(title: 'How do you choose a design pattern according to the context ?', viewsNb: 100,
								   content: "I have no idea how to choose my design pattern for my application", creationDate: new Date())
						.addToTags(name: 'mvc', description: 'Model-View-Controller (MVC) is an architectural pattern used in software engineering. Use the more specific [asp.net-mvc] tag (ASP.NET MVC) instead, if applicable.')
						.addToTags(name: 'design-patterns', description: 'A design pattern is a general reusable solution to a commonly occurring problem in software design.')
						.addToTags(name: 'gof', description: 'Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides, the authors of the book "Design Patterns: Elements of Reusable Object-Oriented Software" are often referred to as the Gang of Four, or GoF.')
						.addToTags(name: 'factory-pattern', description: 'Is a creational pattern, provide an interface for creating an object, but let sub classes decide which class to instantiate.')
		q6.save()
	    Question q7 = new Question(title: 'Could you tell me more about AJAX', viewsNb: 10,
								   content: "What the fuck is AJAX ? Is that relied with the window cleaner product ?", creationDate: new Date())
						.addToTags(name: 'ajax', description: 'AJAX (Asynchronous JavaScript and XML) is a technique for creating seamless interactive websites via asynchronous data exchange between client and server.')
		q7.save()
	    Question q8 = new Question(title: 'How many applications servers are there ? Are they equivalent ?', viewsNb: 11569,
								   content: "Please help me I'm lost", creationDate: formatter.parse("2013/02/11"))
						.addToTags(name: 'websphere', description: 'IBM WebSphere Application Server, a software application server, is the flagship product within IBMs WebSphere brand. WebSphere Application Server is built using open standards such as Java EE, XML, and Web Services.')
						.addToTags(name: 'weblogic', description: 'WebLogic Server is a Java EE application server suite from Oracle. It is part of Oracles Fusion Middleware product.')
						.addToTags(name: 'jboss', description: 'JBoss Application Server (JBoss AS) is a free software/open-source Java EE-based, Cross-platform Application Server.')
						.addToTags(name: 'glassfish', description: 'GlassFish is a Java EE 6 application server.')
						.addToTags(jee)
		q8.save()
	    Question q9 = new Question(title: 'What do you know about grails and groovy ?', viewsNb: 1000000,
								   content: "Please help me I'm lost", creationDate: formatter.parse("2013/02/25"))
						.addToTags(name: 'grails', description: 'Grails is a web application framework that uses Groovy and Java. The framework is oriented around high-productivity application development, and uses common Java technologies such as Hibernate and Spring.')
						.addToTags(name: 'groovy', description: 'Groovy is an object-oriented programming language for the Java platform. It is a dynamic language with features similar to those of Python, Ruby, Perl, and Smalltalk. It can be used as a scripting language for the Java Platform.')
		q9.save()
	    Question q10 = new Question(title: 'How to create a programming langage ?', viewsNb: 100000000,
								    content: "Please help me I'm lost", creationDate: formatter.parse("2012/12/25"))
						.addToTags(name: 'compiler', description: 'A compiler is a program which translates one language into another. The tag [compiler] should be applied to questions concerning the programming of compilers or for questions about the detailed inner workings of compilers.')
						.addToTags(name: 'syntax', description: 'In computer science, the syntax of a programming language is the set of rules that define the combinations of symbols that are considered to be correctly structured programs in that language.')
						.addToTags(name: 'linker', description: 'The linker is part of the toolchain for producing executables from source code written in compiled programming languages. It takes compiled object code in multiple files and produces a single, "linked", executable file from them.')
		q10.save()
								
		testUser.addToPosts(q0).save()
		floUser.addToPosts(q1).save()
		ronanUser.addToPosts(q2).save()
		testUser.addToPosts(q3).save()
		floUser.addToPosts(q4).save()
		ronanUser.addToPosts(q5).save()
		testUser.addToPosts(q6).save()
		floUser.addToPosts(q7).save()
		ronanUser.addToPosts(q8).save()
		testUser.addToPosts(q9).save()
		testUser.addToPosts(q10).save()
		
		testUser.addToPosts(a1).save()
		floUser.addToPosts(a2).save()
		ronanUser.addToPosts(a3).save()
		
		// Some tags could be never associated to a question
		Tag t1 = new Tag(name: 'assembly', description:'Assembly language (asm) programming questions. Also specify the processor or instruction set your question is related to as well as what assembler you are using. **NOTE**: For .NET assemblies, use the tag [.net-assembly] instead.').save()
		
    }
    def destroy = {
    }
}
