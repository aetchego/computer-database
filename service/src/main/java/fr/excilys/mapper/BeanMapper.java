package fr.excilys.mapper;

import java.sql.Date;
import java.time.LocalDate;

import fr.excilys.dto.ComputerDTO;
import fr.excilys.exception.UserException;
import fr.excilys.model.Computer;
import fr.excilys.service.CompanyService;

public class BeanMapper {

	private final CompanyService companyService;

	public BeanMapper(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	public Computer dtoToBean(ComputerDTO computerDto) throws UserException {
		Computer computer = new Computer();
		if (!computerDto.getIntroduced().isEmpty())
			computer.setIntroduced(Date.valueOf(LocalDate.parse(computerDto.getIntroduced())));
		if (!computerDto.getDiscontinued().isEmpty())
			computer.setDiscontinued(Date.valueOf(LocalDate.parse(computerDto.getDiscontinued())));
		companyService.findByName(computerDto.getBrand()).ifPresent(computer::setCompany);
		computer.setName(computerDto.getName());
		return computer;
	}
}
