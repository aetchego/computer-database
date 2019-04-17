package dao;

import java.sql.SQLException;

import model.Computer;

public interface DaoInterface {

	void create(Computer computer) throws DaoException, SQLException;
	void read() throws DaoException, SQLException;
	void update() throws DaoException;
	void delete(int id) throws DaoException, SQLException;
}
