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
    // TODO successful will have a completion date, method available
    return null;
  }

  public List<Application> getFailed()
  {
    return null;
  }

  public List<Application> getOngoing()
  {
    return null;
  }

  public List<Application> getAfter(Date date)
  {
    // TODO
    return null;
  }
}
