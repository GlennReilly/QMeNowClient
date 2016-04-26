package demo.bluemongo.com.QMeNowClient.presenter;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import demo.bluemongo.com.QMeNowClient.R;
import demo.bluemongo.com.QMeNowClient.model.QMeNowModel;
import demo.bluemongo.com.QMeNowClient.model.UserDetails;
import demo.bluemongo.com.QMeNowClient.view.GenericViewImpl;

/**
 * Created by glenn on 16/01/16.
 */
public class GenericPresenter {
    protected final GenericViewImpl view;
    protected static final QMeNowModel model = new QMeNowModel();

    public GenericPresenter(GenericViewImpl genericView){
        this.view = genericView;
    }

    public String getWebHelperBaseURL() {
        return model.getWebHelperBaseURL(view.getAppSettingsSharedPreferences());
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

    public int getBusinessId() {
        SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getInt(model.BUSINESS_ID, 0);
    }

    public String getLogoFileName() {
        SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.LOGO_FILE_NAME, "no_logo.png");
    }

    public void setGenericUIStuff() {
        int color = Color.parseColor(getHeaderBackgroundColour());
        String logoFileName = getLogoFileName();

        ActionBar actionBar = view.getActivity().getActionBar();
        actionBar.setTitle(getBusinessName());
        actionBar.setBackgroundDrawable(new ColorDrawable(color));
        actionBar.setIcon(R.drawable.app_logo);
        PicassoActionBarIcon picasssoActionBar = new PicassoActionBarIcon(actionBar);
        //http://10.1.1.7:8080/resources/images/noLogo.png
        String URI = model.getWebHelperBaseURL(view.getBusinessDetailsSharedPreferences()) + "resources/images/" + logoFileName;

        Picasso.with(view.getActivity()).load( URI).into(picasssoActionBar);
    }



    private class PicassoActionBarIcon implements Target {

        private final ActionBar actionBar;

        public PicassoActionBarIcon(ActionBar actionBar) {
            this.actionBar = actionBar;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable drawable = new BitmapDrawable(view.getResources(), bitmap);
            actionBar.setIcon(drawable);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            actionBar.setIcon(R.drawable.no_logo);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }
}
