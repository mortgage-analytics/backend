package Group28.Backend.service;

import Group28.Backend.domain.Application;
import Group28.Backend.domain.Count;
import Group28.Backend.domain.MonthlyCount;
import Group28.Backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService
{
  @Autowired
  ApplicationRepository applicationRepository;

  private List<MonthlyCount> monthlyCounts = new ArrayList<>();
  private List<Count> valueCounts = new ArrayList<>();

  public ApplicationService(ApplicationRepository applicationRepository)
  {
    this.applicationRepository = applicationRepository;

    updateCounts();
  }

  public List<Application> getAll()
  {
    return applicationRepository.findAll();
  }

  public List<Application> getSuccessful()
  {
    return applicationRepository.findByApplicationStatusAndCompletionDateIsNotNull("successful");
  }

  public List<Application> getFailed()
  {
    return applicationRepository.findByApplicationStatus("failed");
  }

  public List<Application> getOngoing()
  {
    return applicationRepository.findByApplicationStatusAndCompletionDateIsNull("ongoing");
  }

  public List<Application> getAfter(Date date)
  {
    return applicationRepository.findByCompletionDateAfter(date);
  }

  public List<Application> getBetween(Date start, Date end)
  {
    return applicationRepository.findAllByApplicationCreatedDateBetween(start, end);
  }

  public int getCountByType(String type)
  {
    return applicationRepository.countApplicationsByApplicationType(type);
  }
  public int getCountByStatus(String status)
  {
    return applicationRepository.countApplicationsByApplicationStatus(status);
  }

  public int getCountByStage(String stage)
  {
    return applicationRepository.countApplicationsByApplicationStage(stage);
  }


  public int getCountBetweenValue(double low, double high)
  {
    return applicationRepository.countApplicationsByMortgageAmountProposedBetween(low, high);
  }

  public double getValuePerType(String type)
  {
    List<Application> byType = applicationRepository.findByApplicationType(type);

    double total = 0;
    for(Application application : byType)
    {
      total += application.getMortgageAmountProposed();
    }

    return total;
  }

  public double getValuePerStatus(String status)
  {
    List<Application> byType = applicationRepository.findByApplicationStatus(status);

    double total = 0;
    for(Application application : byType)
    {
      total += application.getMortgageAmountProposed();
    }

    return total;
  }

  public double getValuePerTypeAndStatus(String type, String status)
  {
    List<Application> byType = applicationRepository.findByApplicationTypeAndApplicationStatus(type, status);

    double total = 0;
    for(Application application : byType)
    {
      total += application.getMortgageAmountProposed();
    }

    return total;
  }

  public List<MonthlyCount> getMonthlyCounts()
  {
    return monthlyCounts;
  }

  public List<Count> getValueCounts()
  {
    return valueCounts;
  }

  // Once per 10 minutes
  @Scheduled(fixedRate = 1000 * 60 * 10)
  private void updateCounts()
  {
    updateMonthlyCounts();
    updateAmountCounts();
  }

  private void updateMonthlyCounts()
  {
    List<MonthlyCount> counts = new ArrayList<>();

    // Start and end dates are arbitrary, they just need to encompass all dates
    Calendar current = Calendar.getInstance();
    current.set(2000, Calendar.JANUARY, 1);

    Calendar end = Calendar.getInstance();
    end.set(2025, Calendar.JANUARY, 1);

    // Only get data that is relevant. No need to include any before our first recorded applications
    boolean firstFound = false;
    while (current.before(end))
    {
      Calendar next = Calendar.getInstance();
      next.setTime(current.getTime());
      next.add(Calendar.MONTH, 1);

      int applications = getBetween(current.getTime(), next.getTime()).size();
      int leads = getBetween(current.getTime(), next.getTime()).size();

      if(firstFound || applications > 0 || leads > 0)
      {
        String name = current.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + current.get(Calendar.YEAR);
        counts.add(new MonthlyCount(name, leads, applications));

        firstFound = true;
      }

      current = next;
    }

    // Trim the end off. No need to include any after our last recorded applications.
    for(int i = counts.size() - 1; i >= 0; i--)
    {
      if(counts.get(i).getApplications() <= 0 || counts.get(i).getLeads() <= 0)
      {
        counts.remove(i);
      }
    }

    this.monthlyCounts = counts;
  }

  private void updateAmountCounts()
  {
    List<Count> counts = new ArrayList<>();

    counts.add(new Count("> 100,000", getCountBetweenValue(0.0, 100000.0)));
    counts.add(new Count("100,000 -> 150,000", getCountBetweenValue(100000.0, 150000.0)));
    counts.add(new Count("150,000 -> 200,000", getCountBetweenValue(150000.0, 200000.0)));
    counts.add(new Count("200,000 -> 250,000", getCountBetweenValue(200000.0, 250000.0)));
    counts.add(new Count("250,000 -> 300,000", getCountBetweenValue(250000.0, 300000.0)));
    counts.add(new Count("300,000 -> 350,000", getCountBetweenValue(300000.0, 350000.0)));
    counts.add(new Count("350,000 -> 400,000", getCountBetweenValue(350000.0, 400000.0)));
    counts.add(new Count("400,000 -> 450,000", getCountBetweenValue(400000.0, 450000.0)));
    counts.add(new Count("450,000 -> 500,000", getCountBetweenValue(450000.0, 500000.0)));
    counts.add(new Count("500,000 >", getCountBetweenValue(500000.0, Float.MAX_VALUE)));

    this.valueCounts = counts;
  }

  public List<Application> findApplicationByApplicationCreatedDateBetween(Date startDate, Date endDate){
    return applicationRepository.findApplicationByApplicationCreatedDateBetween(startDate, endDate);
  }
  public List<Application> findByApplicationType(String type){
    return applicationRepository.findByApplicationType(type);
  }


  public List<Application> findByApplicationStage(String appStage){
    return applicationRepository.findByApplicationStage(appStage);
  }


  public List<Application> findApplicationByApplicationTypeAndApplicationCreatedDateBetweenAndApplicationStageAndApplicationStatus(String applicationType, Date applicationCreatedDate, Date applicationCreatedDate2, String applicationStage, String applicationStatus){
    return applicationRepository.findApplicationByApplicationTypeAndApplicationCreatedDateBetweenAndApplicationStageAndApplicationStatus(applicationType, applicationCreatedDate, applicationCreatedDate2, applicationStage, applicationStatus);
  }

}