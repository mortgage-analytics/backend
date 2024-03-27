package Group28.Backend.controller;

import Group28.Backend.domain.Application;
import Group28.Backend.domain.Client;
import Group28.Backend.domain.Lead;
import Group28.Backend.domain.Timeline;
import Group28.Backend.domain.MonthlyCount;
import Group28.Backend.service.ApplicationService;
import Group28.Backend.service.ClientService;
import Group28.Backend.service.LeadService;
import Group28.Backend.service.TimelineService;
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
  @Autowired
  ClientService clientService;
  @Autowired
  TimelineService timelineService;

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
  @GetMapping("/clients/all")
  public ResponseEntity<List<Client>> getAllClients() {
    List<Client> clients = clientService.getAll();
    if (clients.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(clients);
  }

  @GetMapping("/timeline/all")
  public ResponseEntity<List<Timeline>> getAllTimelines() {
    List<Timeline> timelines = timelineService.getAll();
    if (timelines.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(timelines);
  }


  @GetMapping("/graphs/byMonth")
  public ResponseEntity<List<MonthlyCount>> getCountsByMonth()
  {
    List<MonthlyCount> counts = new ArrayList<>();

    // Start and end dates are arbitrary, they just need to encompass all dates
    Calendar current = Calendar.getInstance();
    current.set(2000, Calendar.JANUARY, 1);

    Calendar end = Calendar.getInstance();
    end.set(2025, Calendar.JANUARY, 1);

    // Only get data that is relevant. No need to include any before our first recorded applications
    boolean firstFound = false;
    while (current.before(end))
    {
      Calendar next = Calendar.getInstance();
      next.setTime(current.getTime());
      next.add(Calendar.MONTH, 1);

      int applications = applicationService.getBetween(current.getTime(), next.getTime()).size();
      int leads = leadService.getBetween(current.getTime(), next.getTime()).size();

      if(firstFound || applications > 0 || leads > 0)
      {
        String name = current.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + current.get(Calendar.YEAR);
        counts.add(new MonthlyCount(name, leads, applications));

        firstFound = true;
      }

      current = next;
    }

    // Trim the end off. No need to include any after our last recorded applications.
    for(int i = counts.size() - 1; i >= 0; i--)
    {
      if(counts.get(i).getApplications() <= 0 || counts.get(i).getLeads() <= 0)
      {
        counts.remove(i);
      }
    }

    return ResponseEntity.ok(counts);
  }
}
