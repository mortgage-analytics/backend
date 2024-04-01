package Group28.Backend.service;

import Group28.Backend.domain.Application;
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

  private
  List<MonthlyCount> counts = new ArrayList<>();

  public ApplicationService()
  {
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

  public List<MonthlyCount> getCounts()
  {
    return counts;
  }

  // Once per minute
  @Scheduled(fixedRate = 1000 * 60)
  private void updateCounts()
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

    this.counts = counts;
  }
}