package fr.excilys.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		List<ComputerDTO> computersDTO = new ArrayList<>();
		List<Computer> computers = computerService.listComputers(offset, limit);
		for (Computer e : computers)
			computersDTO.add(computerMapper.BeanToDTO(e));
		return computersDTO;
	}
	
	public List<Computer> search(String name, String filter) {
		List<Computer> computers = new ArrayList<>();
		try {
			computers = computerService.search(name, filter);
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return computers;
	}

	public void createComputer(ComputerDTO computer) {
		
		try {
			validator.check(computer);
			computerService.createComputer(computerMapper.DTOtoBean(computer));
		} catch (UserException | SQLException e ) {
			logger.info(e.getMessage());
		}
	}

	public void showDetails(int id) throws UserException {
		computerService.showDetails(id);
	}

	public void deleteComputer(int id) throws UserException {
		computerService.deleteComputer(id);
	}

	public void updateComputer(ComputerDTO computer, int id) {
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