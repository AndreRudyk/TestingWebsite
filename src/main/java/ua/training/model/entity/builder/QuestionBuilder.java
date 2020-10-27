package ua.training.model.entity.builder;

import ua.training.model.entity.Answer;
import ua.training.model.entity.Question;

/**
 * A builder for the question class.
 */
public class QuestionBuilder {
	
	Question question;
	
	/**
	* Class constructor specifying the question that this class will build.
	*/
	public QuestionBuilder (Question question) {
		this.question = question;
	}
	
	public QuestionBuilder setText (String text) {
		question.setText(text);
		return this;
	}
	
	public QuestionBuilder addAnswer (Answer answer) {
		question.addAnswer(answer);
		return this;
	}
	
}
