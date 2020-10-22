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
import ua.training.model.entity.Test;
import ua.training.model.entity.builder.CertificateBuilder;

public class JDBCCertificateDAO implements CertificateDAO{
	
	private Connection connection;

	public JDBCCertificateDAO(Connection connection) {
		this.connection = connection;
	}

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
	
	public List<Certificate> findAllByUsername(String username){
		List<Certificate> certificates = new ArrayList<>();
		ResultSet rs = null;

		Certificate certificate;

		CertificateBuilder builder;
		
		try {

			PreparedStatement statement = connection.prepareStatement(DBQueries.FIND_ALL_CERTIFICATES_BY_USERNAME);
			statement.setString(1, username);
			
			rs = statement.executeQuery();

			while (rs.next()) {
				certificate = new Certificate();

				builder = new CertificateBuilder(certificate);
				
				builder.setUsername(username)
					   .setTestName(rs.getString(Constants.NAME))
					   .setMark(rs.getInt(Constants.MARK))
					   .setDate(processDate(rs.getString(Constants.TIME)));

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
	
	private String processDate(String timeStamp) {
		int startIndex = 0;
		int endIndex = 11;
		return timeStamp.substring(startIndex, endIndex);
	}

	@Override
	public List<Certificate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Certificate entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Test findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


}
