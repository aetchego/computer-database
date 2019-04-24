package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Companies;
import model.Company;

public class CompanyMapper {

	private static CompanyMapper instance = null;
	
	private CompanyMapper() {
		
	}
	
	public static CompanyMapper getInstance() {
		if (instance == null)
			instance = new CompanyMapper();
		return instance;
	}
	
	public void toBean(ResultSet rs, Companies companies) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		companies.addCompany(company);
	}
}
