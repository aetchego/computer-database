package validator;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;

import fr.excilys.client.UserException;
import fr.excilys.controller.Validator;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ValidatorTest {
	
	Validator validator = Validator.getInstance();
	
	@Test
	public void checkValid() throws UserException {
		validator.check("Test", "1994-11-03", "1995-11-03");
		validator.check("Test", "1994-11-03", "");
		validator.check("Test", "1994-11-03", null);
		validator.check("Test", "   ", "1995-11-03");
		validator.check("Test", null, "1995-11-03");
		validator.check("Test", "", "1995-11-03");
		assert(true);
	}
	
	@Test
	public void checkEmptyName() throws UserException {
		try {
			validator.check("", "", "");
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkEmptySpaceName() throws UserException {
		try {
			validator.check("     ", "", "");
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkNullName() throws UserException {
		try {
			validator.check(null, "", "");
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDiscontinuedBeforeIntroduced() throws UserException {
		try {
			validator.check("Test", "1995-11-03", "1995-11-02");
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDateInFuture() throws UserException {
		try {
			validator.check("Test", "2200-11-03", "1995-11-02");
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
	
	@Test
	public void checkDateBefore1970() throws UserException {
		try {
			validator.check("Test", "1960-11-03", "1995-11-02");
			fail("Should have thrown UserException.");
		}
		catch (UserException e) {assert(true);}
	}
}