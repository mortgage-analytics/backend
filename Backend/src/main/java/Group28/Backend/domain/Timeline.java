package Group28.Backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Timeline {
    @Id
    private String id;

    private Date leadCreated;
    private Date applicationCreated;
    private Date advisorReviewCompleted;
    private Date submission;
    private Date response;
    private Date valuationReceived;
    private Date loanOfferReceived;
    private Date completed;

    // Getters
    public Date getLeadCreated() {
        return leadCreated;
    }

    public Date getApplicationCreated() {
        return applicationCreated;
    }

    public Date getAdvisorReviewCompleted() {
        return advisorReviewCompleted;
    }

    public Date getSubmission() {
        return submission;
    }

    public Date getResponse() {
        return response;
    }

    public Date getValuationReceived() {
        return valuationReceived;
    }

    public Date getLoanOfferReceived() {
        return loanOfferReceived;
    }

    public Date getCompleted() {
        return completed;
    }

    // Setters
    public void setLeadCreated(Date leadCreated) {
        this.leadCreated = leadCreated;
    }

    public void setApplicationCreated(Date applicationCreated) {
        this.applicationCreated = applicationCreated;
    }

    public void setAdvisorReviewCompleted(Date advisorReviewCompleted) {
        this.advisorReviewCompleted = advisorReviewCompleted;
    }

    public void setSubmission(Date submission) {
        this.submission = submission;
    }

    public void setResponse(Date response) {
        this.response = response;
    }

    public void setValuationReceived(Date valuationReceived) {
        this.valuationReceived = valuationReceived;
    }

    public void setLoanOfferReceived(Date loanOfferReceived) {
        this.loanOfferReceived = loanOfferReceived;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    // I'm not including the client in here as a real object. Clients contain the timeline already.
    // It'd cause self referencing issues.
}
