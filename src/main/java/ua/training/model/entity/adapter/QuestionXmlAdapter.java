package ua.training.model.entity.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ua.training.model.entity.Question;

/**
 * Represents an question in a test that is adapted for marshalling into XML file.
 */
@XmlRootElement (name = "question")
public class QuestionXmlAdapter {

	private Question question;
	
	private String xmlQuestionText;
	
	private List<AnswerXmlAdapter> xmlAnswers;
	
	/**
	 * Private no-arg constructor added for marshalling into XML file.
	 */
	private QuestionXmlAdapter () {
		//a private constructor
	}
	
	/**
	* Class constructor with the question that needs to be adapted.
	*/
	public QuestionXmlAdapter(Question question) {
		this.question = question;
		this.xmlQuestionText = question.getText();
		xmlAnswers = adaptAnswers();
	}
	
	/**
	* Returns a list of adapted answers from the original question.
	*/
	private List<AnswerXmlAdapter> adaptAnswers(){
		List<AnswerXmlAdapter> adaptedAnswers = new ArrayList<>();
		question.getAnswers().stream()
					.forEach(q -> adaptedAnswers.add(new AnswerXmlAdapter(q)));
		return adaptedAnswers;
	}

	public String getText() {
		return xmlQuestionText;
	}

	public void setText(String text) {
		this.xmlQuestionText = text;
	}

	public List<AnswerXmlAdapter> getAnswers() {
		return this.xmlAnswers;
	}

	public void setAnswers(List<AnswerXmlAdapter> answers) {
		this.xmlAnswers = answers;
	}
	
}
