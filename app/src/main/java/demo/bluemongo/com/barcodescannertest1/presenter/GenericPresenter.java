package demo.bluemongo.com.barcodescannertest1.presenter;

import android.content.SharedPreferences;

import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.view.GenericView;

/**
 * Created by glenn on 16/01/16.
 */
public class GenericPresenter {
    protected final GenericView view;
    protected static final QMeNowModel model = new QMeNowModel();

    public GenericPresenter(GenericView genericView){
        this.view = genericView;
    }

    public String getWebHelperBaseURL() {
        return model.getWebHelperBaseURL(view.getUserDetailsSharedPreferences());
    }

    public UserDetails getSavedUserDetails() {
        return model.getUserDetails(view.getUserDetailsSharedPreferences());
    }

    public static QMeNowModel getModel() {
        return model;
    }

    public String getUserDetailsPrefsString() {
        return model.USER_DETAILS_PREFERENCES;
    }

    public String getBusinessDetailsPrefsString() {
        return model.BUSINESS_DETAILS_PREFERENCES;
    }

    public String getAppSettingsPrefsString() {
        return model.SETTINGS__PREFERENCES;
    }

    public String getButtonBackgroundColour() {
        SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.BUTTON_COLOUR_HEX_CODE, "");
    }

    public String getHeaderBackgroundColour() {
        SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.HEADER_COLOUR_HEX_CODE, "");
    }

    public String getBackgroundBackgroundColour() {
        SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.BACKGROUND_COLOUR_HEX_CODE, "");
    }

    public String getBusinessName() {
        SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.BUSINESS_NAME, "");
    }
}
