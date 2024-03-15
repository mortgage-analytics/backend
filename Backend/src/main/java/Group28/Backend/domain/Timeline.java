package Group28.Backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Timeline
{
    @Id
    private String id;

    // TODO see if the ml side can handle Date objects instead
    private String leadCreatedDate;
    private String applicationCreatedDate;
    private String advisorReviewCompletedDate;
    private String submissionDate;
    private String responseDate;
    private String valuationDate;
    private String loanOfferReceived;
    private String completedDate;

    public String getLeadCreatedDate() {
        return leadCreatedDate;
    }

    public void setLeadCreatedDate(String leadCreatedDate) {
        this.leadCreatedDate = leadCreatedDate;
    }

    public String getApplicationCreatedDate() {
        return applicationCreatedDate;
    }

    public void setApplicationCreatedDate(String applicationCreatedDate) {
        this.applicationCreatedDate = applicationCreatedDate;
    }

    public String getAdvisorReviewCompletedDate() {
        return advisorReviewCompletedDate;
    }

    public void setAdvisorReviewCompletedDate(String advisorReviewCompletedDate) {
        this.advisorReviewCompletedDate = advisorReviewCompletedDate;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(String valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getLoanOfferReceived() {
        return loanOfferReceived;
    }

    public void setLoanOfferReceived(String loanOfferReceived) {
        this.loanOfferReceived = loanOfferReceived;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }
}
