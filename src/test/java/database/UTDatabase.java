package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.stream.Collectors;

import fr.excilys.dao.DaoFactory;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.CompanyMapper;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Company;

public class UTDatabase {

	private static UTDatabase instance;
	private static final String ENTRIES_SQL = "entriesUT.sql";
    private static final String SCHEMA_SQL = "schema.sql";
    private List<ComputerDTO> computers = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();
    private ComputerMapper computerMapper = ComputerMapper.getInstance();
    private CompanyMapper companyMapper = CompanyMapper.getInstance();
    
	private UTDatabase() {
		this.addCompanies();
		this.addComputers();
		//this.addCompanyToComputer();
	}
	
	public static UTDatabase getInstance() {
		if (instance == null)
			instance = new UTDatabase();
		return instance;
	}
	
	private void addComputers() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		computers.add(computerMapper.StringsToDTO("MacBook Pro 15.4 inch", null, null, null));
		computers.add(computerMapper.StringsToDTO("CM-2a", null, null, ""));
		computers.add(computerMapper.StringsToDTO("CM-200", null, null, "   "));
		computers.add(computerMapper.StringsToDTO("CM-5e", null, null, "RCA"));
		computers.add(computerMapper.StringsToDTO("CM-5", "1991-1-1", null, "Tandy Corporation"));
		computers.add(computerMapper.StringsToDTO("MacBook Pro", "2006-1-10", null, "IMS Associates, Inc."));
		computers.add(computerMapper.StringsToDTO("Apple IIe", null, null, null));
		computers.add(computerMapper.StringsToDTO("Apple IIc", null, null, null));
		computers.add(computerMapper.StringsToDTO("Apple IIGS", null, null, null));
		computers.add(computerMapper.StringsToDTO("Apple IIc Plus", null, null, ""));
		computers.add(computerMapper.StringsToDTO("Apple II Plus", null, null, "     "));
		computers.add(computerMapper.StringsToDTO("Apple III", "1980-5-1", "1984-4-1", "MOS Technology"));
		computers.add(computerMapper.StringsToDTO("Apple Lisa", null, null, "Micro Instrumentation and Telemetry Systems"));
		computers.add(computerMapper.StringsToDTO("CM-2", null, null, " "));
		computers.add(computerMapper.StringsToDTO("Connection Machine", "1987-1-1", null, "Micro Instrumentation and Telemetry Systems"));
		computers.add(computerMapper.StringsToDTO("Apple II", "1977-4-1", "1993-10-1", null));
		computers.add(computerMapper.StringsToDTO("Apple III Plus", "1983-12-1", "1984-4-1", "MOS Technology"));
		computers.add(computerMapper.StringsToDTO("COSMAC ELF", null, null, null));
		computers.add(computerMapper.StringsToDTO("COSMAC VIP", "1977-1-1", null, "    "));
		computers.add(computerMapper.StringsToDTO("ELF II", "1977-1-1", null, null));
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
	
	/*private void addCompanyToComputer() {
		for (ComputerDTO e : computers) {
			if (e.getBrand() != null || !e.getBrand().trim().isEmpty())
				e.setBrand(this.companies.get((e.getBrand()).getName());
		}
	}*/
	
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
	
	public List<ComputerDTO> readComputers() {
		return this.computers;
	}
	
	public List<ComputerDTO> readComputers(int offset, int limit) {
		return this.readComputers().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}
	
	public void deleteComputer(int id) {
		this.computers.remove(id - 1);
	}
	
	public ComputerDTO selectComputerById(int id) {
		ComputerDTO computer = null;
		id--;
		if (id < computers.size())
			computer = computers.get(id);
		return computer;
	}
	
	public int countComputers() {
		return computers.size();
	}
	
	public void createComputer(ComputerDTO computer) {
		computers.add(computer);
	}
	
	public void updateComputer(ComputerDTO computer, int id) {
		id--;
		this.computers.remove(id);
		this.computers.add(id, computer);
		//this.addCompanyToComputer();
	}
}
