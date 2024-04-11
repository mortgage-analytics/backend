package Group28.Backend.controller;

import Group28.Backend.domain.Application;
import Group28.Backend.domain.Client;
import Group28.Backend.domain.Lead;
import Group28.Backend.domain.Timeline;
import Group28.Backend.domain.MonthlyCount;
import Group28.Backend.domain.Count;
import Group28.Backend.dto.ApplicationFilter;
import Group28.Backend.service.ApplicationService;
import Group28.Backend.service.ClientService;
import Group28.Backend.service.LeadService;
import Group28.Backend.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @GetMapping("/applications/between/date")
  public  ResponseEntity<List<Application>> findApplicationByApplicationCreatedDateBetween(Date start, Date end) {
    List<Application> betweenStartAndEnd = applicationService.findApplicationByApplicationCreatedDateBetween(start, end);
    if (betweenStartAndEnd.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(betweenStartAndEnd);
  }

  @GetMapping("/applications/type")
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

  @GetMapping("/applications/stage")
  public ResponseEntity<List<Application>> findByApplicationStage(String appStage){
    List<Application> stage = applicationService.findByApplicationStage(appStage);
    if (stage.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(stage);
  }


  @PostMapping("/applications/filter")
  public ResponseEntity<List<Application>> findApplicationByApplicationTypeAndApplicationCreatedDateBetweenAndApplicationStageAndApplicationStatus(ApplicationFilter filter){
    List<Application> filterByAll = applicationService.findApplicationByApplicationTypeAndApplicationCreatedDateBetweenAndApplicationStageAndApplicationStatus(filter.getAppType(), filter.getStartDate(), filter.getEndDate(), filter.getAppStage(), filter.getAppStatus());
    if (filterByAll.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(filterByAll);
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
    return ResponseEntity.ok(applicationService.getMonthlyCounts());
  }

  @GetMapping("/count/{type}")
  public ResponseEntity<Integer> getCountPerType(@PathVariable String type)
  {
    return ResponseEntity.ok(applicationService.getCountByType(type));
  }

  @GetMapping("graphs/byType")
  public ResponseEntity<List<Count>> getCountsByType()
  {
    List<Count> counts = new ArrayList<>();

    counts.add(new Count("Single", applicationService.getCountByType("Single")));
    counts.add(new Count("Joint", applicationService.getCountByType("Joint")));

    return ResponseEntity.ok(counts);
  }

  @GetMapping("graphs/byStage")
  public ResponseEntity<List<Count>> getCountsByStage()
  {
    System.out.println("Passed security, going to try");

    List<Count> counts = new ArrayList<>();

    counts.add(new Count("CREDIT_SUBMISSION", applicationService.getCountByStage("CREDIT_SUBMISSION")));
    counts.add(new Count("LOAN_OFFER", applicationService.getCountByStage("LOAN_OFFER")));
    counts.add(new Count("ADVISOR_REVIEW", applicationService.getCountByStage("ADVISOR_REVIEW")));
    counts.add(new Count("COMPLETE", applicationService.getCountByStage("COMPLETE")));
    counts.add(new Count("INFORMATION_GATHERING", applicationService.getCountByStage("INFORMATION_GATHERING")));
    counts.add(new Count("RECOMMENDATION", applicationService.getCountByStage("RECOMMENDATION")));
    counts.add(new Count("DRAWDOWN", applicationService.getCountByStage("DRAWDOWN")));

    System.out.println("Going to the return now :D");
    return ResponseEntity.ok(counts);
  }

  @GetMapping("/graphs/byMortgageAmount")
  public ResponseEntity<List<Count>> getMortgageAmountsByAmount()
  {
    return ResponseEntity.ok(applicationService.getValueCounts());
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
