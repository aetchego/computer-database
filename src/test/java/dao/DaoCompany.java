package dao;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import database.UTDatabase;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class DaoCompany {

	@Before
	public void loadDatabase() throws IOException, SQLException {
		System.out.println("DB initialization..");
		UTDatabase.getInstance().reload();
	}
	
	@Test
	public void test() {
		System.out.println("test");
	}

}
