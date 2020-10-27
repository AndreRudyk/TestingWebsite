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
	
}
