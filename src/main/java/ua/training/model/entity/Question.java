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
	
	/**
	* Class constructor specifying the text of the answer and the reference to the first answer.
	*/
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
	 * Sets the first answer in this question and sets next answer for every answer from the list.
	 * If this question already has answers assigned, the new answers will be assigned. 
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
