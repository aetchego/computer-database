package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.stream.Collectors;

import fr.excilys.dao.DaoFactory;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;

public class UTDatabase {

	private static UTDatabase instance;
	private static final String ENTRIES_SQL = "entriesUT.sql";
    private static final String SCHEMA_SQL = "schema.sql";
    private List<Computer> computers = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();
    private ComputerMapper computerMapper = ComputerMapper.getInstance();
    private CompanyMapper companyMapper = CompanyMapper.getInstance();
    
	private UTDatabase() {
		this.addCompanies();
		this.addComputers();
		this.addCompanyToComputer();
	}
	
	public static UTDatabase getInstance() {
		if (instance == null)
			instance = new UTDatabase();
		return instance;
	}
	
	private void addComputers() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		computers.add(computerMapper.toBean(1, "MacBook Pro 15.4 inch", null, null, 1));
		computers.add(computerMapper.toBean(2, "CM-2a", null, null, 2));
		computers.add(computerMapper.toBean(3, "CM-200", null, null, 2));
		computers.add(computerMapper.toBean(4, "CM-5e", null, null, 2));
		computers.add(computerMapper.toBean(5, "CM-5", Date.valueOf("1991-1-1"), null, 2));
		computers.add(computerMapper.toBean(6, "MacBook Pro",  Date.valueOf("2006-1-10"), null, 1));
		computers.add(computerMapper.toBean(7, "Apple IIe", null, null, null));
		computers.add(computerMapper.toBean(8, "Apple IIc", null, null, null));
		computers.add(computerMapper.toBean(9, "Apple IIGS", null, null, null));
		computers.add(computerMapper.toBean(10, "Apple IIc Plus", null, null, null));
		computers.add(computerMapper.toBean(11, "Apple II Plus", null, null, null));
		computers.add(computerMapper.toBean(12, "Apple III",  Date.valueOf("1980-5-1"),  Date.valueOf("1984-4-1"), 1));
		computers.add(computerMapper.toBean(13, "Apple Lisa", null, null, 1));
		computers.add(computerMapper.toBean(14, "CM-2", null, null, 2));
		computers.add(computerMapper.toBean(15, "Connection Machine", Date.valueOf("1987-1-1"), null, 2));
		computers.add(computerMapper.toBean(16, "Apple II", Date.valueOf("1977-4-1"), Date.valueOf("1993-10-1"), 1));
		computers.add(computerMapper.toBean(17, "Apple III Plus", Date.valueOf("1983-12-1"), Date.valueOf("1984-4-1"), 1));
		computers.add(computerMapper.toBean(18, "COSMAC ELF", null, null, 3));
		computers.add(computerMapper.toBean(19, "COSMAC VIP", Date.valueOf("1977-1-1"), null, 3));
		computers.add(computerMapper.toBean(20, "ELF II", Date.valueOf("1977-1-1"), null, 4));
	}
	
	private void addCompanies() {
		companies.add(companyMapper.toBean(1, "Apple Inc."));
        companies.add(companyMapper.toBean(2, "Thinking Machines"));
        companies.add(companyMapper.toBean(3, "RCA"));
        companies.add(companyMapper.toBean(4, "Netronics"));
        companies.add(companyMapper.toBean(5, "Tandy Corporation"));
        companies.add(companyMapper.toBean(6, "Commodore International"));
        companies.add(companyMapper.toBean(7, "MOS Technology"));
        companies.add(companyMapper.toBean(8, "Micro Instrumentation and Telemetry Systems"));
        companies.add(companyMapper.toBean(9, "IMS Associates, Inc."));
        companies.add(companyMapper.toBean(10, "Digital Equipment Corporation"));
		
	}
	
	private void addCompanyToComputer() {
		for (Computer e : computers) {
			/*System.out.println(e.getCompanyId() - 1);
			System.out.println(this.companies.get((e.getCompanyId() - 1)).getName());*/
			if (e.getCompanyId() != null)
				e.setBrand(this.companies.get((e.getCompanyId() - 1)).getName());
		}
	}
	
	private static void executeScript(String filename) throws SQLException, IOException {
        try (final Connection connection = DaoFactory.getInstance().getConnection();
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
    }
	
	public List<Company> readCompanies() {
		return this.companies;
	}
	
	public List<Computer> readComputers() {
		return this.computers;
	}
	
	public List<Computer> readComputers(int offset, int limit) {
		return this.readComputers().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}
	
	public void deleteComputer(int id) {
		this.computers.remove(id + 1);
	}
}
