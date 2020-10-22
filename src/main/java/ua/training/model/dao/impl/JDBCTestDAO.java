package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.TestDAO;
import ua.training.model.entity.Test;
import ua.training.model.entity.builder.TestBuilder;

public class JDBCTestDAO implements TestDAO {

	Connection connection;
	
	private static final char QUOTE = '\'';
	private static final char SPACE = ' ';
	private static final char SEMICOLON = ';';
	private static final String QUESTION_MARK = "?";
	private static final String ASC = "ASC";
	private static final String DESC = "DESC";
	

	public JDBCTestDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Test test) {

		int difficultyId = -1;

		int categoryId = -1;

		ResultSet rs = null;

		try {
			PreparedStatement statementForInsert = connection
					.prepareStatement(DBQueries.INSERT_TEST_WITH_NAME_DESCIPTION_TIME_DIFFICUTY_CATEGORY_LOCATION);
			statementForInsert.setString(1, test.getName());
			statementForInsert.setString(2, test.getDescription());
			statementForInsert.setInt(3, test.getTime());
			statementForInsert.setString(6, test.getLocation());

			PreparedStatement statementForDifficulty = connection.prepareStatement(DBQueries.GET_DIFFICULTY_ID);
			statementForDifficulty.setString(1, test.getDifficulty());

			PreparedStatement statementForCategory = connection.prepareStatement(DBQueries.GET_CATEGORY_ID);
			statementForCategory.setString(1, test.getCategory());

			connection.setAutoCommit(false);
			rs = statementForDifficulty.executeQuery();

			if (rs.next()) {
				difficultyId = rs.getInt(Constants.ID);
			}

			try {
				rs.close();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to close the result set");
				throw new RuntimeException(e1);
			}

			rs = statementForCategory.executeQuery();

			if (rs.next()) {
				categoryId = rs.getInt(Constants.ID);
			}

			try {
				rs.close();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to close the result set");
				throw new RuntimeException(e1);
			}

			statementForInsert.setInt(4, difficultyId);
			statementForInsert.setInt(5, categoryId);

			statementForInsert.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to insert test");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCTestDAO.class).fatal(e1.getMessage());
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to close the connection");
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public List<Test> findAll() {
		List<Test> tests = new ArrayList<>();
		ResultSet rs = null;

		Test test;

		TestBuilder builder;

		try {

			PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_ALL_TESTS);
			rs = statement.executeQuery();

			while (rs.next()) {
				test = new Test();

				builder = new TestBuilder(test);

				builder.setName(rs.getString(Constants.NAME)).setDescription(rs.getString(Constants.DESCRIPTION))
					   .setDifficulty(rs.getString(Constants.DIFFICULTY)).setCategory(rs.getString(Constants.CATEGORY))
					   .setTime(Integer.parseInt(rs.getString(Constants.TIME)));

				tests.add(test);
			}
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed. " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
		return tests;
	}

	@Override
	public void update(Test test) {

		String name = test.getName();
		String description = test.getDescription();
		int time = test.getTime();
		String category = test.getCategory();
		String difficulty = test.getDifficulty();
		int difficultyId = -1;
		int categoryId = -1;

		ResultSet rs = null;

		try {
			PreparedStatement statementForUpdate = connection
					.prepareStatement(DBQueries.UPDATE_TEST_DESCRIPTION_TIME_CATEGORY_DIFFICULTY);
			statementForUpdate.setString(1, description);
			statementForUpdate.setInt(2, time);
			statementForUpdate.setString(5, name);

			PreparedStatement statementForDifficulty = connection.prepareStatement(DBQueries.GET_DIFFICULTY_ID);
			statementForDifficulty.setString(1, difficulty);

			PreparedStatement statementForCategory = connection.prepareStatement(DBQueries.GET_CATEGORY_ID);
			statementForCategory.setString(1, category);

			connection.setAutoCommit(false);
			rs = statementForDifficulty.executeQuery();

			if (rs.next()) {
				difficultyId = rs.getInt(Constants.ID);
			}

			try {
				rs.close();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to close the result set");
				throw new RuntimeException(e1);
			}

			rs = statementForCategory.executeQuery();

			if (rs.next()) {
				categoryId = rs.getInt(Constants.ID);
			}

			try {
				rs.close();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCUserDAO.class).fatal("Failed to close the result set");
				throw new RuntimeException(e1);
			}

			statementForUpdate.setInt(3, categoryId);
			statementForUpdate.setInt(4, difficultyId);

			statementForUpdate.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to insert test");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCTestDAO.class).fatal(e1.getMessage());
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to close the connection");
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public Test findByName(String name) {
		Test test = new Test();
		TestBuilder builder = new TestBuilder(test);
		ResultSet rs = null;

		try {
			PreparedStatement statement = connection
					.prepareStatement(DBQueries.FIND_TEST_BY_NAME_WITH_CATEGORY_DIFFICULTY);

			statement.setString(1, name);

			rs = statement.executeQuery();

			if (rs.next()) {
				builder.setName(rs.getString(Constants.NAME)).setDescription(rs.getString(Constants.DESCRIPTION))
						.setDifficulty(rs.getString(Constants.DIFFICULTY)).setCategory(rs.getString(Constants.CATEGORY))
						.setTime(Integer.parseInt(rs.getString(Constants.TIME)))
						.setLocation(rs.getString(Constants.LOCATION))
						.setNumberOfRequests(rs.getInt(Constants.REQUEST_NUMBER));
			}
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).warn("Connection with database failed");
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).warn("Failed to close the result set");
			}
		}

		return test;
	}

	public boolean checkIfNameAvailable(String name) {
		ResultSet rs = null;

		try {
			PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_TEST_BY_NAME);

			statement.setString(1, name);

			rs = statement.executeQuery();

			return !(rs.next());
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).warn("Connection with database failed");
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).warn("Failed to close the result set");
			}
		}
	}

	@Override
	public String getLocationAndDelete(String name) {
		ResultSet rs = null;
		String location = null;

		try {

			PreparedStatement statementForLocation = connection.prepareStatement(DBQueries.FIND_TEST_BY_NAME);
			PreparedStatement statementForDelete = connection.prepareStatement(DBQueries.DELETE_TEST_BY_NAME);

			statementForLocation.setString(1, name);
			statementForDelete.setString(1, name);

			connection.setAutoCommit(false);

			rs = statementForLocation.executeQuery();

			if (rs.next()) {
				location = rs.getString(Constants.LOCATION);
			}
			statementForDelete.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed. " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed");
				throw new RuntimeException(e1);
			}
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
		return location;
	}

	@Override
	public List<Test> findAllByCategoryAndSorted(String category, String sortBy) {
		List<Test> tests = new ArrayList<>();
		ResultSet rs = null;

		Test test;

		TestBuilder builder;
		
		try {

			PreparedStatement statement = connection.prepareStatement(getPreparedStatement(category, sortBy));
			rs = statement.executeQuery();

			while (rs.next()) {
				test = new Test();

				builder = new TestBuilder(test);

				builder.setName(rs.getString(Constants.NAME)).setDescription(rs.getString(Constants.DESCRIPTION))
						.setDifficulty(rs.getString(Constants.DIFFICULTY)).setCategory(rs.getString(Constants.CATEGORY))
						.setTime(rs.getInt(Constants.TIME))
						.setNumberOfRequests(rs.getInt(Constants.REQUEST_NUMBER));

				tests.add(test);
			}
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed. " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Connection failed");
				throw new RuntimeException(e);
			}
		}
		return tests;
	}
	
	private String getPreparedStatement (String category, String sortBy) {
		StringBuilder result = new StringBuilder();
		
		if (category == null) {
			result.append(DBQueries.FIND_ALL_TESTS_OPEN_FOR_SORT);
		} else {
			result.append(DBQueries.FIND_ALL_TESTS_BY_CATEGORY_OPEN_FOR_SORT);
			int indexOfQuestionMark = result.indexOf(QUESTION_MARK);
			String qoutedCategory = surroundWithQuotes(category);
			result.replace(indexOfQuestionMark, indexOfQuestionMark + 1, qoutedCategory);
		}
		
		String order = ASC;
		if(Constants.REQUEST_NUMBER.equals(sortBy)) {
			order = DESC;
		}
		
		result.append(SPACE)
						.append(sortBy)
						.append(SPACE)
						.append(order)
						.append(SEMICOLON);
		
		return result.toString();
	}
	
	private String surroundWithQuotes(String word) {
		StringBuilder result = new StringBuilder();
		result.append(QUOTE)
			  .append(word)
			  .append(QUOTE);
		return result.toString();
	}

	@Override
	public void incrementNumberOfRequests(Test test) {
		int numberOfRequests = test.getNumberOfRequests();
		String name = test.getName();

		ResultSet rs = null;

		try {
			
			PreparedStatement statementForUpdate = connection.prepareStatement(DBQueries.UPDATE_TEST_REQUEST_NUMBER_BY_NAME);
			
			PreparedStatement statementForNumber = connection.prepareStatement(DBQueries.FIND_TEST_REQUEST_NUMBER_BY_NAME);
			
			statementForUpdate.setString(2, name);
			statementForNumber.setString(1, name);

			connection.setAutoCommit(false);
			rs = statementForNumber.executeQuery();
			if (rs.next()) {
				numberOfRequests = rs.getInt(Constants.REQUEST_NUMBER);
			}
			
			int increasedNumber = numberOfRequests + 1;
			
			statementForUpdate.setInt(1, increasedNumber);
			
			statementForUpdate.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to update test's number of requests.");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCTestDAO.class).fatal(e1.getMessage());
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to close the connection");
				throw new RuntimeException(e);
			}
			
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCTestDAO.class).fatal("Failed to close the connection");
				throw new RuntimeException(e);
			}
		}
		
	}
	
}
