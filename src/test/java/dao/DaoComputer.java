package dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import database.UTDatabase;
import fr.excilys.client.UserException;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class DaoComputer {

	@Before
	public void loadDatabase() throws IOException, SQLException {
		UTDatabase.getInstance().reload();
	}
	
	@Test
	public void read() throws DaoException, DaoConfigException, SQLException {
		List<Computer> actual = UTDatabase.getInstance().readComputers(0, 150);
		List<Computer> expected = DaoFactory.getInstance().getComputer().read(0, 150);
		assertEquals(expected, actual);
		actual.clear();
		expected.clear();
		
		actual = UTDatabase.getInstance().readComputers(1, 10);
		expected = DaoFactory.getInstance().getComputer().read(1, 10);
		assertEquals(expected, actual);
		actual.clear();
		expected.clear();
	}
	
	@Test
	public void deleteById() throws DaoException, DaoConfigException, SQLException, UserException {
		UTDatabase.getInstance().deleteComputer(3);
		DaoFactory.getInstance().getComputer().delete(3);
		List<Computer> actual = UTDatabase.getInstance().readComputers(0, 150);
		List<Computer> expected = DaoFactory.getInstance().getComputer().read(0, 150);
		System.out.println(actual.size() + " / " + expected.size());
		for (int i = 0; i < expected.size(); i++) {
			System.out.println(actual.get(i).toString());
			System.out.println(expected.get(i).toString());
		}
		assertEquals(expected, actual);
	}
}
