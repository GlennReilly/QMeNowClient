package demo.bluemongo.com.barcodescannertest1.view;

import android.content.SharedPreferences;
import android.widget.EditText;

/**
 * Created by glenn on 27/09/15.
 */
public interface UserDetailsView {
    void saveUserDetails(EditText etFirstName, EditText etLastName, EditText etCustomerId);

    SharedPreferences getSharedPreferences();
}
