package demo.bluemongo.com.barcodescannertest1.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by glenn on 16/04/16.
 */
public class RealmAppointmentsResponse extends RealmObject {
    private String appointmentCreationURL;
    private RealmBusinessDTO business = new RealmBusinessDTO();
    private RealmList<RealmAppointment> appointmentList = new RealmList<>();
    private RealmList<RealmAppointmentStatus> appointmentStatusList = new RealmList<>();


    public String getAppointmentCreationURL() {
        return appointmentCreationURL;
    }

    public void setAppointmentCreationURL(String appointmentCreationURL) {
        this.appointmentCreationURL = appointmentCreationURL;
    }

    public RealmList<RealmAppointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(RealmList<RealmAppointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public RealmList<RealmAppointmentStatus> getAppointmentStatusList() {
        return appointmentStatusList;
    }

    public void setAppointmentStatusList(RealmList<RealmAppointmentStatus> appointmentStatusList) {
        this.appointmentStatusList = appointmentStatusList;
    }

    public RealmBusinessDTO getBusiness() {
        return business;
    }

    public void setBusiness(RealmBusinessDTO business) {
        this.business = business;
    }
}
