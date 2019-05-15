package fr.excilys.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.dao.DaoConfigException;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.service.ComputerService;
import fr.excilys.validator.InputValidator;

@Component
public class ComputerController {

	private final ComputerService computerService;
	private final InputValidator validator;
	private final ComputerMapper computerMapper;
	private final Logger logger = LoggerFactory.getLogger(ComputerController.class);
	
	public ComputerController(ComputerService computerService, InputValidator validator, ComputerMapper computerMapper) {
		super();
		this.computerService = computerService;
		this.validator = validator;
		this.computerMapper = computerMapper;
	}

	public List<ComputerDTO> listComputers(int offset, int limit) throws UserException {
		return computerService.listComputers(offset, limit).stream().map(computerMapper::beanToDto).collect(Collectors.toList());
	}
	
	public List<ComputerDTO> search(String name, String filter) {
		try {
			return computerService.search(name, filter).stream().map(computerMapper::beanToDto).collect(Collectors.toList());
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return Collections.emptyList();
	}

	public void createComputer(ComputerDTO computer) {
		try {
			validator.check(computer);
			computerService.createComputer(computerMapper.dtoToBean(computer));
		} catch (UserException | SQLException | DaoConfigException e ) {
			logger.info(e.getMessage());
		}
	}

	public ComputerDTO showDetails(int id) throws UserException {
		return computerMapper.beanToDto(computerService.showDetails(id));
	}

	public void deleteComputer(int id) throws UserException {
		computerService.deleteComputer(id);
	}

	public void updateComputer(int id, ComputerDTO computer) {
		try {
			validator.check(computer);
			computerService.updateComputer(id, computerMapper.dtoToBean(computer));
		} catch (UserException | SQLException | DaoConfigException e) {
			logger.info(e.getMessage());
		}
	}
	
	public int countComputers() throws UserException {
		int res = 0;
		res = computerService.countComputers();
		return res;
	}

}