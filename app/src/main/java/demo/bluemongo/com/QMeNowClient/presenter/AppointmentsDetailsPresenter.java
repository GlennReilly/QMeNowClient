package demo.bluemongo.com.QMeNowClient.presenter;

import java.util.List;

import demo.bluemongo.com.QMeNowClient.api.AppointmentDetailWebHelper;
import demo.bluemongo.com.QMeNowClient.model.Appointment;
import demo.bluemongo.com.QMeNowClient.model.AppointmentStatus;
import demo.bluemongo.com.QMeNowClient.model.QMeNowModel;
import demo.bluemongo.com.QMeNowClient.view.AppointmentDetailsView;
import demo.bluemongo.com.QMeNowClient.view.GenericViewImpl;

/**
 * Created by glenn on 30/12/15.
 */
public class AppointmentsDetailsPresenter extends GenericPresenter{
    private AppointmentDetailsView view;
    private final QMeNowModel model = new QMeNowModel();
    private String message;

    public AppointmentsDetailsPresenter(AppointmentDetailsView appointmentDetailsView) {
        super((GenericViewImpl) appointmentDetailsView);
        this.view = appointmentDetailsView;
    }


    public void checkInAppointment(Appointment appointment, List<AppointmentStatus> appointmentStatusList) {
        AppointmentDetailWebHelper appointmentDetailWebHelper = new AppointmentDetailWebHelper(this);
        appointmentDetailWebHelper.checkInAppointment(appointment, appointmentStatusList);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
