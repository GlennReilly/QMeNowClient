package demo.bluemongo.com.barcodescannertest1.view;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.api.AppointmentDetailWebHelper;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentStatus;

/**
 * Created by glenn on 30/12/15.
 */
public class AppointmentsDetailsPresenter {
    private AppointmentDetailsView view;
    private String message;

    public AppointmentsDetailsPresenter(AppointmentDetailsView appointmentDetailsView) {
        this.view = appointmentDetailsView;
    }


    public void progressAppointmentStatus(Appointment appointment, List<AppointmentStatus> appointmentStatusList) {
        AppointmentDetailWebHelper appointmentDetailWebHelper = new AppointmentDetailWebHelper(this);
        appointmentDetailWebHelper.progressAppointmentStatus(appointment,appointmentStatusList);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
