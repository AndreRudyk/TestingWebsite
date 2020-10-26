package ua.training.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.TestDAO;
import ua.training.model.dao.impl.Constants;
import ua.training.model.entity.Answer;
import ua.training.model.entity.Question;
import ua.training.model.entity.Test;
import ua.training.model.entity.adapter.TestXmlAdapter;
import ua.training.model.entity.builder.TestBuilder;

public class TestService {

	private DAOFactory daoFactory;
	
	private static final String[] ANSWERS = {"answer1", "answer2", "answer3", "answer4"};
	
	private static final String[] ANSWERS_CORRECT = {"answer1correct", "answer2correct", "answer3correct", "answer4correct"};
	
	public TestService() {
		this.daoFactory = DAOFactory.getInstance();
	}

	public boolean isNameAvailable(String name) {

		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			return testDAO.checkIfNameAvailable(name);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
	}

	public Test createTest(HttpServletRequest request) {
		Test test = new Test();
		TestBuilder builder = new TestBuilder(test);
		
		builder.setName(request.getParameter(Constants.NAME))
			   .setDescription(request.getParameter(Constants.DESCRIPTION))
			   .setDifficulty(request.getParameter(Constants.DIFFICULTY))
			   .setCategory(request.getParameter(Constants.CATEGORY))
			   .setTime(Integer.parseInt(request.getParameter(Constants.TIME)));
		
		return  test;
	}
	
	public Test addQuestion (HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Test test = (Test) session.getAttribute(Constants.TEST);
		
		String questionText = request.getParameter(Constants.QUESTION);
		
		Answer[] answers = getAnswers(request);
		
		if ((!questionText.isEmpty()) && answers != null) {
			Question question = new Question(questionText);
			for (Answer answer : answers) {
				question.addAnswer(answer);
			}
			test.addQuestion(question);
			session.setAttribute("test", test);
			return test;
		}
		return null;
	}
	
	private Answer[] getAnswers (HttpServletRequest request) {
		
		Answer[] answersList = new Answer[4];
		
		String answerText;
		boolean isCorrect;
		
		Answer answer;
		
		for (int i = 0; i < ANSWERS.length; i++) {
			answerText = request.getParameter(ANSWERS[i]);
			isCorrect = (request.getParameter(ANSWERS_CORRECT[i]) != null);
			if (!answerText.isEmpty()) {
				answer = new Answer(answerText, isCorrect);
				answersList[i] = answer;
			} else {
				return null;
			}
			
		}
		return answersList;
	}
	
	public void saveToXml (Test test, String filePath) {
		
		File file = new File(filePath);
		
		try {
			Files.deleteIfExists(file.toPath());
		} catch (IOException e) {
			LogManager.getLogger(TestService.class).fatal("An error occured while tryingto delete a file");
			throw new RuntimeException();
		}

		TestXmlAdapter testAdapter = new TestXmlAdapter(test);
		
		testAdapter.saveToXml(filePath);
	}
	
	public void addToDatabase (Test test) {
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			testDAO.create(test);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured while trying to add a test to database");
			throw new RuntimeException();
		}
	}
	
	public List<Test> findAllTests(){
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			return testDAO.findAll();
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
	}
	
	public Optional<Test> getCompleteTest(String name) {
		Test test;
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			test = testDAO.findByName(name);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
				
		TestXmlAdapter adapter = new TestXmlAdapter(test);
		test = adapter.getFromXml(test);
		
		return Optional.ofNullable(test);		
	}
	
	public void deleteTest(String name) {
		
		String location = null;
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			location = testDAO.getLocationAndDelete(name);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).warn("Failed to delete the file from database");
			throw new RuntimeException();
		}
		File file = new File(location);
		
		try {
			Files.deleteIfExists(file.toPath());
			LogManager.getLogger(TestService.class).info("File " + name + " was successfuly deleted.");
		} catch (IOException e) {
			LogManager.getLogger(TestService.class).warn("Failed to delete the file");
			throw new RuntimeException();
		}
	}
	
	public Question getQuestion(HttpServletRequest request) {
		
		Question question = new Question(request.getParameter(Constants.QUESTION));
		
		Answer[] answers = getAnswers(request);
		
		if (question.getText() != null && answers != null) {
			for (Answer answer : answers) {
				question.addAnswer(answer);
			}
			return question;
		}
		return null;
	}
	
	public void updateTest(Test test) {
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			testDAO.update(test);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the test");
			throw new RuntimeException();
		}
	}
	
	
	
	public List<Test> findAllByCategoryAndSorted(String category, String sortBy){
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			String sortByDefault = "name";
			if (sortBy != null) {
				sortByDefault = sortBy;
			}
			return testDAO.findAllByCategoryAndSorted(category, sortByDefault);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
	}
	
	public int evaluateQuestion(Test test, int previousIndex, HttpServletRequest request) {
		Question question = test.getQuestions().get(previousIndex);
		
		List<Answer> answers = question.getAnswers();
		
		boolean userAnswer = false;
		boolean actualAnswer = false;
		
		for (int i = 0; i < ANSWERS.length; i++) {

			userAnswer = (request.getParameter(ANSWERS_CORRECT[i]) != null);
			actualAnswer = answers.get(i).isCorrect();
			
			if (userAnswer != actualAnswer) {
				return 0;
			} 
		}
		
		return 1;
	}
	
	public int calculateFinalGrade (int correctAnswersNum, int questionsNum) {
		
		int hundred = 100;
		
		return correctAnswersNum * hundred / questionsNum;
	}
	
	public void incrementNumberOfRequests(Test test) {
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			testDAO.incrementNumberOfRequests(test);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
		
	}
	
	public Test updateMainInfo(Test test, HttpServletRequest request) {
		
		String newDesciption = request.getParameter(Constants.DESCRIPTION);
		String newDuration = request.getParameter(Constants.TIME);
		String newCategory = request.getParameter(Constants.CATEGORY);
		String newDifficulty = request.getParameter(Constants.DIFFICULTY);
		if (newDesciption != null) {
			test.setDescription(newDesciption);
		}
		if (newDuration != null) {
			test.setTime(Integer.parseInt(newDuration));
		}
		if(newCategory != null) {
			test.setCategory(newCategory);
		}
		if (newDifficulty != null) {
			test.setDifficulty(newDifficulty);
		}
		return test;
	}
}

