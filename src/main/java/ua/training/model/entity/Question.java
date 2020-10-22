package ua.training.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

	private static final String TAB = "    ";
	private static final String EOL = System.lineSeparator();
	private static final char SPACE = ' ';
	
	private String text;
	private Answer firstAnswer;
	
	public Question(String text) {
		this.text = text;
	}
	
	public Question(String text, Answer firstAnswer) {
		this.text = text;
		this.firstAnswer = firstAnswer;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Answer> getAnswers() {
		List<Answer> answers = new ArrayList<>();
		if (firstAnswer != null) {
			answers.add(firstAnswer);
			Answer currentAnswer = firstAnswer;
			while (currentAnswer.hasNextAnswer()) {
				currentAnswer = currentAnswer.getNextAnswer();
				answers.add(currentAnswer);
			}
		}
		return answers;
	}
	
	/**
	 * Sets the first answer in this Question and sets next Answer for every Answer from the list.
	 * 			If this Question already has Answers assigned, the new Answers will be assigned. 
	 * 	
	 * @param answers the possible answers to this Question
	 */
	public void setAnswers(List<Answer> answers) {
		
		firstAnswer = answers.get(0);
		
		Answer currentAnswer = firstAnswer;
		
		for (int i = 1; i < answers.size();  i++) {
			currentAnswer.setNextAnswer(answers.get(i));
			currentAnswer = currentAnswer.getNextAnswer();
		}
	}
	
	public void addAnswer(Answer newAnswer) {
		if (firstAnswer != null) {
			Answer currentAnswer = firstAnswer;
			
			while(currentAnswer.hasNextAnswer()) {
				currentAnswer = currentAnswer.getNextAnswer();
			}
			
			currentAnswer.setNextAnswer(newAnswer);
		} else {
			this.firstAnswer = newAnswer;
		}
	}
	
	public Answer getFirstAnswer() {
		return firstAnswer;
	}

	public void setFirstAnswer(Answer firstAnswer) {
		this.firstAnswer = firstAnswer;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(text).append(EOL);
		int index = 1;
		
		Answer currentAnswer = firstAnswer;
		
		while (currentAnswer != null) {
			sb.append(TAB).append(index).append(SPACE).append(currentAnswer.getText()).append(EOL);
			currentAnswer = currentAnswer.getNextAnswer();
			index++;
		}
		
		return sb.toString();
	}
	
	
}
