package demo.bluemongo.com.QMeNowClient.view;

import android.content.SharedPreferences;

import demo.bluemongo.com.QMeNowClient.model.AppointmentsResponse;
import demo.bluemongo.com.QMeNowClient.presenter.AppointmentsPresenter;
import io.realm.Realm;

/**
 * Created by glenn on 5/10/15.
 */
public interface RetrieveAppointmentsView {

    void retrieveAppointmentsFromWeb();
    void displayAppointments(AppointmentsResponse appointmentsResponse);
    void showMessage(String message);
    String getMessage(AppointmentsPresenter.MessageToUser messageToUser);
    SharedPreferences getUserDetailsSharedPreferences();
    SharedPreferences getBusinessDetailsSharedPreferences();
    SharedPreferences getAppSettingsSharedPreferences();
    void setupRealm();
    Realm getRealm();
    void setUIElementsFromSavedDetails();
}
