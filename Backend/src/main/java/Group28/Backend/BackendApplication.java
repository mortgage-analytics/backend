package Group28.Backend;

import Group28.Backend.Security.AuthEntryPointJwt;
import Group28.Backend.Security.JwtAuthFilter;
import Group28.Backend.controller.DataController;
import Group28.Backend.controller.LoginController;
import Group28.Backend.domain.DataEntry;
import Group28.Backend.domain.User;
import Group28.Backend.repository.DataRepository;
import Group28.Backend.repository.UserRepository;
import Group28.Backend.service.DataService;
import Group28.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

@SpringBootApplication
public class BackendApplication implements ApplicationRunner
{
	@Autowired
	LoginController loginController;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;


	@Autowired
	DataController dataController;

	@Autowired
	DataService dataService;

	@Autowired
	DataRepository dataRepository;


	@Autowired
	JwtAuthFilter jwtAuthFilter;

	@Autowired
	AuthEntryPointJwt authEntryPointJwt;

	public static void main(String[] args)
	{
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		// Add users
		userRepository.save(new User("byrnel58@tcd.ie", "Test12345_"));

		FileInputStream fileInputStream = new FileInputStream("Backend/src/main/resources/combined_report-1707267132094.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);

		// Assuming there is only one sheet, you might iterate over sheets if needed
		Sheet sheet = workbook.getSheetAt(0);

		dataRepository.deleteAll();

		// Skip the zeroth row, it's just titles
		for(int i = 1; i < 353; i++)
		{
			Row row = sheet.getRow(i);

			DataEntry entry = new DataEntry();
			entry.setLastUpdatedDate(getDateOrNull(row, 0));

			entry.setPrimaryLastLoggedOn(getDateOrNull(row, 5));
			entry.setApplicationType(getStringOrNull(row, 6));

			entry.setApplicationStage(getStringOrNull(row, 8));
			entry.setPropertyIdentified(getStringOrNull(row, 9).equals("Yes"));
			entry.setMortgageAmountProposed(getNumericOrZero(row, 10));
			entry.setLeadCreatedDate(getDateOrNull(row, 11));
			entry.setApplicationCreatedDate(getDateOrNull(row, 12));
			entry.setAdvisorReviewCompleteDate(getDateOrNull(row, 13));
			entry.setSubmissionDate(getDateOrNull(row, 14));
			entry.setResponseDate(getDateOrNull(row, 15));
			entry.setValuationReceivedDate(getDateOrNull(row, 16));
			entry.setLoanOfferReceived(getDateOrNull(row, 17));
			entry.setCompletionDate(getDateOrNull(row, 18));
			entry.setEstimatedClosingDate(getDateOrNull(row, 19));
			entry.setLeadSourceDetails(getStringOrNull(row, 20));
			entry.setApplicationStatus(getStringOrNull(row, 21));
			entry.setSubmittedTo(getStringOrNull(row, 22));

			entry.setSecondaryLastLoggedOn(getDateOrNull(row, 26));
			entry.setLeadSource(getStringOrNull(row, 27));

			entry.setNextActionDetails(getStringOrNull(row, 29));

			entry.setSingle(getStringOrNull(row, 31).equals("Single"));
			entry.setNextAction(getStringOrNull(row, 32));

			dataRepository.save(entry);
		}
	}

	private static String getStringOrNull(Row row, int i)
	{
		Cell c = row.getCell(i);
		if(c == null)
		{
			return null;
		}

		return c.getStringCellValue();
	}

	private static Date getDateOrNull(Row row, int i)
	{
		Cell c = row.getCell(i);
		if(c == null)
		{
			return null;
		}

		return c.getDateCellValue();
	}

	private static double getNumericOrZero(Row row, int i)
	{
		Cell c = row.getCell(i);
		if(c == null)
		{
			return 0.0f;
		}

		return c.getNumericCellValue();
	}
}
