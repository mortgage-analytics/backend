package Group28.Backend.repository;

import Group28.Backend.domain.Application;
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

  List<Application> findAllByApplicationCreatedDateBetween(Date start, Date end);
}
