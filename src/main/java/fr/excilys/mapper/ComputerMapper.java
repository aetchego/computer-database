package fr.excilys.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.excilys.dao.DaoFactory;
import fr.excilys.dto.ComputerDTO;
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
	
	public ComputerDTO StringsToDTO(String name, String introduced, String discontinued, String brand) {
		ComputerDTO computer = new ComputerDTO();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setBrand(brand);
		return computer;
	}

	public Computer DTOtoBean(ComputerDTO computerDto) throws SQLException {
		Computer computer = new Computer();
		if (!computerDto.getIntroduced().isEmpty())
			computer.setIntroduced( Date.valueOf(LocalDate.parse(computerDto.getIntroduced())));
		if (!computerDto.getDiscontinued().isEmpty())
			computer.setDiscontinued(Date.valueOf(LocalDate.parse(computerDto.getDiscontinued())));
		if (!computerDto.getBrand().isEmpty())
			computer.setCompany(computerDto.getBrand(), DaoFactory.getInstance().getCompany().read());
		computer.setName(computerDto.getName());
		return computer;
	}
	
	public ComputerDTO BeanToDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(computer.getName());
		computerDTO.setIntroduced(String.valueOf(computer.getIntroduced()));
		computerDTO.setDiscontinued(String.valueOf(computer.getDiscontinued()));
		computerDTO.setId(computer.getId());
		if (computer.getCompany() != null)
			computerDTO.setBrand(computer.getCompany().getName());
		return computerDTO;
	}
	
	public Computer DBtoBean(ResultSet rs) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced(rs.getDate("introduced"));
		computer.setDiscontinued(rs.getDate("discontinued"));
		computer.setCompany(rs.getString("company_name"), DaoFactory.getInstance().getCompany().read());
		return computer;
	}
}
