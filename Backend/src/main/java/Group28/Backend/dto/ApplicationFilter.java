package Group28.Backend.dto;
import Group28.Backend.domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

public class ApplicationFilter {

    private Date startDate;
    private Date endDate;
    private boolean isSingle;
    private String appType;
    private String appStage;
    private String appStatus;

//    public ApplicationFilter(Date startDate, Date endDate, boolean isSingle, Application appType, String appStage, String appStatus) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.isSingle = isSingle;
//        this.appType = appType;
//        this.appStage = appStage;
//        this.appStatus = appStatus;
//    }

    public String getAppStage() {
        return appStage;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public String getAppType() {
        return appType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }
    public boolean getIsSingle(){
        return isSingle;
    }

    public void setAppStage(String appStage) {
        this.appStage = appStage;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setSingle(boolean single) {
        this.isSingle = single;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
