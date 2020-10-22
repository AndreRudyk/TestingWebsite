package ua.training.model.dao;

import ua.training.model.dao.impl.JDBCDAOFactory;

public abstract class DAOFactory {

	private static DAOFactory daoFactory;

	public abstract UserDAO createUserDAO();

	public abstract TestDAO createTestDAO();

	public abstract CertificateDAO createCertificateDAO();

	public static DAOFactory getInstance() {
		if (daoFactory == null) {
			synchronized (DAOFactory.class) {
				if (daoFactory == null) {
					DAOFactory inst = new JDBCDAOFactory();
					daoFactory = inst;
				}
			}
		}
		return daoFactory;
	}

}
