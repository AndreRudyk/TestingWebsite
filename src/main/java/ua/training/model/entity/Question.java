package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a question in a test.
 * It may have a link to the first answer.
 */
public class Question {

	private static final String TAB = "    ";
	private static final String EOL = System.lineSeparator();
	private static final char SPACE = ' ';
	
	private String text;
	private Answer firstAnswer;
	
	/**
	* Class constructor specifying the text of the question.
	*/
	public Question(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
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
	 * Adds a next answer to the question.
	 * @param newAnswer	an answer to be added to the question
	 */
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
