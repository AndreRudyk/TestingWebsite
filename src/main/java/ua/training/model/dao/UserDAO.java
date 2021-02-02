package ua.training.model.dao;

import java.util.List;
import java.util.Optional;

import ua.training.model.entity.User;

public interface UserDAO extends GenericDAO<User>{
	public Optional<User> findByUsernameAndPassword (String username, String password);
	public Optional<User> findByUsername (String username);
	public void grantAdminRights (String username);
	public void revokeAdminRights (String username);
	public void deleteByUsername (String username);
	List<User> findAll();
	void update(User entity);
}
