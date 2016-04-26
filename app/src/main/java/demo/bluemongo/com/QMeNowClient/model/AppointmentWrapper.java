package demo.bluemongo.com.QMeNowClient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by glenn on 5/01/16.
 */
public class AppointmentWrapper implements Parcelable{
    private Appointment appointment;
    private List<AppointmentStatus> statusList;

    public AppointmentWrapper(){}

    protected AppointmentWrapper(Parcel in) {
        appointment = in.readParcelable(Appointment.class.getClassLoader());
        statusList = in.createTypedArrayList(AppointmentStatus.CREATOR);
    }

    public static final Creator<AppointmentWrapper> CREATOR = new Creator<AppointmentWrapper>() {
        @Override
        public AppointmentWrapper createFromParcel(Parcel in) {
            return new AppointmentWrapper(in);
        }

        @Override
        public AppointmentWrapper[] newArray(int size) {
            return new AppointmentWrapper[size];
        }
    };

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setStatusList(List<AppointmentStatus> statusList) {
        this.statusList = statusList;
    }

    public List<AppointmentStatus> getStatusList() {
        return statusList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(appointment, flags);
        dest.writeTypedList(statusList);
    }
}
