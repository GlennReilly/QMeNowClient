package demo.bluemongo.com.barcodescannertest1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by glenn on 16/10/15.
 */
public class AppointmentStatus implements Parcelable {
    private int id;
    private String name;
    private int businessId;
    private String backgroundColourHexCode;
    private int sequenceNumber;
    private boolean customerInitiated;

    public AppointmentStatus() {
    }

    protected AppointmentStatus(Parcel in) {
        id = in.readInt();
        name = in.readString();
        businessId = in.readInt();
        backgroundColourHexCode = in.readString();
        sequenceNumber = in.readInt();
        customerInitiated = in.readByte() != 0;
    }

    public static final Creator<AppointmentStatus> CREATOR = new Creator<AppointmentStatus>() {
        @Override
        public AppointmentStatus createFromParcel(Parcel in) {
            return new AppointmentStatus(in);
        }

        @Override
        public AppointmentStatus[] newArray(int size) {
            return new AppointmentStatus[size];
        }
    };

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

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.backgroundColourHexCode = backgroundColourHexCode;
    }

    public String getBackgroundColourHexCode() {
        return backgroundColourHexCode;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public boolean isCustomerInitiated() {
        return customerInitiated;
    }

    public void setCustomerInitiated(boolean customerInitiated) {
        this.customerInitiated = customerInitiated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(businessId);
        dest.writeString(backgroundColourHexCode);
        dest.writeInt(sequenceNumber);
        dest.writeByte((byte) (customerInitiated ? 1 : 0));
    }
}
