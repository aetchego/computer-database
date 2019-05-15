package fr.excilys.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import fr.excilys.mapper.CompanyMapper;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@Component
public class UTDatabase {

	private static final String ENTRIES_SQL = "entriesUT.sql";
	private static final String SCHEMA_SQL = "schema.sql";
	private final CompanyMapper companyMapper;
	private final DataSource dataSource;
	private Map<Integer, Computer> computers = new TreeMap<>();
	private Map<Integer, Company> companies = new TreeMap<>();

	public UTDatabase(CompanyMapper companyMapper, DataSource dataSource) {
		super();
		this.companyMapper = companyMapper;
		this.dataSource = dataSource;
		this.addCompanies();
		this.addComputers();
	}

	private void addComputer(int id, String name, Date introduced, Date discontinued, Integer companyId) {
		Computer computer = new Computer();
		computer.setId(id);
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		Optional.ofNullable(companyId).map(companies::get).ifPresent(computer::setCompany);
		computers.put(id, computer);
	}

	private void addComputers() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		addComputer(1, "MacBook Pro 15.4 inch", null, null, 1);
		addComputer(2, "CM-2a", null, null, 2);
		addComputer(3, "CM-200", null, null, 2);
		addComputer(4, "CM-5e", null, null, 2);
		addComputer(5, "CM-5", Date.valueOf("1991-1-1"), null, 2);
		addComputer(6, "MacBook Pro", Date.valueOf("2006-1-10"), null, 1);
		addComputer(7, "Apple IIe", null, null, null);
		addComputer(8, "Apple IIc", null, null, null);
		addComputer(9, "Apple IIGS", null, null, null);
		addComputer(10, "Apple IIc Plus", null, null, null);
		addComputer(11, "Apple II Plus", null, null, null);
		addComputer(12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"), 1);
		addComputer(13, "Apple Lisa", null, null, 1);
		addComputer(14, "CM-2", null, null, 2);
		addComputer(15, "Connection Machine", Date.valueOf("1987-1-1"), null, 2);
		addComputer(16, "Apple II", Date.valueOf("1977-4-1"), Date.valueOf("1993-10-1"), 1);
		addComputer(17, "Apple III Plus", Date.valueOf("1983-12-1"), Date.valueOf("1984-4-1"), 1);
		addComputer(18, "COSMAC ELF", null, null, 3);
		addComputer(19, "COSMAC VIP", Date.valueOf("1977-1-1"), null, 3);
		addComputer(20, "ELF II", Date.valueOf("1977-1-1"), null, 4);
	}

	private void addCompany(int id, String name) {
		Company company = companyMapper.toBean(id, name);
		companies.put(id, company);
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

	private void executeScript(String filename) throws SQLException, IOException {
		try (final Connection connection = dataSource.getConnection();
				final Statement statement = connection.createStatement();
				final InputStream resourceAsStream = UTDatabase.class.getClassLoader().getResourceAsStream(filename);
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
		computers.clear();
		companies.clear();
		addCompanies();
		addComputers();
	}

	public List<Company> readCompanies() {
		return List.copyOf(this.companies.values());
	}

	public List<Computer> readComputers() {
		return List.copyOf(this.computers.values());
	}

	public List<Computer> readComputers(int offset, int limit) {
		return this.readComputers().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

	public void deleteComputer(int id) {
		this.computers.remove(id);
	}

	public Optional<Computer> selectComputerById(int id) {
		return Optional.ofNullable(computers.get(id));
	}

	public int countComputers() {
		return computers.size();
	}

	public void createComputer(Computer computer) {
		computer.setId(computers.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1);
		computers.put(computer.getId(), computer);
	}

	public void updateComputer(Computer computer, int id) {
		this.computers.remove(id);
		this.computers.put(id, computer);
	}
}