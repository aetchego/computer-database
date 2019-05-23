package fr.excilys.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.model.Company;
import fr.excilys.service.CompanyService;

@Component
public class InputValidator {

	private final CompanyService companyService;

	public InputValidator(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	public void check(ComputerDTO computer) throws UserException {
		this.checkName(computer.getName());
		Optional<LocalDate> introduced = this.checkDateParsing(computer.getIntroduced());
		Optional<LocalDate> discontinued = this.checkDateParsing(computer.getDiscontinued());
		this.compareDates(introduced, discontinued);
		this.checkCompanyName(computer.getBrand());
	}

	private void checkCompanyName(String brand) throws UserException {
		if (brand == null || brand.trim().isEmpty() || brand.equals("---"))
			return;
		List<Company> comp = companyService.search().getCompaniesList();
		for (Company e : comp)
			if (e.getName().equals(brand))
				return;
		throw new UserException("[ERROR] Company name does not exist.");
	}

	private Optional<LocalDate> checkDateParsing(String date) throws UserException {
		if (date == null || date.trim().isEmpty())
			return Optional.empty();
		try {
			LocalDate parsed = LocalDate.parse(date);
			if (parsed.isAfter(LocalDate.now()))
				throw new UserException("[ERROR] Date cannot be after today.");
			if (parsed.isBefore(LocalDate.parse("1970-01-01")))
				throw new UserException("[ERROR] Date cannot be before 01/01/1970.");
			return Optional.of(parsed);
		} catch (DateTimeParseException e) {
			throw new UserException("[ERROR] Date parsing exception.");
		}
	}

	private void checkName(String name) throws UserException {
		if (name == null || name.trim().isEmpty())
			throw new UserException("[ERROR] Name cannot be empty.");
	}

	private void compareDates(Optional<LocalDate> inDate, Optional<LocalDate> outDate) throws UserException {
		if (inDate.isPresent() && outDate.isPresent() && inDate.get().isAfter(outDate.get()))
			throw new UserException("[ERROR] Introduced date cannot be after discontinued date.");
	}
}
