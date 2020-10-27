package ua.training.model.entity.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.logging.log4j.LogManager;

import ua.training.model.entity.Answer;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.service.TestService;

/**
 * Represents a test with a list of questions that is adapted for marshalling into XML file.
 */
@XmlRootElement(namespace = "ua.trainning.test")
public class TestXmlAdapter {

	@XmlTransient
	private Test test;

	private List<QuestionXmlAdapter> xmlQuestions;

	/**
	* Class constructor.
	*/
	public TestXmlAdapter() {
		xmlQuestions = new ArrayList<>();
	}

	/**
	* Class constructor with the test that needs to be adapted.
	*/
	public TestXmlAdapter(Test test) {
		this.test = test;
		this.xmlQuestions = adaptQuestions();
	}

	/**
	* Returns a list of adapted questions from the original test.
	*/
	private List<QuestionXmlAdapter> adaptQuestions() {
		List<QuestionXmlAdapter> adaptedQuestions = new ArrayList<>();
		for (Question question : test.getQuestions()) {
			adaptedQuestions.add(new QuestionXmlAdapter(question));
		}
		return adaptedQuestions;
	}

	/**
	 * Returns a test from with the questions and answers from this object.
	 * 
	 * @param test that will be updated with the new questions and answers.
	 */
	public Test reverseToTest(Test test) {
		Question reversedQuestion;
		Answer reversedAnswer;
		for (QuestionXmlAdapter question : xmlQuestions) {
			reversedQuestion = new Question(question.getText());
			for (AnswerXmlAdapter answer : question.getAnswers()) {
				reversedAnswer = new Answer(answer.getText(), answer.isCorrect());
				reversedQuestion.addAnswer(reversedAnswer);
			}
			test.getQuestions().add(reversedQuestion);
		}
		return test;
	}

	@XmlTransient
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<QuestionXmlAdapter> getQuestions() {
		return this.xmlQuestions;
	}

	public void setQuestions(List<QuestionXmlAdapter> questions) {
		this.xmlQuestions = questions;
	}

	/**
	 * Saves this question and answers from this test adapter into an XML file.
	 * @param filePath	the path where the file will be stored.
	 */
	public void saveToXml(String filePath) {
		try {
			JAXBContext context = JAXBContext.newInstance(TestXmlAdapter.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(this, new File(filePath));
		} catch (JAXBException e) {
			LogManager.getLogger(TestService.class).warn("Failed to save the test to a file");
			throw new RuntimeException(e);
		}

	}

	/**
	 * Returns a test from with the questions and answers from the XML file.
	 * 
	 * @param test that will be updated with the new questions and answers.
	 */
	public Test getFromXml(Test newTest) {
		try {
			JAXBContext context = JAXBContext.newInstance(TestXmlAdapter.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			TestXmlAdapter testAdapter = (TestXmlAdapter) unmarshaller.unmarshal(new FileReader(newTest.getLocation()));

			return testAdapter.reverseToTest(newTest);
		} catch (JAXBException e) {
			LogManager.getLogger(TestService.class).warn("Failed to get the test from the file");
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			LogManager.getLogger(TestService.class).warn("The test file wasn't found");
			throw new RuntimeException(e);
		}
	}
	
}
