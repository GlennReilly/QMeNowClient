package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.GenericView;

/**
 * Created by glenn on 16/01/16.
 */
public class GenericPresenter {
    private final GenericView view;
    private static final QMeNowModel model = new QMeNowModel();

    public GenericPresenter(GenericView genericView){
        this.view = genericView;
    }

    public String getWebHelperBaseURL() {
        return model.getWebHelperBaseURL(view.getSharedPreferences());
    }

    public UserDetails getSavedUserDetails() {
        return model.getUserDetails(view.getSharedPreferences());
    }

    public static String getUserDetailsPrefsString() {
        return model.USER_DETAILS_PREFERENCES;
    }
}
