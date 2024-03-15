package Group28.Backend.domain;

import org.springframework.data.annotation.Id;

public class Client
{
    @Id
    private String id;

    private String type;
    private String submittedTo;
    private boolean propertyIdentified;
    private double mortgageAmount;

    // TODO ask ml-team if this can be changed to a boolean (it's either or, boolean makes the most sense)
    private String singleOrJoint;

    // Getters
    public void setType(String type) {
        this.type = type;
    }

    public void setSubmittedTo(String submittedTo) {
        this.submittedTo = submittedTo;
    }

    public void setPropertyIdentified(boolean propertyIdentified) {
        this.propertyIdentified = propertyIdentified;
    }

    public void setMortgageAmount(double mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public void setSingleOrJoint(String singleOrJoint) {
        this.singleOrJoint = singleOrJoint;
    }

    // Setters
    public String getType() {
        return type;
    }

    public String getSubmittedTo() {
        return submittedTo;
    }

    public boolean isPropertyIdentified() {
        return propertyIdentified;
    }

    public double getMortgageAmount() {
        return mortgageAmount;
    }

    public String getSingleOrJoint() {
        return singleOrJoint;
    }
}


