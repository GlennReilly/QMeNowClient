package demo.bluemongo.com.barcodescannertest1.view;

import android.content.SharedPreferences;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.model.Appointment;

/**
 * Created by glenn on 5/10/15.
 */
public interface RetrieveAppointmentsView {
    SharedPreferences getSharedPreferences();
    void retrieveAppointments();
    void displayAppointments(List<Appointment> appointmentsList);
    void showMessage(String message);
}
