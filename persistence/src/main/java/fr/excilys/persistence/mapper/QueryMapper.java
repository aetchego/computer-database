package fr.excilys.persistence.mapper;

import org.springframework.stereotype.Component;

@Component
public class QueryMapper {

	private static final String SELECT = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.name AS company_name, computer.company_id\n"
			+ "from company\n" + "RIGHT JOIN computer\n" + "ON company.id = computer.company_id";
	private static final String FILTER_BY = " WHERE computer.name LIKE ? OR company.name LIKE ?";
	private static final String ORDER_BY = " ORDER BY ";

	public String toSqlQuery(String name, String order, String sens) {
		String query = SELECT;
		if (order == null)
			order = "computer.id";
		if (sens == null)
			sens = "ASC";
		if (name != null && !name.isEmpty())
			query += FILTER_BY;
		query += ORDER_BY + order + " " + sens + " LIMIT ? OFFSET ?";
		return query;
	}

}
