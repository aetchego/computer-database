package dao;

import java.sql.SQLException;

public interface DaoInterface {

	void create() throws DaoException;
	void read() throws DaoException, SQLException;
	void update() throws DaoException;
	void delete() throws DaoException;
}
