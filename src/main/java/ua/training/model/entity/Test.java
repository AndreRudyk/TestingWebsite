package ua.training.model.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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
	private DIFFICULTY difficulty;
	
	/**
	 * The category of the test.
	 */
	private CATEGORY category;
	
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
	public Test() {
		questions = new ArrayList<>();
	}
	
	/**
	 * The levels of difficulty that the test may have.
	 */
	public enum DIFFICULTY {
        EASY, MEDIUM, HARD
    }
	
	/**
	 * The categories that the test may have.
	 */
	public enum CATEGORY {
        ENGLISH, HISTORY, MATH
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDifficulty() {
		return difficulty.toString().toLowerCase();
	}
	
	public void setDifficulty(String difficulty) {
		EnumSet.allOf(DIFFICULTY.class).forEach(diff -> {
			if (diff.toString().equalsIgnoreCase(difficulty)) {
				this.difficulty = diff;
			}
		});
	}
	
	public String getCategory() {
		return category.toString().toLowerCase();
	}
	
	public void setCategory(String category) {
		EnumSet.allOf(CATEGORY.class).forEach(cat -> {
			if (cat.toString().equalsIgnoreCase(category)) {
				this.category = cat;
			}
		});
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
	public void addQuestions(Question...newQuestions) {
		for(Question question : newQuestions) {
			questions.add(question);
		}
	}
	
	public int getNumberOfRequests() {
		return numberOfRequests;
	}
	
	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}
	
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
	
}
