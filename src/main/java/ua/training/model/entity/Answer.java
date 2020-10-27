package ua.training.model.entity;

/**
 * Represents an answer to a question in a test.
 * It has text, information about its correctness and may have a next answer.
 */
public class Answer {
	
	private String text;
	private boolean correct;
	private Answer nextAnswer;
	
	/**
	* Class constructor specifying the text of the answer and the value of correct field.
	*/
	public Answer (String text, boolean correct) {
		this.text = text;
		this.correct = correct;
	}
	
	/**
	* Class constructor specifying the text of the answer.
	*/
	public Answer(String text) {
		this(text, false);
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	public Answer getNextAnswer() {
		return nextAnswer;
	}

	public void setNextAnswer(Answer nextAnswer) {
		this.nextAnswer = nextAnswer;
	}
	
	public boolean hasNextAnswer() {
		return nextAnswer != null;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	public void makeCorrect() {
		this.correct = true;
	}
	
	@Override
	public String toString() {
		return text;
	}

}
