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
import ua.training.model.enums.TestCategory;
import ua.training.model.enums.TestDifficulty;

/**
 * Service that serves as a link between the servlet and the database for the test class.
 */
public class TestService {

	private DAOFactory daoFactory;
	
	/**
	 * A list of answer tags that are provided on the .jsp page.
	 */
	private static final String[] ANSWERS = {"answer1", "answer2", "answer3", "answer4"};
	
	/**
	 * A list of answer correctness tags that are provided on the .jsp page.
	 */
	private static final String[] ANSWERS_CORRECT = {"answer1correct", "answer2correct", "answer3correct", "answer4correct"};
	
	/**
	 * Class constructor.
	 */
	public TestService() {
		this.daoFactory = DAOFactory.getInstance();
	}

	/**
	 * Returns true if the provided name is not taken.
	 * 
	 * @param name the name to be checked for availability.
	 */
	public boolean isNameAvailable(String name) {

		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			return testDAO.checkIfNameAvailable(name);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
	}

	/**
	 * Creates a test from the data provided in the request and inserts the test into the database.
	 * 
	 * @param request	HttpServletRequest with the data about the test.
	 */
	public Test createTest(HttpServletRequest request) {
		return Test.builder()
					.setName(request.getParameter(Constants.NAME))
					.setDescription(request.getParameter(Constants.DESCRIPTION))
					.setDifficulty(TestDifficulty.valueOf(request.getParameter(Constants.DIFFICULTY).toUpperCase()))
					.setCategory(TestCategory.valueOf(request.getParameter(Constants.CATEGORY).toUpperCase()))
					.setTime(Integer.parseInt(request.getParameter(Constants.TIME)))
					.build();
	}
	
	/**
	 * Adds a question to a test in the session.
	 * Return the updated test.
	 * 
	 * @param request HttpServletRequest with the data about the test and question.
	 */
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
	
	/**
	 * Returns answers from the data provided in the request.
	 * 
	 * @param request	HttpServletRequest with the data about the answers.
	 */
	private Answer[] getAnswers (HttpServletRequest request) {
		
		Answer[] answersList = new Answer[4];
		
		String answerText;
		boolean isCorrect;
		
		Answer answer;
		
		for (int i = 0; i < ANSWERS.length; i++) {
			answerText = request.getParameter(ANSWERS[i]);
			isCorrect = (request.getParameter(ANSWERS_CORRECT[i]) != null);
			if (answerText != null) {
				answer = new Answer(answerText, isCorrect);
				answersList[i] = answer;
			} else {
				return null;
			}
			
		}
		return answersList;
	}
	
	/**
	 * Saves the provided test at the specified location.
	 * Deletes a file with the same name at this location if such a file exists.
	 * 
	 * @param test		the test to be saved.
	 * @param filePath	the location where the test will be saved,
	 */
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
	
	/**
	 * Inserts the test into the database.
	 * @param test	the test to be saved.
	 */
	public void addToDatabase (Test test) {
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			testDAO.create(test);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured while trying to add a test to database");
			throw new RuntimeException();
		}
	}
	
	/**
	 * Returns a list of all tests available in the database.
	 */
	public List<Test> findAllTests(){
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			return testDAO.findAll();
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
	}
	
	/**
	 * Returns an optional of test with the data from the database and 
	 * 		questions with answers from the XML file.
	 * @param name	the name of the test to be found.
	 */
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
	
	/**
	 * Deletes a test from the database and and its XML file
	 * 
	 * @param name	the name of the test to be deleted.
	 */
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
	
	/**
	 * Returns a question from the data provided in the request.
	 * 
	 * @param request	HttpServletRequest with the data about the question.
	 */
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
	
	/**
	 * Update the information about the test in the database.
	 * Uses the testname field in the test as reference point.
	 * 
	 * @param test	the test to be updated.
	 */
	public void updateTest(Test test) {
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			testDAO.update(test);
		} catch (Exception e) {
			LogManager.getLogger(UserService.class).fatal("Failed to update the test");
			throw new RuntimeException();
		}
	}
	
	/**
	 * Returns a list of tests by category and sorted.
	 * 
	 * @param category	the category of test to be returned (may be null if tests of all categories are needed).
	 * @param sortBy	the field by which the tests should be sorted.
	 */
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
	
	/**
	 * Returns 1 if the question was answered correctly, 
	 * 	and return 0 if the answer was incorrect.
	 * 
	 * @param test				the test that is being taken.
	 * @param previousIndex		the index of the previous question.
	 * @param request			HttpServletRequest with the data about the question.
	 */
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
	
	/**
	 * Returns a int that represent a percentage of correctly answered questions.
	 *  
	 * @param correctAnswersNum the number of correct answers.
	 * @param questionsNum		the total number of questions in the test.
	 */
	public int calculateFinalGrade (int correctAnswersNum, int questionsNum) {
		
		int hundred = 100;
		
		return correctAnswersNum * hundred / questionsNum;
	}
	
	/**
	 * Increments the number of requests of the provided test in the database.
	 * 
	 * @param test	test which number of request needs to be incremented.
	 */
	public void incrementNumberOfRequests(Test test) {
		try (TestDAO testDAO = daoFactory.createTestDAO()) {
			testDAO.incrementNumberOfRequests(test);
		} catch (Exception e) {
			LogManager.getLogger(TestService.class).fatal("An error occured");
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * Updates the information in the provided test according to the data provided in the request.
	 * 
	 * @param test		the test to be updated.
	 * @param request	HttpServletRequest with the data about the test.
	 */
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
			test.setCategory(TestCategory.valueOf(newCategory.toUpperCase()));
		}
		if (newDifficulty != null) {
			test.setDifficulty(TestDifficulty.valueOf(newDifficulty.toUpperCase()));
		}
		return test;
	}
}

