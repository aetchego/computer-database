package fr.excilys.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.excilys.dto.ComputerDTO;
import fr.excilys.exception.UserException;

public class DatesValidator implements ConstraintValidator<AreValidDates, ComputerDTO> {

	private void compareDates(Optional<LocalDate> inDate, Optional<LocalDate> outDate) throws UserException {
		if (inDate.isPresent() && outDate.isPresent() && inDate.get().isAfter(outDate.get()))
			throw new UserException("[ERROR] Introduced date cannot be after discontinued date.");
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

	@Override
	public boolean isValid(ComputerDTO computer, ConstraintValidatorContext context) {
		Optional<LocalDate> introduced;
		try {
			introduced = this.checkDateParsing(computer.getIntroduced());
			Optional<LocalDate> discontinued = this.checkDateParsing(computer.getDiscontinued());
			this.compareDates(introduced, discontinued);
		} catch (UserException e) {
			context.buildConstraintViolationWithTemplate("Dates are invalid.").addPropertyNode("introduced")
					.addConstraintViolation();
			return false;
		}
		return true;
	}

}
