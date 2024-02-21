package Group28.Backend.service;

import Group28.Backend.domain.DataEntry;
import Group28.Backend.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DataService
{
  @Autowired
  DataRepository dataRepository;

  public List<DataEntry> getAll()
  {

    return dataRepository.findAll();
  }

  public List<DataEntry> getSuccessful()
  {

    return dataRepository.findByApplicationStatusAndCompletionDateIsNotNull("successful");
  }

  public List<DataEntry> getFailed()
  {

    return dataRepository.findByApplicationStatus("failed");
  }

  public List<DataEntry> getOngoing()
  {

    return dataRepository.findByApplicationStatusAndCompletionDateIsNull("ongoing");
  }

  public List<DataEntry> getAfter(Date date)
  {
    return dataRepository.findByCompletionDateAfter(date);
  }
}
