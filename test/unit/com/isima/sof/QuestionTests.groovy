package com.isima.sof



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Question)
class QuestionTests {

    void testQuestionWithAnswers() {
	   Date date = new Date()
       Question q4 = new Question(title: 'What is ADO.NET ?', viewsNb: 12,
								   content: "Please help me I'm lost", creationDate: date)
						.addToAnswers(content: "You're insain", creationDate: date)
	   q4.save()
	   
	   assertNotNull(q4)
	   assertNotNull(q4.answers)
	   assertEquals q4.answers.size(), 1
    }
}
