package Group28.Backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class DataEntry
{
    @Id
    private String id;

    private Date primaryLastLoggedOn;
    private String applicationType;
    private String applicationStage;
    private boolean propertyIdentified;
    private double mortgageAmountProposed;
    private Date lastUpdatedDate;
    private Date leadCreatedDate;
    private Date applicationCreatedDate;
    private Date advisorReviewCompleteDate;
    private Date submissionDate;
    private Date responseDate;
    private Date valuationReceivedDate;
    private Date loanOfferReceived;
    private Date completionDate;
    private Date estimatedClosingDate;
    private String leadSourceDetails;
    private String applicationStatus;
    private String submittedTo;
    private Date secondaryLastLoggedOn;
    private String leadSource;
    private String nextActionDetails;
    private boolean single; // true for single, false for joint
    private String nextAction;

    public Date getLastUpdatedDate()
    {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate)
    {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getPrimaryLastLoggedOn()
    {
        return primaryLastLoggedOn;
    }

    public void setPrimaryLastLoggedOn(Date primaryLastLoggedOn)
    {
        this.primaryLastLoggedOn = primaryLastLoggedOn;
    }

    public String getApplicationType()
    {
        return applicationType;
    }

    public void setApplicationType(String applicationType)
    {
        this.applicationType = applicationType;
    }

    public String getApplicationStage()
    {
        return applicationStage;
    }

    public void setApplicationStage(String applicationStage)
    {
        this.applicationStage = applicationStage;
    }

    public boolean isPropertyIdentified()
    {
        return propertyIdentified;
    }

    public void setPropertyIdentified(boolean propertyIdentified)
    {
        this.propertyIdentified = propertyIdentified;
    }

    public double getMortgageAmountProposed()
    {
        return mortgageAmountProposed;
    }

    public void setMortgageAmountProposed(double mortgageAmountProposed)
    {
        this.mortgageAmountProposed = mortgageAmountProposed;
    }

    public Date getLeadCreatedDate()
    {
        return leadCreatedDate;
    }

    public void setLeadCreatedDate(Date leadCreatedDate)
    {
        this.leadCreatedDate = leadCreatedDate;
    }

    public Date getApplicationCreatedDate()
    {
        return applicationCreatedDate;
    }

    public void setApplicationCreatedDate(Date applicationCreatedDate)
    {
        this.applicationCreatedDate = applicationCreatedDate;
    }

    public Date getAdvisorReviewCompleteDate()
    {
        return advisorReviewCompleteDate;
    }

    public void setAdvisorReviewCompleteDate(Date advisorReviewCompleteDate)
    {
        this.advisorReviewCompleteDate = advisorReviewCompleteDate;
    }

    public Date getSubmissionDate()
    {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate)
    {
        this.submissionDate = submissionDate;
    }

    public Date getResponseDate()
    {
        return responseDate;
    }

    public void setResponseDate(Date responseDate)
    {
        this.responseDate = responseDate;
    }

    public Date getValuationReceivedDate()
    {
        return valuationReceivedDate;
    }

    public void setValuationReceivedDate(Date valuationReceivedDate)
    {
        this.valuationReceivedDate = valuationReceivedDate;
    }

    public Date getLoanOfferReceived()
    {
        return loanOfferReceived;
    }

    public void setLoanOfferReceived(Date loanOfferReceived)
    {
        this.loanOfferReceived = loanOfferReceived;
    }

    public Date getCompletionDate()
    {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate)
    {
        this.completionDate = completionDate;
    }

    public Date getEstimatedClosingDate()
    {
        return estimatedClosingDate;
    }

    public void setEstimatedClosingDate(Date estimatedClosingDate)
    {
        this.estimatedClosingDate = estimatedClosingDate;
    }

    public String getLeadSourceDetails()
    {
        return leadSourceDetails;
    }

    public void setLeadSourceDetails(String leadSourceDetails)
    {
        this.leadSourceDetails = leadSourceDetails;
    }

    public String getApplicationStatus()
    {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus)
    {
        this.applicationStatus = applicationStatus;
    }

    public String getSubmittedTo()
    {
        return submittedTo;
    }

    public void setSubmittedTo(String submittedTo)
    {
        this.submittedTo = submittedTo;
    }

    public Date getSecondaryLastLoggedOn()
    {
        return secondaryLastLoggedOn;
    }

    public void setSecondaryLastLoggedOn(Date secondaryLastLoggedOn)
    {
        this.secondaryLastLoggedOn = secondaryLastLoggedOn;
    }

    public String getLeadSource()
    {
        return leadSource;
    }

    public void setLeadSource(String leadSource)
    {
        this.leadSource = leadSource;
    }

    public String getNextActionDetails()
    {
        return nextActionDetails;
    }

    public void setNextActionDetails(String nextActionDetails)
    {
        this.nextActionDetails = nextActionDetails;
    }

    public boolean isSingle()
    {
        return single;
    }

    public void setSingle(boolean single)
    {
        this.single = single;
    }

    public String getNextAction()
    {
        return nextAction;
    }

    public void setNextAction(String nextAction)
    {
        this.nextAction = nextAction;
    }
}
