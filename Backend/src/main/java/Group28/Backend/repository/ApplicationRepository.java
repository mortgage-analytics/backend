package Group28.Backend.repository;

import Group28.Backend.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String>
{
  List<Application> findByCompletionDateExists();
  List<Application> findByApplicationCreatedDateAfter(Date date);
}
