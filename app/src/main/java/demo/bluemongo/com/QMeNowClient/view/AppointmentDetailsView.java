package demo.bluemongo.com.QMeNowClient.view;

import android.content.SharedPreferences;

import java.util.List;

import demo.bluemongo.com.QMeNowClient.model.Appointment;
import demo.bluemongo.com.QMeNowClient.model.AppointmentStatus;

/**
 * Created by glenn on 30/12/15.
 */
public interface AppointmentDetailsView {
    void checkInAppointment(Appointment appointment, List<AppointmentStatus> appointmentStatusList);
    SharedPreferences getUserDetailsSharedPreferences();
    SharedPreferences getBusinessDetailsSharedPreferences();
    SharedPreferences getAppSettingsSharedPreferences();
    void setUIElementsFromSavedDetails();
}
