package ua.training.model.entity.adapter;

import javax.xml.bind.annotation.XmlRootElement;

import ua.training.model.entity.Answer;

@XmlRootElement (name = "answer")
public class AnswerXmlAdapter {
	
	private String xmlAnswertext;
	
	private boolean xmlCorrect;
	
	/**
	 * Private no-arg constructor added for marshalling into xml file.
	 */
	private AnswerXmlAdapter () {};
	
	public AnswerXmlAdapter (Answer answer) {
		this.xmlAnswertext = answer.getText();
		this.xmlCorrect = answer.isCorrect();
	}

	public String getText() {
		return xmlAnswertext;
	}

	public void setText(String text) {
		this.xmlAnswertext = text;
	}

	public boolean isCorrect() {
		return xmlCorrect;
	}

	public void setCorrect(boolean correct) {
		this.xmlCorrect = correct;
	}
	
}
