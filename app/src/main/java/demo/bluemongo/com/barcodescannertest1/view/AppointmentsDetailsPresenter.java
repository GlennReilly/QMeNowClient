package demo.bluemongo.com.barcodescannertest1.view;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.api.AppointmentDetailWebHelper;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentStatus;
import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.presenter.GenericPresenter;

/**
 * Created by glenn on 30/12/15.
 */
public class AppointmentsDetailsPresenter extends GenericPresenter{
    private AppointmentDetailsView view;
    private final QMeNowModel model = new QMeNowModel();
    private String message;

    public AppointmentsDetailsPresenter(AppointmentDetailsView appointmentDetailsView) {
        super((GenericView) appointmentDetailsView);
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
