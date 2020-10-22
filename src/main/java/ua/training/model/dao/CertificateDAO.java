package ua.training.model.dao;

import java.util.List;

import ua.training.model.entity.Certificate;
import ua.training.model.entity.Test;

public interface CertificateDAO extends GenericDAO<Certificate> {
	public Test findByUsername(String username);
	public List<Certificate> findAllByUsername(String username);
}
