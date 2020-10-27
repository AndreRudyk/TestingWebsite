package ua.training.model.entity.builder;

import ua.training.model.entity.Certificate;

/**
 * A builder for the certificate class.
 */
public class CertificateBuilder {
	
	private Certificate certificate;
	
	/**
	* Class constructor specifying the certificate that this class will build.
	*/
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
