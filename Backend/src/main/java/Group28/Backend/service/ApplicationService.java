package Group28.Backend.service;

import Group28.Backend.domain.Application;
import Group28.Backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationService
{
  @Autowired
  ApplicationRepository applicationRepository;

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
}