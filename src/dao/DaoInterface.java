package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import model.Computer;

public interface DaoInterface {

	void create(Computer computer) throws DaoException, SQLException;
	Optional<ArrayList<Computer>> read(int offset, int limit) throws DaoException, SQLException;
	void delete(int id) throws DaoException, SQLException;
	void update(Computer computer, Integer id) throws DaoException, SQLException;
}
