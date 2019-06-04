package fr.excilys.services.mapper;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import fr.excilys.model.Computer;
import fr.excilys.services.dto.ComputerDTO;
import fr.excilys.services.exception.UserException;
import fr.excilys.services.services.CompanyService;

@Component
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
		if (computerDto.getId() == null)
			computer.setId(0);
		else
			computer.setId(computerDto.getId());
		return computer;
	}
}
