package dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import database.UTDatabase;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Company;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CompanyDaoTest {

	@Before
	public void loadDatabase() throws IOException, SQLException {
		UTDatabase.getInstance().reload();
	}
	
	@Test
	public void read() throws DaoException, DaoConfigException, SQLException {
		final List<Company> actual = UTDatabase.getInstance().readCompanies();
		final List<Company> expected = DaoFactory.getInstance().getCompany().read().getCompanies();
		assertEquals(expected, actual);
	}

}
