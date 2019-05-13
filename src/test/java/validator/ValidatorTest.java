package validator;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;

import fr.excilys.client.UserException;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dao.DaoException;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.validator.InputValidator;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidatorTest {
	
	private ComputerMapper mapper = ComputerMapper.getInstance();
	
	InputValidator validator = InputValidator.getInstance();
	
	@Test
	public void checkValid() throws UserException, DaoException, DaoConfigException, SQLException {
		validator.check(mapper.StringsToDTO("Test", "1994-11-03", "1995-11-03", "Apple Inc."));
		validator.check(mapper.StringsToDTO("Test", "1994-11-03", "", ""));
		validator.check(mapper.StringsToDTO("Test", "1994-11-03", null, null));
		validator.check(mapper.StringsToDTO("Test", "   ", "1995-11-03", "RCA"));
		validator.check(mapper.StringsToDTO("Test", null, "1995-11-03", "     "));
		validator.check(mapper.StringsToDTO("Test", "", "1995-11-03", "RCA"));
		assert(true);
	}
	
	@Test
	public void checkEmptyName() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("", null, null, null));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkEmptySpaceName() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("      ", null, null, null));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkNullName() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO(null, null, null, null));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDiscontinuedBeforeIntroduced() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("Test", "1996-11-03", "1995-11-03", null));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDateInFuture() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("Test", "2220-11-03", null, null));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDateBefore1970() throws UserException, DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("Test", "1960-11-03", null, null));
			fail("Should have thrown UserException.");
		} catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDateErrorParsing() throws DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("Test", "2000/11/03", null, null));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkIfBrandExists() throws DaoException, DaoConfigException, SQLException {
		try {
			validator.check(mapper.StringsToDTO("Test", null, null, "Unknown"));
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	
}