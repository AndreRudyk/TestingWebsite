package ua.training.model.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Test {
	
	private String name;
	private String description;
	private DIFFICULTY difficulty;
	private CATEGORY category;
	private int time;
	private List<Question> questions;
	private int numberOfRequests;
	private String location;
	
	public Test() {
		questions = new ArrayList<>();
	}
	
	public enum DIFFICULTY {
        EASY, MEDIUM, HARD
    }
	
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
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
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

	public synchronized void incrementRequestNumber() {
		numberOfRequests++;
	}
	
}
