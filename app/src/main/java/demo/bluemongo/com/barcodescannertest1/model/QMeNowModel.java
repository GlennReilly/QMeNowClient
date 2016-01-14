package demo.bluemongo.com.barcodescannertest1.model;

/**
 * Created by glenn on 24/09/15.
 */

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import demo.bluemongo.com.barcodescannertest1.utils.InputHelper;


public class QMeNowModel {
    public static final String USER_DETAILS_PREFERENCES = "USER_DETAILS_PREFERENCES";
    public static final String SETTINGS__PREFERENCES = "SETTINGS__PREFERENCES";

    final String FIRSTNAME = "firstName";
    final String LASTNAME = "lastName";
    final String CUSTOMERID = "customerId";
    final String EMPTY = "";
    final String WEBHELPER_BASEURL = "WebHelperBaseUrl";

    public boolean isBarcodeValid(String rawValue) {
        boolean result = false;
        String dateTimeformat = "";
        String dateTimeString = "";
        String customerName = "";
        String content = "";

        try {
            JSONObject jsonObject = new JSONObject(rawValue);
             //dateTimeformat = jsonObject.getString("DateTimeFormat");
             dateTimeString = jsonObject.getString("DateTimeString");
             customerName = jsonObject.getString("CustomerName");
             content = jsonObject.getString("Content");
            //SimpleDateFormat formatter = new SimpleDateFormat(dateTimeformat);
            Date barcodeDate = InputHelper.getDateFromISO8601String(dateTimeString); //formatter.parse(dateTimeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(barcodeDate);
            //calendar.add(Calendar.HOUR_OF_DAY, 1);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            Date barcodeDateExpiryDate = calendar.getTime();
            Date now = new Date();
            if (barcodeDateExpiryDate.compareTo(now) > 0){   //.. does the barcode expire in the future?
                //barcodeDateWithAddedTime is later than now, so still within grace period.
                result = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveUserDetails(UserDetails userDetails, SharedPreferences settings) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(FIRSTNAME, userDetails.getFirstName());
        editor.putString(LASTNAME, userDetails.getLastName());
        editor.putString(CUSTOMERID, userDetails.getCustomerId());
        editor.commit();
    }

    public UserDetails getUserDetails(SharedPreferences settings){
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(settings.getString(FIRSTNAME, EMPTY));
        userDetails.setLastName(settings.getString(LASTNAME, EMPTY));
        userDetails.setCustomerId(settings.getString(CUSTOMERID, EMPTY));

        return userDetails;
    }

    public String getWebHelperBaseURL(SharedPreferences settings){
        return settings.getString(WEBHELPER_BASEURL, EMPTY);
    }

    public void saveWebHelperBaseURL(String webHelperBaseURL, SharedPreferences settings){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(WEBHELPER_BASEURL, webHelperBaseURL);
        editor.commit();
    }




}
