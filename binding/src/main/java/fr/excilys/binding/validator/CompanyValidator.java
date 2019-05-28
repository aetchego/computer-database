package fr.excilys.binding.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.excilys.binding.exception.UserException;
import fr.excilys.model.Company;
import fr.excilys.persistence.dao.CompanyDao;

public class CompanyValidator implements ConstraintValidator<isCompany, String> {

	private CompanyDao dao;

	public CompanyValidator(CompanyDao dao) {
		super();
		this.dao = dao;
	}

	private void checkCompanyName(String brand) throws UserException {
		if (brand == null || brand.trim().isEmpty() || brand.equals("---"))
			return;
		List<Company> comp = dao.search().getCompaniesList();
		for (Company e : comp)
			if (e.getName().equals(brand))
				return;
		throw new UserException("[ERROR] Company name does not exist.");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			this.checkCompanyName(value);
		} catch (UserException e) {
			return false;
		}
		return true;
	}

}
