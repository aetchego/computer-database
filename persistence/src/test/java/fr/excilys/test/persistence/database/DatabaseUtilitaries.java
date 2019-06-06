package fr.excilys.test.persistence.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@Component
public class DatabaseUtilitaries {
	private static final String ENTRIES_SQL = "entriesUT.sql";
	private static final String SCHEMA_SQL = "schema.sql";
	private static DataSource dataSource;

	private Map<Integer, Company> companies = new TreeMap<>();
	private Map<Integer, Computer> computers = new TreeMap<>();

	public DatabaseUtilitaries(DataSource dataSource) {
		super();
		addCompanies();
		addComputers();
		DatabaseUtilitaries.dataSource = dataSource;

	}

	private void addCompanies() {
		addCompany(1, "Apple Inc.");
		addCompany(2, "Thinking Machines");
		addCompany(3, "RCA");
		addCompany(4, "Netronics");
		addCompany(5, "Tandy Corporation");
		addCompany(6, "Commodore International");
		addCompany(7, "MOS Technology");
		addCompany(8, "Micro Instrumentation and Telemetry Systems");
		addCompany(9, "IMS Associates, Inc.");
		addCompany(10, "Digital Equipment Corporation");
	}

	private void addCompany(int id, String name) {
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		companies.put(id, company);
	}

	public Computer createComputer(int id, String name, Date introduced, Date discontinued, int companyId) {

		Computer computer = new Computer();
		computer.setId(id);
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setCompany(this.companies.get(companyId));
		return computer;
	}

	private void addComputers() {
		computers.put(1, createComputer(1, "MacBook Pro 15.4 inch", null, null, 1));
		computers.put(2, createComputer(2, "CM-2a", null, null, 2));
		computers.put(3, createComputer(3, "CM-200", null, null, 2));
		computers.put(4, createComputer(4, "CM-5e", null, null, 2));
		computers.put(5, createComputer(5, "CM-5", Date.valueOf("1991-1-1"), null, 2));
		computers.put(6, createComputer(6, "MacBook Pro", Date.valueOf("2006-1-10"), null, 1));
		computers.put(7, createComputer(7, "Apple IIe", null, null, 10));
		computers.put(8, createComputer(8, "Apple IIc", null, null, 10));
		computers.put(9, createComputer(9, "Apple IIGS", null, null, 10));
		computers.put(10, createComputer(10, "Apple IIc Plus", null, null, 8));
		computers.put(11, createComputer(11, "Apple II Plus", null, null, 7));
		computers.put(12, createComputer(12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"), 1));
		computers.put(13, createComputer(13, "Apple Lisa", null, null, 1));
		computers.put(14, createComputer(14, "CM-2", null, null, 2));
		computers.put(15, createComputer(15, "Connection Machine", Date.valueOf("1987-1-1"), null, 2));
		computers.put(16, createComputer(16, "Apple II", Date.valueOf("1977-4-1"), Date.valueOf("1993-10-1"), 1));
		computers.put(17, createComputer(17, "Apple III Plus", Date.valueOf("1983-12-1"), Date.valueOf("1984-4-1"), 1));
		computers.put(18, createComputer(18, "COSMAC ELF", null, null, 3));
		computers.put(19, createComputer(19, "COSMAC VIP", Date.valueOf("1977-1-1"), null, 3));
		computers.put(20, createComputer(20, "ELF II", Date.valueOf("1977-1-1"), null, 4));
	}

	private static void executeScript(String filename) throws SQLException, IOException {
		try (Connection connection = dataSource.getConnection();
				final Statement statement = connection.createStatement();
				final InputStream resourceAsStream = DatabaseUtilitaries.class.getClassLoader()
						.getResourceAsStream(filename);
				final Scanner scanner = new Scanner(resourceAsStream)) {

			StringBuilder sb = new StringBuilder();
			while (scanner.hasNextLine()) {
				final String nextLine = scanner.nextLine();
				sb.append(nextLine);
			}
			final StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), ";");

			while (stringTokenizer.hasMoreTokens()) {
				final String nextToken = stringTokenizer.nextToken();
				statement.execute(nextToken);
			}
		}
	}

	public void reload() throws IOException, SQLException {
		executeScript(SCHEMA_SQL);
		executeScript(ENTRIES_SQL);
	}

	public Map<Integer, Company> getCompanies() {
		return this.companies;
	}

	public Map<Integer, Computer> getComputers() {
		return this.computers;
	}

	public List<Computer> findAll() {
		return this.computers.values().stream().collect(Collectors.toList());
	}

	public Computer findById(int id) throws DataAccessException {
		if (!computers.containsKey(id))
			throw new DataAccessException("ID does not exist") {
				private static final long serialVersionUID = 1L;
			};
		return computers.get(id);
	}

	public long count() {
		return computers.size();
	}

	public void deleteById(Integer id) {
		computers.remove(id);
	}

	public void update(Computer computer) {
		this.computers.replace(computer.getId(), computer);
	}

	public Long countByComputerOrCompanyName(String name) {
		return computers.entrySet().stream()
				.filter(e -> e.getValue().getName().equals(name) || e.getValue().getCompany().getName().equals(name))
				.count();
	}
}
