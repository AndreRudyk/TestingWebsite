package ua.training.model.dao;

import java.util.List;

import ua.training.model.entity.Test;

public interface TestDAO extends GenericDAO<Test> {
	public Test findByName(String name);
	public boolean checkIfNameAvailable(String name);
	public String getLocationAndDelete(String name);
	public void update(Test test);
	public List<Test> findAllByCategoryAndSorted(String category, String sortBy);
	public void incrementNumberOfRequests(Test test);
}
