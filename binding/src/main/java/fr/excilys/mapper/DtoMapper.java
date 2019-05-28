package fr.excilys.mapper;

import org.springframework.stereotype.Component;

import fr.excilys.dto.ComputerDTO;
import fr.excilys.model.Computer;

@Component
public class DtoMapper {

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

}
