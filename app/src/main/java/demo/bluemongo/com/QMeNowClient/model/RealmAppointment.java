package demo.bluemongo.com.QMeNowClient.model;

import io.realm.RealmObject;

/**
 * Created by glenn on 16/04/16.
 */
public class RealmAppointment extends RealmObject{
    private int id;
    private String messageToCustomer;
    private String strAppointmentDate;
    private String strAppointmentTime;
    private int locationId;
    private String locationName;
    private int customerId;
    private int appointmentTypeId;
    private int status;
    private String statusName;
    private boolean isComplete;
    private String appointmentTypeName;
    private String appointmentTypePrefix;
    private String statusHexCode;
    private String appTypeHexCode;
    private String locationHexCode;
    private String strCheckInDateTime;

    public int getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(int appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
    }

    public String getAppointmentTypeName() {
        return appointmentTypeName;
    }

    public void setAppointmentTypeName(String appointmentTypeName) {
        this.appointmentTypeName = appointmentTypeName;
    }

    public String getAppointmentTypePrefix() {
        return appointmentTypePrefix;
    }

    public void setAppointmentTypePrefix(String appointmentTypePrefix) {
        this.appointmentTypePrefix = appointmentTypePrefix;
    }

    public String getAppTypeHexCode() {
        return appTypeHexCode;
    }

    public void setAppTypeHexCode(String appTypeHexCode) {
        this.appTypeHexCode = appTypeHexCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getLocationHexCode() {
        return locationHexCode;
    }

    public void setLocationHexCode(String locationHexCode) {
        this.locationHexCode = locationHexCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getMessageToCustomer() {
        return messageToCustomer;
    }

    public void setMessageToCustomer(String messageToCustomer) {
        this.messageToCustomer = messageToCustomer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusHexCode() {
        return statusHexCode;
    }

    public void setStatusHexCode(String statusHexCode) {
        this.statusHexCode = statusHexCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStrAppointmentDate() {
        return strAppointmentDate;
    }

    public void setStrAppointmentDate(String strAppointmentDate) {
        this.strAppointmentDate = strAppointmentDate;
    }

    public String getStrAppointmentTime() {
        return strAppointmentTime;
    }

    public void setStrAppointmentTime(String strAppointmentTime) {
        this.strAppointmentTime = strAppointmentTime;
    }

    public String getStrCheckInDateTime() {
        return strCheckInDateTime;
    }

    public void setStrCheckInDateTime(String strCheckInDateTime) {
        this.strCheckInDateTime = strCheckInDateTime;
    }
}
