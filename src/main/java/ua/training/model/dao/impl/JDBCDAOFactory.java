package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import com.mysql.cj.jdbc.MysqlDataSource;

import ua.training.model.dao.CertificateDAO;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.TestDAO;
import ua.training.model.dao.UserDAO;

/**
 * A factory that provides DAOs for entities with a connection.
 *
 */
public class JDBCDAOFactory extends DAOFactory{

	/**
	 * Returns a user DAO.
	 */
	@Override
	public UserDAO createUserDAO() {
		return new JDBCUserDAO(getConnection());
	}

	/**
	 * Returns a test DAO.
	 */
	@Override
	public TestDAO createTestDAO() {
		return new JDBCTestDAO(getConnection());
	}
	
	
	/**
	 * Returns a certificate DAO.
	 */
	@Override
	public CertificateDAO createCertificateDAO() {
		return new JDBCCertificateDAO(getConnection());
	}
	
	/** 
	 * Returns a connection to MySQL database.
	 */
	private Connection getConnection(){
		String encoding = "UTF-8";
		String collation = "utf8_general_ci";
		String characterEncoding = encoding;
		String connectionCollation = collation;
		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser(DBQueries.DB_USER);
			dataSource.setPassword(DBQueries.DB_PASSWORD);
			dataSource.setDatabaseName(DBQueries.DB_NAME);
			dataSource.setCharacterEncoding(characterEncoding);
			dataSource.setConnectionCollation(connectionCollation);
			Connection conn = (Connection) dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			LogManager.getLogger(JDBCDAOFactory.class).fatal("Failed to get a connection to database");
			throw new RuntimeException(e);
		}
    }

}
