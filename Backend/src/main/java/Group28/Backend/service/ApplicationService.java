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
}