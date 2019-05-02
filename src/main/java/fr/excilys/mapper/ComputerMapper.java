package fr.excilys.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fr.excilys.dao.ComputerDao;
import fr.excilys.dao.DaoException;
import fr.excilys.dao.DaoFactory;
import fr.excilys.model.Companies;
import fr.excilys.model.Computer;

public class ComputerMapper {

	private static ComputerMapper instance = null;

	private ComputerMapper() {

	}

	public static ComputerMapper getInstance() {
		if (instance == null)
			instance = new ComputerMapper();
		return instance;
	}

	public Computer toBean(String name, String inDate, String outDate) throws SQLException {
		Computer computer = new Computer();
		if (!inDate.isBlank())
			computer.setIntroduced( Date.valueOf(LocalDate.parse(inDate)));
		if (!outDate.isBlank())
			computer.setDiscontinued(Date.valueOf(LocalDate.parse(outDate)));
		computer.setName(name);
		return (computer);
	}
	
	public Computer toBean(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setBrand(rs.getString("company_name"));
		computer.setCompanyId(rs.getInt("company_id"));
		computer.setIntroduced(rs.getDate("introduced"));
		computer.setDiscontinued(rs.getDate("discontinued"));
		return computer;
	}
}
