package fr.excilys.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.model.Computer;
import fr.excilys.service.CompanyService;

@Component
public class ComputerMapper {

	private final CompanyService companyService;

	public ComputerMapper(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	public ComputerDTO stringsToDto(String name, String introduced, String discontinued, String brand) {
		ComputerDTO computer = new ComputerDTO();
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setBrand(brand);
		return computer;
	}

	public Computer dtoToBean(ComputerDTO computerDto) throws UserException {
		Computer computer = new Computer();
		if (!computerDto.getIntroduced().isEmpty())
			computer.setIntroduced(Date.valueOf(LocalDate.parse(computerDto.getIntroduced())));
		if (!computerDto.getDiscontinued().isEmpty())
			computer.setDiscontinued(Date.valueOf(LocalDate.parse(computerDto.getDiscontinued())));
		if (!computerDto.getBrand().isEmpty())
			computer.setCompany(computerDto.getBrand(), companyService.listCompanies());
		computer.setName(computerDto.getName());
		return computer;
	}

	public ComputerDTO beanToDto(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(computer.getName());
		computerDTO.setIntroduced(String.valueOf(computer.getIntroduced()));
		computerDTO.setDiscontinued(String.valueOf(computer.getDiscontinued()));
		computerDTO.setId(computer.getId());
		if (computer.getCompany() != null)
			computerDTO.setBrand(computer.getCompany().getName());
		return computerDTO;
	}

	public Computer dbToBean(ResultSet rs) throws SQLException, UserException {
		Computer computer = new Computer();
		computer.setId(rs.getInt("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroduced(rs.getDate("introduced"));
		computer.setDiscontinued(rs.getDate("discontinued"));
		computer.setCompany(rs.getString("company_name"), companyService.listCompanies());
		return computer;
	}
}
