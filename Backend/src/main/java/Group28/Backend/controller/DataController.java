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

    public ResponseEntity<List<Application>> getSuccessful() {
    List<Application> successfulEntries = applicationService.getSuccessful();
    if (successfulEntries.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(successfulEntries);

  }

  @GetMapping("/applications/failed")
  public ResponseEntity<List<Application>> getFailed() {
    List<Application> failedEntries = applicationService.getFailed();
    if (failedEntries.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(failedEntries);
  }

  @GetMapping("/applications/ongoing")
  public ResponseEntity<List<Application>> getOngoing() {
    List<Application> ongoingEntries = applicationService.getOngoing();
    if (ongoingEntries.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(ongoingEntries);
  }

  @GetMapping("/applications/after/{date}")
  public ResponseEntity<List<Application>> getAllAfter(@PathVariable Date date) {
    List<Application> entriesAfterDate = applicationService.getAfter(date);
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

  @GetMapping()
  public  ResponseEntity<List<Application>> getAllBetweenStartAndEndData(Date start, Date end) {
    List<Application> betweenStartAndEnd = applicationService.getAllBetweenStartAndEndData(start, end);
    if (betweenStartAndEnd.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(betweenStartAndEnd);
  }

  @GetMapping
  public  ResponseEntity<List<Application>> findByApplicationType(String appType) {
    List<Application> byAppType = applicationService.findByApplicationType(appType);
    if (byAppType.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(byAppType);
  }

/*
  @GetMapping
  public ResponseEntity<List<Application>> getApplicationsIfIsSingleOrJoint(boolean isSingle) {
    List<Application> singleApp = applicationService.getApplicationsIfIsSingleOrJoint(isSingle);
    if (singleApp.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(singleApp);
  }
*/
  @GetMapping
  public ResponseEntity<List<Application>> findByApplicationStage(String appStage){
    List<Application> stage = applicationService.findByApplicationStage(appStage);
    if (stage.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(stage);
  }

  /*
  @GetMapping
  public ResponseEntity<List<Application>> getAllApplicationTypeAndApplicationCreatedDateSAndApplicationStatusAndApplicationStageAndIfIsSingleOrJoint(Date startDate, Date endDate, Application appType, String appStatus,
                                                                       boolean isSingle, String appStage){
    List<Application> filterByAll = applicationService.getAllApplicationTypeAndApplicationCreatedDateSAndApplicationStatusAndApplicationStageAndIfIsSingleOrJoint(startDate, endDate, appType, appStatus, isSingle, appStage);
    if (filterByAll.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(filterByAll);
  }
  */
}
