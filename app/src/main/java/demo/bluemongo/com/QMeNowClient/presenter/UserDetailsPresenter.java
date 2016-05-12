package demo.bluemongo.com.QMeNowClient.presenter;

import demo.bluemongo.com.QMeNowClient.model.UserDetails;
import demo.bluemongo.com.QMeNowClient.view.GenericViewImpl;
import demo.bluemongo.com.QMeNowClient.view.UserDetailsView;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetailsPresenter extends GenericPresenter{
    UserDetailsView userDetailsView;
    //private final QMeNowModel model = new QMeNowModel();
    private UserDetails userDetails;




    public UserDetailsPresenter(UserDetailsView view) {
        super((GenericViewImpl) view);
        this.userDetailsView = view;
    }

    public void saveUserDetails(String firstName, String lastName, Integer customerId) {
        UserDetails currentUserDetails = model.getUserDetails(userDetailsView.getUserDetailsSharedPreferences());
        if(currentUserDetails.getCustomerId() != customerId){
            model.clearRealmAppointmentCache(view.getRealm());
        }

        userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setCustomerId(customerId);
        model.saveUserDetails(userDetails, userDetailsView.getUserDetailsSharedPreferences());

    }

    public void removeUserDetails() {
        model.removeUserDetails(userDetailsView.getUserDetailsSharedPreferences());
    }
}
