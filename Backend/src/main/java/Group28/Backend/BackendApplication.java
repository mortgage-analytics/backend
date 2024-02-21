package Group28.Backend;

import Group28.Backend.Security.AuthEntryPointJwt;
import Group28.Backend.Security.JwtAuthFilter;
import Group28.Backend.controller.DataController;
import Group28.Backend.controller.LoginController;
import Group28.Backend.domain.Application;
import Group28.Backend.domain.Lead;
import Group28.Backend.domain.User;
import Group28.Backend.repository.ApplicationRepository;
import Group28.Backend.repository.LeadRepository;
import Group28.Backend.repository.UserRepository;
import Group28.Backend.service.ApplicationService;
import Group28.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.apache.poi.ss.usermodel.*;

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
	ApplicationService applicationService;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	LeadRepository leadRepository;


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

		FileInputStream fileInputStream = new FileInputStream("./src/main/resources/combined_report-1707267132094.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);

		loadApplications(workbook, applicationRepository);
		loadLeads(workbook, leadRepository);
	}

	private static void loadApplications(Workbook workbook, ApplicationRepository applicationRepository)
	{
		Sheet sheet = workbook.getSheetAt(0);

		applicationRepository.deleteAll();

		// Skip the zeroth row, it's just titles
		for(int i = 1; i < 353; i++)
		{
			Row row = sheet.getRow(i);

			Application entry = new Application();
			entry.setLastUpdatedDate(getDateOrNull(row, 0));

			entry.setPrimaryLastLoggedOn(getDateOrNull(row, 5));
			entry.setApplicationType(getStringOrEmpty(row, 6));

			entry.setApplicationStage(getStringOrEmpty(row, 8));
			entry.setPropertyIdentified(getStringOrEmpty(row, 9).equals("Yes"));
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
			entry.setLeadSourceDetails(getStringOrEmpty(row, 20));
			entry.setApplicationStatus(getStringOrEmpty(row, 21));
			entry.setSubmittedTo(getStringOrEmpty(row, 22));

			entry.setSecondaryLastLoggedOn(getDateOrNull(row, 26));
			entry.setLeadSource(getStringOrEmpty(row, 27));

			entry.setNextActionDetails(getStringOrEmpty(row, 29));

			entry.setSingle(getStringOrEmpty(row, 31).equals("Single"));
			entry.setNextAction(getStringOrEmpty(row, 32));

			applicationRepository.save(entry);
		}
	}

	private static void loadLeads(Workbook workbook, LeadRepository leadRepository)
	{
		Sheet sheet = workbook.getSheetAt(1);

		leadRepository.deleteAll();

		for(int i = 1; i < 71; i++)
		{
			Row row = sheet.getRow(i);

			Lead lead = new Lead();

			lead.setModifiedDate(getDateOrNull(row, 0));
			lead.setMortgageTypeDetail(getStringOrEmpty(row, 1));
			lead.setMortgageType(getStringOrEmpty(row, 2));

			lead.setLoanAmount(getNumericOrZero(row, 8));
			lead.setPropertyKnown(getStringOrEmpty(row, 9).equals("Yes"));
			lead.setCreatedDate(getDateOrNull(row, 10));
			lead.setNextActionDate(getDateOrNull(row, 11));
			lead.setLeadSource(getStringOrEmpty(row, 12));
			lead.setLeadSourceDetail(getStringOrEmpty(row, 13));
			lead.setStatus(getStringOrEmpty(row, 14));
			lead.setAdditionalInfo(getStringOrEmpty(row, 15));

			leadRepository.save(lead);
		}
	}

	private static String getStringOrEmpty(Row row, int i)
	{
		Cell c = row.getCell(i);
		if(c == null)
		{
			return "";
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
