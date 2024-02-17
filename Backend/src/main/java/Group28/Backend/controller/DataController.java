package Group28.Backend.controller;

import Group28.Backend.domain.DataEntry;
import Group28.Backend.service.DataService;
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
  DataService dataService;

  @GetMapping("/hi")
  public ResponseEntity<String> hi()
  {
    return ResponseEntity.ok("Hello");
  }

  @GetMapping("/all")
  public ResponseEntity<List<DataEntry>> getAll()
  {
    return ResponseEntity.ok(dataService.getAll());
  }

  @GetMapping("/successful")
  public List<DataEntry> getSuccessful()
  {
    // TODO
    return null;
  }

  @GetMapping("/failed")
  public List<DataEntry> getFailed()
  {
    return null;
  }

  @GetMapping("/ongoing")
  public List<DataEntry> getOngoing()
  {
    return null;
  }

  @GetMapping("/after/{date}")
  public List<DataEntry> getAllAfter(@PathVariable Date date)
  {
    // TODO
    return null;
  }
}
