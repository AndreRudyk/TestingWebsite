package ua.training.model.entity.builder;

import ua.training.model.entity.Certificate;

public class CertificateBuilder {
	
	private Certificate certificate;
	
	public CertificateBuilder(Certificate certificate) {
		this.certificate = certificate;
	}
	
	public CertificateBuilder setUsername(String username) {
		certificate.setUsername(username);
		return this;
	}
	
	public CertificateBuilder setTestName(String testName) {
		certificate.setTestName(testName);
		return this;
	}
	
	public CertificateBuilder setDate(String date) {
		certificate.setDate(date);
		return this;
	}
	
	public CertificateBuilder setMark(int mark) {
		certificate.setMark(mark);
		return this;
	}

}
