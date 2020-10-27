package ua.training.model.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.CertificateDAO;
import ua.training.model.dao.DAOFactory;
import ua.training.model.entity.Certificate;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.entity.builder.CertificateBuilder;

/**
 * Service that serves as a link between the servlet and the database for the certificate class.
 */
public class CertificateService {

	private DAOFactory daoFactory;
	
	/**
	 * Class constructor.
	 */
	public CertificateService() {
		this.daoFactory = DAOFactory.getInstance();
	}
	
	/**
	 * Creates a certificate with the provided data.
	 * 
	 * @param user	user to whom the certificate will be attributed.
	 * @param test	test that will be indicated in the certificate.
	 * @param markthe received mark.
	 */
	public void create (User user, Test test, int mark) {
		Certificate certificate = new Certificate();
		CertificateBuilder builder = new CertificateBuilder(certificate);
		
		builder.setUsername(user.getUsername())
			   .setTestName(test.getName())
			   .setMark(mark);
		try (CertificateDAO certifcateDAO = daoFactory.createCertificateDAO()) {
			certifcateDAO.create(certificate);
		} catch (Exception e) {
			LogManager.getLogger(CertificateService.class).fatal("An error occured. " + e.getMessage());
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * Returns a list of certificates that are owned by a user with the provided username.
	 * 
	 * @param username	the name of the user whose certificate need to be found,
	 */
	public List<Certificate> findAllByUsername(String username) {
		try (CertificateDAO certifcateDAO = daoFactory.createCertificateDAO()) {
			List<Certificate> certificates = certifcateDAO.findAllByUsername(username);
			return certificates;
		} catch (Exception e) {
			LogManager.getLogger(CertificateService.class).fatal("Couldn't retrieve certificates. " + e.getMessage());
			throw new RuntimeException();
		}
	}
	
}
