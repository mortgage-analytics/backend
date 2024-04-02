package Group28.Backend.repository;

import Group28.Backend.domain.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeadRepository extends MongoRepository<Lead, String>
{
    List<Lead> findAllByCreatedDateBetween(Date start, Date end);
}
