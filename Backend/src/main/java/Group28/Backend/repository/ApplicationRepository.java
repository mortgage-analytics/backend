package Group28.Backend.repository;

import Group28.Backend.domain.Application;
import Group28.Backend.dto.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String>
{

  List<Application> findByApplicationStatusAndCompletionDateIsNotNull(String status);
  List<Application> findByApplicationStatusAndCompletionDateIsNull(String status);
  List<Application> findByApplicationStatus(String status);
  List<Application> findByCompletionDateAfter(Date date);
  List<Application> getAllBetweenStartAndEndData(Date startDate, Date endDate);
  List<Application> getAllByApplicationType(Application appType);
  List<Application> getAllApplicationsByStatus(String appStatus);
  List<Application> getApplicationsIfIsSingleOrJoint(boolean isSingle);
  List<Application> getApplicationsByApplicationStage(String appStage);


}
