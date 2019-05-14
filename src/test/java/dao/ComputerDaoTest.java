package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import database.UTDatabase;
import fr.excilys.client.UserException;
import fr.excilys.controller.ComputerController;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.ComputerMapper;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ComputerDaoTest {

	private ComputerController controller = ComputerController.getInstance();
	private ComputerMapper mapper = ComputerMapper.getInstance();
	
	@Before
	public void loadDatabase() throws IOException, SQLException {
		UTDatabase.getInstance().reload();
	}
	
	@Test
	public void read() throws DaoException, DaoConfigException, SQLException, UserException {
		List<ComputerDTO> actual = UTDatabase.getInstance().readComputers(0, 150);
		List<ComputerDTO> expected = controller.listComputers(0, 150);
		assertEquals(expected, actual);
		actual.clear();
		expected.clear();
		
		actual = UTDatabase.getInstance().readComputers(1, 10);
		expected = controller.listComputers(1, 10);
		assertEquals(expected, actual);
		actual.clear();
		expected.clear();
	}
	
	@Test
	public void deleteById() throws DaoException, DaoConfigException, SQLException, UserException {
		UTDatabase.getInstance().deleteComputer(3);
		DaoFactory.getInstance().getComputer().delete(3);
		List<ComputerDTO> actual = UTDatabase.getInstance().readComputers(0, 150);
		List<ComputerDTO> expected = controller.listComputers(0, 150);
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectById() throws DaoException, DaoConfigException, SQLException, UserException {
		try {
			ComputerDTO actual = UTDatabase.getInstance().selectComputerById(15);
			ComputerDTO expected = controller.showDetails(15);
			assertEquals(expected, actual);
			expected = controller.showDetails(-10);
			fail("DaoException should have been thrown");
		} catch (DaoException | UserException e) {
			assert(true);
		}
	}
	
	@Test
	public void count() throws DaoException, DaoConfigException, SQLException, UserException {
		int actual = UTDatabase.getInstance().countComputers();
		int expected = controller.countComputers();
		assertEquals(expected, actual);
	}
	
	@Test
	public void create() throws DaoException, DaoConfigException, SQLException, UserException {
		ComputerDTO computer = mapper.StringsToDTO("Test", "2006-1-9", "2006-1-10", "Apple Inc.");
		UTDatabase.getInstance().createComputer(computer);
		controller.createComputer(computer);
		assertEquals(controller.showDetails(controller.countComputers() - 1),
		UTDatabase.getInstance().selectComputerById(UTDatabase.getInstance().countComputers() - 1));
		UTDatabase.getInstance().deleteComputer(UTDatabase.getInstance().countComputers());
	}
	
	@Test
	public void update() throws DaoException, DaoConfigException, SQLException {
		ComputerDTO computer = mapper.StringsToDTO("Test", "2006-1-9", "2006-1-10", "Apple Inc.");
		ComputerDTO computerCopy = UTDatabase.getInstance().selectComputerById(5);
		controller.updateComputer(5, computer);
		UTDatabase.getInstance().updateComputer(computer, 5);
		assertEquals(DaoFactory.getInstance().getComputer().showDetails(5), 
		UTDatabase.getInstance().selectComputerById(5));
		UTDatabase.getInstance().updateComputer(computerCopy, 5);
	}
}
