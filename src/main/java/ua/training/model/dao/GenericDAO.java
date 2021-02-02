package ua.training.model.dao;

public interface GenericDAO<T> extends AutoCloseable {
	void create (T entity);
}
