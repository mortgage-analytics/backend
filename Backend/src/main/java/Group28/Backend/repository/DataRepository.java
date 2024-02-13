package Group28.Backend.repository;

import Group28.Backend.domain.DataEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataRepository extends MongoRepository<DataEntry, String>
{
  List<DataEntry> findByCompletionDateExists();
  List<DataEntry> findByApplicationCreatedDateAfter(Date date);
}
