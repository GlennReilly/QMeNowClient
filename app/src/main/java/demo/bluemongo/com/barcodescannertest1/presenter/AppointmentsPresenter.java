package demo.bluemongo.com.barcodescannertest1.presenter;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.RetrieveAppointmentsView;

/**
 * Created by glenn on 5/10/15.
 */
public class AppointmentsPresenter {
    private final RetrieveAppointmentsView view;
    private final QMeNowModel model = new QMeNowModel();
    private String message;

    public AppointmentsPresenter(RetrieveAppointmentsView getApointmentsView) {
        this.view = getApointmentsView;
    }

    public UserDetails getSavedUserDetails() {
        return model.getUserDetails(view.getSharedPreferences());
    }

    public String getUserDetailsPrefsString() {
        return model.USER_DETAILS_PREFERENCES;
    }

/*    public void appointmentResultsCallback(String result) {
        view.displayAppointments(result);
    }*/

    public void showAppointmentsList(List<Appointment> appointmentsList) {
        view.displayAppointments(appointmentsList);
    }

    public void setMessage(String message) {
        this.message = message;
        view.showMessage(message);
    }

    public String getMessage() {
        return message;
    }

}
