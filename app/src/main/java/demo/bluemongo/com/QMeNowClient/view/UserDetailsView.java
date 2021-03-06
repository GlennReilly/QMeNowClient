package demo.bluemongo.com.QMeNowClient.view;

import android.content.SharedPreferences;
import android.widget.EditText;

/**
 * Created by glenn on 27/09/15.
 */
public interface UserDetailsView {
    void saveUserDetails(EditText etFirstName, EditText etLastName, EditText etCustomerId);
    void removeUserDetails();
    void setUIElementsFromSavedDetails();

    SharedPreferences getUserDetailsSharedPreferences();
    SharedPreferences getBusinessDetailsSharedPreferences();
    SharedPreferences getAppSettingsSharedPreferences();

}
