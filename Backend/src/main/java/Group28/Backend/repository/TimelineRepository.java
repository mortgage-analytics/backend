package Group28.Backend.repository;

import Group28.Backend.domain.Timeline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimelineRepository extends MongoRepository<Timeline, String> {

}

