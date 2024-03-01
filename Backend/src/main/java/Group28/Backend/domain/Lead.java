package Group28.Backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Lead {
    @Id
    String id;

    private Date modifiedDate;
    private String mortgageTypeDetail;
    private String mortgageType;
    private double loanAmount;
    private boolean propertyKnown;
    private Date createdDate;
    private Date nextActionDate;
    private String leadSource;
    private String leadSourceDetail;
    private String status;
    private String additionalInfo;

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getMortgageTypeDetail() {
        return mortgageTypeDetail;
    }

    public void setMortgageTypeDetail(String mortgageTypeDetail) {
        this.mortgageTypeDetail = mortgageTypeDetail;
    }

    public String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public boolean isPropertyKnown() {
        return propertyKnown;
    }

    public void setPropertyKnown(boolean propertyKnown) {
        this.propertyKnown = propertyKnown;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getNextActionDate() {
        return nextActionDate;
    }

    public void setNextActionDate(Date nextActionDate) {
        this.nextActionDate = nextActionDate;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getLeadSourceDetail() {
        return leadSourceDetail;
    }

    public void setLeadSourceDetail(String leadSourceDetail) {
        this.leadSourceDetail = leadSourceDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}

