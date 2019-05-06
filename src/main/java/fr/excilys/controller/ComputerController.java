package fr.excilys.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.excilys.client.UserException;
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

	public List<Computer> listComputers(int offset, int limit) throws UserException {
		return (computerService.listComputers(offset, limit));
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
	
	public List<Computer> searchByCompany(String name) {
		List<Computer> computers = new ArrayList<>();
		return computers;
	}

	public void createComputer(String name, String inDate, String outDate, String brand) {
		
		try {
			validator.check(name, inDate, outDate);
			computerService.createComputer(computerMapper.toBean(name, inDate, outDate), brand);
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

	public void updateComputer(String name, String inDate, String outDate, String brand, int id) {
		try {
			validator.check(name, inDate, outDate);
			computerService.updateComputer(computerMapper.toBean(name, inDate, outDate), brand, id);
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