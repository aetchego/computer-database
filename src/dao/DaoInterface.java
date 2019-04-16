package dao;

public interface DaoInterface {

	void create() throws DaoException;
	void read() throws DaoException;
	void update() throws DaoException;
	void delete() throws DaoException;
}
