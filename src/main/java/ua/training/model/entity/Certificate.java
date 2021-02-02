package ua.training.model.entity;

/**
 * Represents a certificate that has a reference to a user and a test
 * and contains the received mark and date of passing the test.
 */
public class Certificate {
	
	String username;
	String testName;
	int mark;
	String date;
	
	private Certificate() {
		//a private constructor
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public static CertificateBuilder builder() {
		return new Certificate().new CertificateBuilder();
	}
	
	public class CertificateBuilder{
		
		private CertificateBuilder() {
			//a private constructor
		}
		
		public CertificateBuilder setUsername(String username) {
			Certificate.this.username = username;
			return this;
		}
		
		public CertificateBuilder setTestName(String testName) {
			Certificate.this.testName = testName;
			return this;
		}
		
		public CertificateBuilder setDate(String date) {
			Certificate.this.date = date;
			return this;
		}
		
		public CertificateBuilder setMark(int mark) {
			Certificate.this.mark = mark;
			return this;
		}
		
		public Certificate build() {
			return Certificate.this;
		}
	}
	
}
