package ua.training.model.entity.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import ua.training.model.entity.Question;

@XmlRootElement (name = "question")
public class QuestionXmlAdapter {

	private Question question;
	
	private String xmlQuestionText;
	
	private List<AnswerXmlAdapter> xmlAnswers;
	
	/**
	 * Private no-arg constructor added for marshalling into xml file.
	 */
	private QuestionXmlAdapter () {};
	
	public QuestionXmlAdapter(Question question) {
		this.question = question;
		this.xmlQuestionText = question.getText();
		xmlAnswers = adaptAnswers();
	}
	
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
