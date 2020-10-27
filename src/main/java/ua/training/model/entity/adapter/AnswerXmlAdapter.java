package ua.training.model.entity.adapter;

import javax.xml.bind.annotation.XmlRootElement;

import ua.training.model.entity.Answer;

/**
 * Represents an answer to a question in a test that is adapted for marshalling into XML file.
 */
@XmlRootElement (name = "answer")
public class AnswerXmlAdapter {
	
	private String xmlAnswertext;
	
	private boolean xmlCorrect;
	
	/**
	 * Private no-arg constructor added for marshalling into XML file.
	 */
	private AnswerXmlAdapter () {};
	
	/**
	* Class constructor with the answer that needs to be adapted.
	*/
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
