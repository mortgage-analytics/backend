package Group28.Backend.service;

import Group28.Backend.domain.ProbabilityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ModelService
{
  public void test()
  {
    RestTemplate template = new RestTemplate();

    ResponseEntity<String> response = template.getForEntity("https://mortgagemodel.azurewebsites.net/test", String.class);

    if(response.getStatusCode() == HttpStatus.OK)
    {
      System.out.println(response.getBody());
    } else
    {
      System.out.println("Error: " + response.getStatusCode());
    }
  }

  public double singleProbability(ProbabilityRequest request)
  {
    RestTemplate template = new RestTemplate();

    // TODO endpoint url not done yet.
    ResponseEntity<Double> response = template.getForEntity("https://mortgagemodel.azurewebsites.net/", Double.class);

    if(response.getStatusCode() == HttpStatus.OK)
    {
      return response.getBody();
    } else
    {
      return Double.NaN;
    }
  }

  public double[] manyProbabilities(List<ProbabilityRequest> request)
  {
    RestTemplate template = new RestTemplate();

    // TODO endpoint url not done yet.
    ResponseEntity<double[]> response = template.getForEntity("https://mortgagemodel.azurewebsites.net/", double[].class);

    if(response.getStatusCode() == HttpStatus.OK)
    {
      return response.getBody();
    } else
    {
      return null;
    }
  }

  public String getAnalytics()
  {
    RestTemplate template = new RestTemplate();

    ResponseEntity<String> response = template.getForEntity("https://mortgagemodel.azurewebsites.net/data_analytics", String.class);

    if(response.getStatusCode() == HttpStatus.OK)
    {
      System.out.println(response.getBody());
      return response.getBody();
    } else
    {
      return null;
    }
  }
}
