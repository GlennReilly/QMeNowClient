package demo.bluemongo.com.QMeNowClient.model;

import io.realm.RealmObject;

/**
 * Created by glenn on 17/04/16.
 */
public class RealmAppointmentStatus extends RealmObject{
    private int id;
    private String name;
    private int businessId;
    private String backgroundColourHexCode;
    private int sequenceNumber;
    private boolean customerInitiated;

    public String getBackgroundColourHexCode() {
        return backgroundColourHexCode;
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.backgroundColourHexCode = backgroundColourHexCode;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public boolean isCustomerInitiated() {
        return customerInitiated;
    }

    public void setCustomerInitiated(boolean customerInitiated) {
        this.customerInitiated = customerInitiated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
