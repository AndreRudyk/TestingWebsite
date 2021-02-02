package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

import ua.training.model.enums.TestCategory;
import ua.training.model.enums.TestDifficulty;

/**
 * Represents a test with a list of questions.
 */
public class Test {

	/**
	 * The name of the test.
	 */
	private String name;

	/**
	 * The description of the test.
	 */
	private String description;

	/**
	 * The difficulty of the test.
	 */
	private TestDifficulty difficulty;

	/**
	 * The category of the test.
	 */
	private TestCategory category;

	/**
	 * The duration of the test.
	 */
	private int time;

	/**
	 * The list of questions.
	 */
	private List<Question> questions;

	/**
	 * The number that represents how many time the test has been passed.
	 */
	private int numberOfRequests;

	/**
	 * The location of the XML file with the test.
	 */
	private String location;

	/**
	 * Class constructor.
	 */
	private Test() {
		questions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDifficulty() {
		return difficulty.toString().toLowerCase();
	}

	public void setDifficulty(TestDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getCategory() {
		return category.toString().toLowerCase();
	}

	public void setCategory(TestCategory category) {
		this.category = category;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Adds a new question to the test.
	 */
	public void addQuestion(Question question) {
		questions.add(question);
	}

	/**
	 * Adds a set of new questions to the test.
	 */
//	public void addQuestions(Question...newQuestions) {
//		for(Question question : newQuestions) {
//			questions.add(question);
//		}
//	}

	public int getNumberOfRequests() {
		return numberOfRequests;
	}

//	public void setNumberOfRequests(int numberOfRequests) {
//		this.numberOfRequests = numberOfRequests;
//	}

	public int getSize() {
		return questions.size();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Increments the number of requests in the test.
	 */
	public synchronized void incrementRequestNumber() {
		numberOfRequests++;
	}

	public static TestBuilder builder() {
		return new Test().new TestBuilder();
	}

	public class TestBuilder {

		private TestBuilder() {
			// a private constructor
		}

		public TestBuilder setName(String name) {
			Test.this.name = name;
			return this;
		}

		public TestBuilder setDescription(String description) {
			Test.this.description = description;
			return this;
		}

		public TestBuilder setDifficulty(TestDifficulty difficulty) {
			Test.this.difficulty = difficulty;
			return this;
		}

		public TestBuilder setCategory(TestCategory category) {
			Test.this.category = category;
			return this;
		}

		public TestBuilder setTime(int time) {
			Test.this.time = time;
			return this;
		}

		public TestBuilder setQuestions(List<Question> questions) {
			Test.this.questions = questions;
			return this;
		}

		public TestBuilder addQuestion(Question question) {
			Test.this.questions.add(question);
			return this;
		}

		/**
		 * Adds a set of new questions to the test.
		 */
		public TestBuilder addQuestions(Question... newQuestions) {
			for (Question question : newQuestions) {
				Test.this.questions.add(question);
			}
			return this;
		}

		public TestBuilder setNumberOfRequests(int numberOfRequests) {
			Test.this.numberOfRequests = numberOfRequests;
			return this;
		}

		public TestBuilder setLocation(String location) {
			Test.this.location = location;
			return this;
		}

		public Test build() {
			return Test.this;

		}
	}
}
