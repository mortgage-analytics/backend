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
  List<Application> findByApplicationType(String type);
  //List<Application> findByApp(boolean isSingle);
  List<Application> findByApplicationStage(String appStage);

  //List<Application> getAllApplicationTypeAndApplicationCreatedDateSAndApplicationStatusAndApplicationStageAndIfIsSingleOrJoint(Date startDate, Date endDate, Application appType, String appStatus,
                                               //boolean isSingle, String appStage);
}
