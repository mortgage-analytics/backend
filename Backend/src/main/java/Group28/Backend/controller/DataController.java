package Group28.Backend.controller;

import Group28.Backend.domain.Application;
import Group28.Backend.domain.Lead;
import Group28.Backend.domain.MonthlyCount;
import Group28.Backend.service.ApplicationService;
import Group28.Backend.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/api/data")
public class DataController
{
  @Autowired
  ApplicationService applicationService;

  @Autowired
  LeadService leadService;

  @GetMapping("/hi")
  public ResponseEntity<String> hi()
  {
    return ResponseEntity.ok("Hello");
  }

  @GetMapping("/applications/all")
  public ResponseEntity<List<Application>> getAll()
  {
    return ResponseEntity.ok(applicationService.getAll());
  }


  @GetMapping("/applications/successful")

  public ResponseEntity<List<Application>> getSuccessful()
  {
    List<Application> successfulEntries = applicationService.getSuccessful();
    if (successfulEntries.isEmpty())
    {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(successfulEntries);

  }

  @GetMapping("/applications/failed")
  public ResponseEntity<List<Application>> getFailed()
  {
    List<Application> failedEntries = applicationService.getFailed();
    if (failedEntries.isEmpty())
    {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(failedEntries);
  }

  @GetMapping("/applications/ongoing")
  public ResponseEntity<List<Application>> getOngoing()
  {
    List<Application> ongoingEntries = applicationService.getOngoing();
    if (ongoingEntries.isEmpty())
    {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(ongoingEntries);
  }

  @GetMapping("/applications/after/{date}")
  public ResponseEntity<List<Application>> getAllAfter(@PathVariable Date date)
  {
    List<Application> entriesAfterDate = applicationService.getAfter(date);
    if (entriesAfterDate.isEmpty())
    {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(entriesAfterDate);
  }

  @GetMapping("/leads/all")
  public ResponseEntity<List<Lead>> getAllLeads()
  {
    return ResponseEntity.ok(leadService.getAll());
  }


  @GetMapping("/graphs/byMonth")
  public ResponseEntity<List<MonthlyCount>> getCountsByMonth()
  {
    return ResponseEntity.ok(applicationService.getCounts());
  }

  @GetMapping("/count/{type}")
  public ResponseEntity<Integer> getCountPerType(@PathVariable String type)
  {
    return ResponseEntity.ok(applicationService.getCountByType(type));
  }

  @GetMapping("/valueByType/{type}")
  public ResponseEntity<Double> getValuePerType(@PathVariable String type)
  {
    return ResponseEntity.ok(applicationService.getValuePerType(type));
  }

  @GetMapping("/valueByStatus/{status}")
  public ResponseEntity<Double> getValuePerStatus(@PathVariable String status)
  {
    return ResponseEntity.ok(applicationService.getValuePerStatus(status));
  }

  @GetMapping("/valueByTypeAndStatus/{type}/{status}")
  public ResponseEntity<Double> getValuePerTypeAndStatus(@PathVariable String type, @PathVariable String status)
  {
    return ResponseEntity.ok(applicationService.getValuePerTypeAndStatus(type, status));
  }
}
