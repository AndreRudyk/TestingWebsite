package ua.training.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import com.mysql.cj.jdbc.MysqlDataSource;

import ua.training.model.dao.CertificateDAO;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.TestDAO;
import ua.training.model.dao.UserDAO;

public class JDBCDAOFactory extends DAOFactory{

	@Override
	public UserDAO createUserDAO() {
		return new JDBCUserDAO(getConnection());
	}

	@Override
	public TestDAO createTestDAO() {
		return new JDBCTestDAO(getConnection());
	}
	
	@Override
	public CertificateDAO createCertificateDAO() {
		return new JDBCCertificateDAO(getConnection());
	}
	
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
