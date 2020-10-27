package ua.training.model.entity.builder;

import java.util.List;

import ua.training.model.entity.Question;
import ua.training.model.entity.Test;

/**
 * A builder for the test class.
 */
public class TestBuilder {
	
	Test test;
	
	/**
	* Class constructor specifying the test that this class will build.
	*/
	public TestBuilder(Test test) {
		this.test = test;
	}
	
	public TestBuilder setName(String name) {
		test.setName(name);
		return this;
	}
	
	public TestBuilder setDescription(String description) {
		test.setDescription(description);
		return this;
	}
	
	public TestBuilder setDifficulty(String complexity) {
		test.setDifficulty(complexity);
		return this;
	}
	
	public TestBuilder setCategory(String category) {
		test.setCategory(category);
		return this;
	}
	
	public TestBuilder setTime(int time) {
		test.setTime(time);
		return this;
	}
	
	public TestBuilder setNumberOfRequests(int number) {
		test.setNumberOfRequests(number);
		return this;
	}
	
	public TestBuilder setLocation(String location) {
		test.setLocation(location);
		return this;
	}
	
	public TestBuilder setQuestions(List<Question> questions) {
		test.setQuestions(questions);
		return this;
	}
	
	public TestBuilder addQuestion(Question question) {
		test.getQuestions().add(question);
		return this;
	}
	
	public TestBuilder addQuestions(Question...newQuestions) {
		for (Question question : newQuestions) {
			test.addQuestion(question);
		}
		return this;
	}
	
}
