package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.UserDetailsView;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetailsPresenter {
    UserDetailsView userDetailsView;
    private final QMeNowModel model = new QMeNowModel();
    private UserDetails userDetails;

    public String getUserDetailsPrefsString() {
        return model.USER_DETAILS_PREFERENCES;
    }

    public UserDetailsPresenter(UserDetailsView view) {
    this.userDetailsView = view;
    }

    public void saveUserDetails(String firstName, String lastName, String customerId) {
        userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setCustomerId(customerId);
        model.saveUserDetails(userDetails, userDetailsView.getSharedPreferences());
    }

    public UserDetails getSavedUserDetails() {
        return model.getUserDetails(userDetailsView.getSharedPreferences());
    }
}
