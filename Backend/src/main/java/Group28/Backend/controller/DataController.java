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

  @GetMapping("/applications/successful")
  public List<Application> getSuccessful()
  {
    // TODO
    return null;
  }

  @GetMapping("/applications/failed")
  public List<Application> getFailed()
  {
    return null;
  }

  @GetMapping("/applications/ongoing")
  public List<Application> getOngoing()
  {
    return null;
  }

  @GetMapping("/applications/after/{date}")
  public List<Application> getAllAfter(@PathVariable Date date)
  {
    // TODO
    return null;
  }

  @GetMapping("/leads/all")
  public ResponseEntity<List<Lead>> getAllLeads()
  {
    return ResponseEntity.ok(leadService.getAll());
  }
}
