package Group28.Backend.repository;

import Group28.Backend.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {

}
