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

  public List<Application> getAllBetweenStartAndEndData(Date startDate, Date endDate){
    return applicationRepository.getAllBetweenStartAndEndData(startDate, endDate);
  }
  public List<Application> getAllByApplicationType(Application appType){
    return applicationRepository.getAllByApplicationType(appType);
  }
  public List<Application> getAllApplicationsByStatus(String appStatus){
    return applicationRepository.getAllApplicationsByStatus(appStatus);
  }
  public List<Application> getApplicationsIfIsSingleOrJoint(boolean isSingle){
    return applicationRepository.getApplicationsIfIsSingleOrJoint(isSingle);
  }
  public List<Application> getApplicationsByApplicationStage(String appStage){
    return applicationRepository.getApplicationsByApplicationStage(appStage);
  }

  public List<Application> getApplicationsByAllFilters(Date startDate, Date endDate, Application appType, String appStatus,
                                                       boolean isSingle, String appStage){
    return applicationRepository.getApplicationByAllFilters(startDate, endDate, appType, appStatus, isSingle, appStage);
  }

}
