package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import database.UTDatabase;
import fr.excilys.client.UserException;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Computer;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ComputerDaoTest {

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
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectById() throws DaoException, DaoConfigException, SQLException {
		try {
			Optional<Computer> actual = UTDatabase.getInstance().selectComputerById(15);
			Optional<Computer> expected = DaoFactory.getInstance().getComputer().showDetails(15);
			assertEquals(expected, actual);
			expected = DaoFactory.getInstance().getComputer().showDetails(-10);
			fail("DaoException should have been thrown");
		} catch (DaoException e) {
			assert(true);
		}
	}
	
	@Test
	public void count() throws DaoException, DaoConfigException, SQLException {
		int actual = UTDatabase.getInstance().countComputers();
		int expected = DaoFactory.getInstance().getComputer().countComputers();
		assertEquals(expected, actual);
	}
	
	@Test
	public void create() throws DaoException, DaoConfigException, SQLException {
		Computer computer = new Computer();
		computer.setName("Test");
		computer.setCompanyId(3);
		computer.setIntroduced(Date.valueOf("2006-1-9"));
		computer.setDiscontinued(Date.valueOf("2006-1-10"));
		UTDatabase.getInstance().createComputer(computer);
		DaoFactory.getInstance().getComputer().create(computer);
		assertEquals(DaoFactory.getInstance().getComputer().showDetails(DaoFactory.getInstance().getComputer().countComputers() - 1),
		UTDatabase.getInstance().selectComputerById(UTDatabase.getInstance().countComputers() - 1));
		UTDatabase.getInstance().deleteComputer(UTDatabase.getInstance().countComputers());
	}
	
	@Test
	public void update() throws DaoException, DaoConfigException, SQLException {
		Computer computer = new Computer();
		Computer computerCopy = UTDatabase.getInstance().selectComputerById(5).get();
		computer.setName("Test");
		computer.setIntroduced(Date.valueOf("2006-1-9"));
		computer.setDiscontinued(Date.valueOf("2006-1-10"));
		computer.setId(computerCopy.getId());
		computer.setCompanyId(5);
		DaoFactory.getInstance().getComputer().update(5, computer);
		UTDatabase.getInstance().updateComputer(computer, 5);
		assertEquals(DaoFactory.getInstance().getComputer().showDetails(5), 
		UTDatabase.getInstance().selectComputerById(5));
		UTDatabase.getInstance().updateComputer(computerCopy, 5);
	}
}
