package fr.excilys.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.excilys.client.UserException;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Computer;
import fr.excilys.service.ComputerService;

public class ComputerController {

	private static ComputerController instance = null;
	private ComputerService computerService = ComputerService.getInstance();
	private Validator validator = Validator.getInstance();
	private ComputerMapper computerMapper = ComputerMapper.getInstance();
	private Logger logger = LoggerFactory.getLogger(ComputerController.class);

	private ComputerController() {
	}

	public static ComputerController getInstance() {
		if (instance == null)
			instance = new ComputerController();
		return instance;
	}

	public List<ComputerDTO> listComputers(int offset, int limit) throws UserException {
		return computerService.listComputers(offset, limit).stream().map(computerMapper::BeanToDTO).collect(Collectors.toList());
	}
	
	public List<ComputerDTO> search(String name, String filter) {
		try {
			return computerService.search(name, filter).stream().map(computerMapper::BeanToDTO).collect(Collectors.toList());
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return Collections.emptyList();
	}

	public void createComputer(ComputerDTO computer) {
		try {
			validator.check(computer);
			computerService.createComputer(computerMapper.DTOtoBean(computer));
		} catch (UserException | SQLException e ) {
			logger.info(e.getMessage());
		}
	}

	public ComputerDTO showDetails(int id) throws UserException {
		return computerMapper.BeanToDTO(computerService.showDetails(id));
	}

	public void deleteComputer(int id) throws UserException {
		computerService.deleteComputer(id);
	}

	public void updateComputer(int id, ComputerDTO computer) {
		try {
			validator.check(computer);
			computerService.updateComputer(id, computerMapper.DTOtoBean(computer));
		} catch (UserException | SQLException e) {
			logger.info(e.getMessage());
		}
	}
	
	public int countComputers() throws UserException {
		int res = 0;
		res = computerService.countComputers();
		return res;
	}

}