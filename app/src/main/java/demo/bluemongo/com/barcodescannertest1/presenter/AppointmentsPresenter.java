package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.GenericView;
import demo.bluemongo.com.barcodescannertest1.view.RetrieveAppointmentsView;

/**
 * Created by glenn on 5/10/15.
 */
public class AppointmentsPresenter extends GenericPresenter {
    private final RetrieveAppointmentsView view;
    private final QMeNowModel model = new QMeNowModel();
    private String message;
    public enum MessageToUser {NOAPPOINTMENTSFOUND}

    public AppointmentsPresenter(RetrieveAppointmentsView getAppointmentsView) {
        super((GenericView)getAppointmentsView);
        this.view = getAppointmentsView;
    }

    public UserDetails getSavedUserDetails() {
        return model.getUserDetails(view.getSharedPreferences());
    }


    public void showAppointmentsList(AppointmentsResponse appointmentsResponse) {
        view.displayAppointments(appointmentsResponse);
    }

    public void setMessage(String message) {
        this.message = message;
        view.showMessage(message);
    }

    public void setMessage(MessageToUser messageToUser) {
        this.message = view.getMessage(messageToUser);
        view.showMessage(this.message);
    }

    public String getMessage() {
        return message;
    }

    public String getWebHelperBaseURL() {
        return model.getWebHelperBaseURL(view.getSharedPreferences());
    }
}
