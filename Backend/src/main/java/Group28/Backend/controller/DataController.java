package Group28.Backend.controller;

import Group28.Backend.domain.Application;
import Group28.Backend.domain.Lead;
import Group28.Backend.service.ApplicationService;
import Group28.Backend.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

// TODO make all return ResponseEntities, needed for security
// TODO require authorization with PreAuthorize annotations

@CrossOrigin(origins = "*", maxAge = 3600)
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

  
  @GetMapping("/successful")

    public ResponseEntity<List<Lead>> getSuccessful() {
    List<Lead> successfulEntries = leadService.getSuccessful();
    if (successfulEntries.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(successfulEntries);

  }

  @GetMapping("/failed")
  public ResponseEntity<List<Lead>> getFailed() {
    List<Lead> failedEntries = leadService.getFailed();
    if (failedEntries.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(failedEntries);
  }

  @GetMapping("/ongoing")
  public ResponseEntity<List<Lead>> getOngoing() {
    List<Lead> ongoingEntries = leadService.getOngoing();
    if (ongoingEntries.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(ongoingEntries);
  }

  @GetMapping("/after/{date}")
  public ResponseEntity<List<Lead>> getAllAfter(@PathVariable Date date) {
    List<Lead> entriesAfterDate = leadService.getAfter(date);
    if (entriesAfterDate.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(entriesAfterDate);
  }

  @GetMapping("/leads/all")
  public ResponseEntity<List<Lead>> getAllLeads()
  {
    return ResponseEntity.ok(leadService.getAll());
  }
}
