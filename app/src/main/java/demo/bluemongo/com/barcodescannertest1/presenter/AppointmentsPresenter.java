package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.GenericView;
import demo.bluemongo.com.barcodescannertest1.view.RetrieveAppointmentsView;

/**
 * Created by glenn on 5/10/15.
 */
public class AppointmentsPresenter extends GenericPresenter {
    private final RetrieveAppointmentsView view;
    private String message;

    public void setAppointmentsInCache(AppointmentsResponse appointmentsResponse) {
        model.setAppointmentsInCache(appointmentsResponse, view.getRealm());
    }

    public AppointmentsResponse getAppointmentsFromCache() {
        return model.getAppointmentsFromCache(view.getRealm());
    }

    public enum MessageToUser {NO_APPOINTMENTS_FOUND, CUSTOMER_NOT_IN_THIS_BUSINESS }


    public AppointmentsPresenter(RetrieveAppointmentsView getAppointmentsView) {
        super((GenericView)getAppointmentsView);
        this.view = getAppointmentsView;
    }

    public UserDetails getSavedUserDetails() {
        return model.getUserDetails(view.getUserDetailsSharedPreferences());
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

}
