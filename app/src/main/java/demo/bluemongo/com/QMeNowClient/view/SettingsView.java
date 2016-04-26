package demo.bluemongo.com.QMeNowClient.view;

import android.content.SharedPreferences;

/**
 * Created by glenn on 14/01/16.
 */
public interface SettingsView {
    SharedPreferences getUserDetailsSharedPreferences();
    SharedPreferences getBusinessDetailsSharedPreferences();
    SharedPreferences getAppSettingsSharedPreferences();
    void setUIElementsFromSavedDetails();
}
