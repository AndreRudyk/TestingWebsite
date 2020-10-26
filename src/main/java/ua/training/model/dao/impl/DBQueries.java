package ua.training.model.dao.impl;

import java.util.ResourceBundle;

public class DBQueries {
	private static ResourceBundle bundle = ResourceBundle.getBundle("db");
	
	public static final String INSERT_USER_BY_UNAME_EMAIL_PASS_FNAME_LNAME = bundle.getString("insert.user.by.uname.email.password.fname.lname");
	
	public static final String GET_USER_AND_ROLE_BY_UNAME_AND_PASSWORD = bundle.getString("get.user.and.role.by.username.and.password");
	
	public static final String DELETE_USER_BY_ID = bundle.getString("delete.by.id");
	
	public static final String SELECT_ALL_USERS_WITH_ROLES = bundle.getString("select.all.users.with.roles");
	
	public static final String UPDATE_USER = bundle.getString("user.updateEmailFirstnameLastname");
	
	public static final String GET_ROLE_ID = bundle.getString("role.get.id");
	
	public static final String CHANGE_USER_ROLE = bundle.getString("user.change.role");
	
	public static final String INSERT_TEST_WITH_NAME_DESCIPTION_TIME_DIFFICUTY_CATEGORY_LOCATION = bundle.getString("insert.test.with.name.description.time.difficulty.category.location");
	
	public static final String GET_DIFFICULTY_ID = bundle.getString("difficulty.getId");
	
	public static final String GET_CATEGORY_ID = bundle.getString("category.getId");
	
	public static final String FIND_TEST_BY_NAME = bundle.getString("test.findByName");
	
	public static final String FIND_ALL_TESTS = bundle.getString("test.findAllTestsWithCategoryDifficulty");
	
	public static final String FIND_TEST_BY_NAME_WITH_CATEGORY_DIFFICULTY = bundle.getString("test.findByNameWithCategoryDifficulty");
	
	public static final String DELETE_TEST_BY_NAME = bundle.getString("test.deleteByName");
	
	public static final String UPDATE_TEST_DESCRIPTION_TIME_CATEGORY_DIFFICULTY = bundle.getString("test.updateDescriptionTimeCategoryDifficulty");
	
	public static final String GET_USER_EMAIL_FIRSTNAME_LASTNAME_BY_UNAME = bundle.getString("user.getEmailFirstnameLastNameByUsername");
	
	public static final String FIND_ALL_TESTS_OPEN_FOR_SORT = bundle.getString("test.findAllTestsOpenForSort");
	
	public static final String FIND_ALL_TESTS_BY_CATEGORY_OPEN_FOR_SORT = bundle.getString("test.findAllTestsByCategoryOpenForSort");
	
	public static final String INSERT_CERTIFICATE_WITH_USERID_TESTID_MARK = bundle.getString("certificate.insertWithUserTestMark");
	
	public static final String FIND_USERID_BY_USERNAME = bundle.getString("user.findIdByUsername");
	
	public static final String UPDATE_TEST_REQUEST_NUMBER_BY_NAME = bundle.getString("test.updateNumberOfRequests");
	
	public static final String FIND_TEST_REQUEST_NUMBER_BY_NAME = bundle.getString("test.findNumberOfRequestsByName");
	
	public static final String FIND_ALL_CERTIFICATES_BY_USERNAME = bundle.getString("certificate.findAllByUsername");
	
	public static final String DB_USER = bundle.getString("user");
	
	public static final String DB_PASSWORD = bundle.getString("password");
	
	public static final String DB_NAME = bundle.getString("dbName");
}
