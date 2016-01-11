package demo.bluemongo.com.barcodescannertest1.view;

import android.content.SharedPreferences;

import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;

/**
 * Created by glenn on 5/10/15.
 */
public interface RetrieveAppointmentsView {
    SharedPreferences getSharedPreferences();
    void retrieveAppointments();
    void displayAppointments(AppointmentsResponse appointmentsResponse);
    void showMessage(String message);
}
