package demo.bluemongo.com.barcodescannertest1.view;

import android.content.SharedPreferences;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentStatus;

/**
 * Created by glenn on 30/12/15.
 */
public interface AppointmentDetailsView {
    void progressAppointmentStatus(Appointment appointment, List<AppointmentStatus> appointmentStatusList);

    SharedPreferences getSharedPreferences();
}
