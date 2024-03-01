package Group28.Backend.repository;

import Group28.Backend.domain.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends MongoRepository<Lead, String>
{
}
