package demo.bluemongo.com.barcodescannertest1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


/**
 * Created by glenn on 5/10/15.
 */
public class Appointment implements Parcelable {
    private int id;
    private String messageToCustomer;
    private String strAppointmentDate;
    private String strAppointmentTime;
    private transient Date appointmentDate;
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

    protected Appointment(Parcel in) {
        id = in.readInt();
        messageToCustomer = in.readString();
        strAppointmentDate = in.readString();
        strAppointmentTime = in.readString();
        locationId = in.readInt();
        locationName = in.readString();
        customerId = in.readInt();
        appointmentTypeId = in.readInt();
        status = in.readInt();
        statusName = in.readString();
        isComplete = in.readByte() != 0;
        appointmentTypeName = in.readString();
        appointmentTypePrefix = in.readString();
        statusHexCode = in.readString();
        appTypeHexCode = in.readString();
        locationHexCode = in.readString();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

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

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(messageToCustomer);
        dest.writeString(strAppointmentDate);
        dest.writeString(strAppointmentTime);
        dest.writeInt(locationId);
        dest.writeString(locationName);
        dest.writeInt(customerId);
        dest.writeInt(appointmentTypeId);
        dest.writeInt(status);
        dest.writeString(statusName);
        dest.writeByte((byte) (isComplete ? 1 : 0));
        dest.writeString(appointmentTypeName);
        dest.writeString(appointmentTypePrefix);
        dest.writeString(statusHexCode);
        dest.writeString(appTypeHexCode);
        dest.writeString(locationHexCode);
    }


}