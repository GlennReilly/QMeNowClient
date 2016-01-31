package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.GenericView;
import demo.bluemongo.com.barcodescannertest1.view.UserDetailsView;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetailsPresenter extends GenericPresenter{
    UserDetailsView userDetailsView;
    private final QMeNowModel model = new QMeNowModel();
    private UserDetails userDetails;




    public UserDetailsPresenter(UserDetailsView view) {
        super((GenericView) view);
        this.userDetailsView = view;
    }

    public void saveUserDetails(String firstName, String lastName, Integer customerId) {
        userDetails = new UserDetails();
        userDetails.setFirstName(firstName);
        userDetails.setLastName(lastName);
        userDetails.setCustomerId(customerId);
        model.saveUserDetails(userDetails, userDetailsView.getUserDetailsSharedPreferences());
    }

}
