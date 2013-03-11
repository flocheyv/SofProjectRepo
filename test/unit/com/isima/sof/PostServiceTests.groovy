package com.isima.sof



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PostService)
@Mock([Question, Badge, User])
class PostServiceTests {

	User testUser
	
	@Before
	void setUp() {
		testUser = new User(username: 'me', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 8)
					.addToBadges(new Badge(name: 'Noob', description: 'Badge which is very easy to earn.', reputationNeeded: 1))
	}
	
    void testIncrementation() {
		Date date = new Date()
		Question q4 = new Question(title: 'What is ADO.NET ?', votesNb: 7,
									content: "Please help me I'm lost", creationDate: date)
		//q4.save()
		
		testUser.addToPosts(q4).save()
		
		assertEquals 7, q4.votesNb
		assertEquals 1, testUser.badges.size()
		
		/*
		service.incVotes(q4.id)  // Cannot test id is never set in unit tests
		         				 // No signature of method: com.isima.sof.Post.get() is applicable for argument types: () values: []
		
		assertEquals 8, q4.votesNb
		assertEquals 2, testUser.badges.size()
		*/
    }
	/*
	void testDecrementation() {
		def testUser = new User(username: 'me', enabled: true, password: 'password', website: 'www.lol.fr', location: 'Clermont-Ferrand', age: 40, reputation: 8)
		testUser.save(flush: true)
		
		Question q1 = new Question(title: 'How can I develop in Python and on which environment ?', viewsNb: 0, votesNb: 7,
								   content: "Please help me I'm lost", creationDate: new Date())
		q1.save()
		
		testUser.addToPosts(q1).save()
		
		assertEquals q1.votesNb, 7
		postService.decVotes(q1.id)
		assertEquals q1.votesNb, 6
	}
	*/
}
