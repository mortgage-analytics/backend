package Group28.Backend.service;

import Group28.Backend.domain.Timeline;
import Group28.Backend.repository.TimelineRepository;
import Group28.Backend.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
@Service
public class TimelineService {
    @Autowired
    TimelineRepository timelineRepository;

    public List<Timeline> getAll() {
        return timelineRepository.findAll();
    }
}
