package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.CertificateDAO;
import ua.training.model.entity.Certificate;

/**
 * This is a DAO for the certificate entity.
 *
 */
public class JDBCCertificateDAO implements CertificateDAO{
	
	private Connection connection;
	
	/**
	 * Class constructor with a Connection.
	 * @param connection	a connection to the database.
	 */
	public JDBCCertificateDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Inserts the certificate into the database using the data from provided certificate.
	 * 
	 * @param certificate	certificate to insert.
	 */
	@Override
	public void create(Certificate certificate) {
		ResultSet rs = null;
		String username = certificate.getUsername();
		String testName = certificate.getTestName();
		
		try {

			PreparedStatement statementForInsert = connection.prepareStatement(DBQueries.INSERT_CERTIFICATE_WITH_USERID_TESTID_MARK);
			PreparedStatement statementForUser = connection.prepareStatement(DBQueries.FIND_USERID_BY_USERNAME);
			PreparedStatement statementForTest = connection.prepareStatement(DBQueries.FIND_TEST_BY_NAME);
			
			statementForUser.setString(1, username);
			statementForTest.setString(1, testName);
			statementForInsert.setInt(3, certificate.getMark());
			
			int userId = -1;
			int testId = -1;
			
			connection.setAutoCommit(false);
			
			rs = statementForUser.executeQuery();
			
			if (rs.next()) {
				userId = rs.getInt(Constants.ID);
			}
			
			try {
				rs.close();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCCertificateDAO.class).fatal("Failed to close the result set. " + e1.getMessage());
				throw new RuntimeException(e1);
			}
			
			rs = statementForTest.executeQuery();
			
			if (rs.next()) {
				testId = rs.getInt(Constants.ID);
			}
			
			try {
				rs.close();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCCertificateDAO.class).fatal("Failed to close the result set. " + e1.getMessage());
				throw new RuntimeException(e1);
			}
			
			statementForInsert.setInt(1, userId);
			statementForInsert.setInt(2, testId);
			statementForInsert.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCCertificateDAO.class).warn("Failed to create certificate. " + e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LogManager.getLogger(JDBCCertificateDAO.class).warn("Failed to rollback the commit to database.");
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				LogManager.getLogger(JDBCCertificateDAO.class).warn("Connection failed");
				throw new RuntimeException(e);
			}
			try {
				rs.close();
			} catch (SQLException e) {
				LogManager.getLogger(JDBCCertificateDAO.class).warn("Failed to close the result set.");
				throw new RuntimeException(e);
			}
		}
		
	}
	
	/**
	 * Returns a list of certificates that are owned by a user with the provided username.
	 * 
	 * @param username	the name of the user whose certificate need to be found,
	 */
	public List<Certificate> findAllByUsername(String username){
		List<Certificate> certificates = new ArrayList<>();
		ResultSet rs = null;

		Certificate certificate;

		try {

			PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_ALL_CERTIFICATES_BY_USERNAME);
			statement.setString(1, username);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				certificate = Certificate.builder()
						.setUsername(username)
						.setTestName(rs.getString(Constants.NAME))
						.setMark(rs.getInt(Constants.MARK))
						.setDate(processDate(rs.getString(Constants.TIME)))
						.build();

				certificates.add(certificate);
			}
			return certificates;
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
		
	}
	
	/**
	 * Returns the date without the time.
	 * @param timeStamp	full date from the database.
	 */
	private String processDate(String timeStamp) {
		int startIndex = 0;
		int endIndex = 11;
		return timeStamp.substring(startIndex, endIndex);
	}

	/**
	 * Closes the connection to the database.
	 */
	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			LogManager.getLogger(JDBCCertificateDAO.class).warn("Failed to close the connection with the database");
			throw new RuntimeException(e);
		}
	}

}
