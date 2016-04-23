package demo.bluemongo.com.barcodescannertest1.view;

import android.content.SharedPreferences;

import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.presenter.AppointmentsPresenter;
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
