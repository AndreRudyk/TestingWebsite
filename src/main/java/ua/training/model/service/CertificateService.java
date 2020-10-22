package ua.training.model.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import ua.training.model.dao.CertificateDAO;
import ua.training.model.dao.DAOFactory;
import ua.training.model.entity.Certificate;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.entity.builder.CertificateBuilder;

public class CertificateService {

	private DAOFactory daoFactory;
	
	public CertificateService() {
		this.daoFactory = DAOFactory.getInstance();
	}
	
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
