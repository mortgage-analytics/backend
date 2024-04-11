package Group28.Backend.domain;

public class ProbabilityRequest
{
  private String application_type; // 0 for single, 1 for joint
  private String mortgage_type; // 0 for purchasing new home, 1 for remortgaging
  private double property_value;
  private double mortgage_amount_proposed;
  private int documents_uploaded;
  private double summed_income;

  // Getters
  public String getApplication_type() {
    return application_type;
  }

  public String getMortgage_type() {
    return mortgage_type;
  }

  public double getProperty_value() {
    return property_value;
  }

  public double getMortgage_amount_proposed() {
    return mortgage_amount_proposed;
  }

  public int getDocuments_uploaded() {
    return documents_uploaded;
  }

  public double getSummed_income() {
    return summed_income;
  }

  // Setters
  public void setApplication_type(String application_type) {
    this.application_type = application_type;
  }

  public void setMortgage_type(String mortgage_type) {
    this.mortgage_type = mortgage_type;
  }

  public void setProperty_value(double property_value) {
    this.property_value = property_value;
  }

  public void setMortgage_amount_proposed(double mortgage_amount_proposed) {
    this.mortgage_amount_proposed = mortgage_amount_proposed;
  }

  public void setDocuments_uploaded(int documents_uploaded) {
    this.documents_uploaded = documents_uploaded;
  }

  public void setSummed_income(double summed_income) {
    this.summed_income = summed_income;
  }
}
