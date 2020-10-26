package ua.training.model.entity;

public class Answer {
	
	private String text;
	private boolean correct;
	private Answer nextAnswer;
	
	public Answer (String text, boolean correct) {
		this.text = text;
		this.correct = correct;
	}
	
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
