package com.isima.sof

class QuestionService {

    def listByTag(long id, String sortCriteria) {
		// Easy solution but inefficient (because it loads questions)
		/*def tag = Tag.get(params.id);
		def questionInstanceList = tag.questions.findAll()*/
		
		def questionInstanceList = Question.withCriteria {
			tags {
				eq('id', id )
			}
			order(sortCriteria, "desc")
		}
    }
	
	def incrementViewsNumber(Question question) {
		question.viewsNb++;
	}
}
