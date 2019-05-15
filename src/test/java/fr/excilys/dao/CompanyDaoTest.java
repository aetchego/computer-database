package fr.excilys.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.excilys.config.AppConfig;
import fr.excilys.dao.CompanyDao;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.database.UTDatabase;
import fr.excilys.model.Company;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CompanyDaoTest {

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UTDatabase utDatabase;

	@Before
	public void loadDatabase() throws IOException, SQLException {
		utDatabase.reload();
	}

	@Test
	public void read() throws DaoException, DaoConfigException, SQLException {
		final List<Company> actual = utDatabase.readCompanies();
		final List<Company> expected = companyDao.read().getCompanies();
		assertEquals(expected, actual);
	}

}