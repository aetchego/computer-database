package fr.excilys.validator;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.excilys.config.AppConfig;
import fr.excilys.database.UTDatabase;
import fr.excilys.exception.DaoConfigException;
import fr.excilys.exception.DaoException;
import fr.excilys.exception.UserException;
import fr.excilys.mapper.ComputerMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ValidatorTest {

	@Autowired
	private ComputerMapper mapper;
	@Autowired
	private InputValidator validator;
	@Autowired
	private UTDatabase database;

	public void setUp() throws IOException, SQLException {
		database.reload();
	}

	@Test
	public void checkValid() throws UserException, DaoException, DaoConfigException, SQLException {
		validator.check(mapper.stringsToDto("Test", "1994-11-03", "1995-11-03", "Apple Inc."));
		validator.check(mapper.stringsToDto("Test", "1994-11-03", "", ""));
		validator.check(mapper.stringsToDto("Test", "1994-11-03", null, null));
		validator.check(mapper.stringsToDto("Test", "   ", "1995-11-03", "RCA"));
		validator.check(mapper.stringsToDto("Test", null, "1995-11-03", "     "));
		validator.check(mapper.stringsToDto("Test", "", "1995-11-03", "RCA"));
		assert (true);
	}

	@Test
	public void checkEmptyName() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("", null, null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkEmptySpaceName() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("      ", null, null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkNullName() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto(null, null, null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkDiscontinuedBeforeIntroduced()
			throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("Test", "1996-11-03", "1995-11-03", null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkDateInFuture() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("Test", "2220-11-03", null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkDateBefore1970() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("Test", "1960-11-03", null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkDateErrorParsing() throws DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("Test", "2000/11/03", null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

	@Test
	public void checkIfBrandExists() throws DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.stringsToDto("Test", null, null, "Unknown"));
			fail("Should have thrown UserException.");
		} catch (UserException e) {
			assert (true);
		}
	}

}