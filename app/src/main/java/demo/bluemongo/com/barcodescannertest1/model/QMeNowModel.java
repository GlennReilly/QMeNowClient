package demo.bluemongo.com.barcodescannertest1.model;

/**
 * Created by glenn on 24/09/15.
 */

import android.content.SharedPreferences;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import demo.bluemongo.com.barcodescannertest1.utils.InputHelper;


public class QMeNowModel {
    public static final String USER_DETAILS_PREFERENCES = "USER_DETAILS_PREFERENCES";
    public static final String SETTINGS__PREFERENCES = "SETTINGS__PREFERENCES";

    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String CUSTOMERID = "customerId";
    final String EMPTY = "";
    final String WEBHELPER_BASEURL = "WebHelperBaseUrl";
    final String DEFAULT_WEBHELPER_BASEURL = "http://10.1.1.7:8080/";
    private CustomerQRCodePayload customerQRCodePayload = null;

    public demo.bluemongo.com.barcodescannertest1.model.CustomerQRCodePayload getCustomerQRCodePayload() {
        return customerQRCodePayload;
    }

    public boolean isBusinessBarcodeValid(String rawValue) {
        boolean result = false;
        //String dateTimeString = "";
        String businessName = "";
        String content = "";

        try {
            JSONObject jsonObject = new JSONObject(rawValue);
            //dateTimeString = jsonObject.getString("dateTimeString");
            //businessName = jsonObject.getString("businessName");
            content = jsonObject.getString("Content");
            BusinessQRCodePayload businessQRCodePayload = new BusinessQRCodePayload();
            businessQRCodePayload.setDateTimeString(jsonObject.getString("dateTimeString"));
            businessQRCodePayload.setBusinessName(jsonObject.getString("businessName"));
            businessQRCodePayload.setContent(jsonObject.getString("Content"));

            businessQRCodePayload.setButtonColourHexCode(jsonObject.getString("buttonColourHexCode"));
            businessQRCodePayload.setHeaderColourHexCode(jsonObject.getString("headerColourHexCode"));
            businessQRCodePayload.setBackgroundColourHexCode(jsonObject.getString("backgroundColourHexCode"));
            businessQRCodePayload.setFooterColourHexCode(jsonObject.getString("footerColourHexCode"));

            Date barcodeDate = InputHelper.getDateFromISO8601String(businessQRCodePayload.getDateTimeString()); //formatter.parse(dateTimeString);
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

    public boolean isCustomerBarcodeValid(String barcodeContent) {
        boolean result = false;

        try {
            JSONObject jsonObject = new JSONObject(barcodeContent);
            CustomerQRCodePayload customerQRCodePayload = new CustomerQRCodePayload();
            customerQRCodePayload.setDateTimeString(jsonObject.getString("dateTimeString"));
            customerQRCodePayload.setCustomerFirstName(jsonObject.getString("customerFirstName"));
            customerQRCodePayload.setCustomerLastName(jsonObject.getString("customerLastName"));
            customerQRCodePayload.setCustomerId(jsonObject.getInt("customerId"));
            customerQRCodePayload.setContent(jsonObject.getString("Content"));

            Date barcodeDate = InputHelper.getDateFromISO8601String(customerQRCodePayload.getDateTimeString()); //formatter.parse(dateTimeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(barcodeDate);
            //calendar.add(Calendar.HOUR_OF_DAY, 1);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            Date barcodeDateExpiryDate = calendar.getTime();
            Date now = new Date();
            if ((barcodeDateExpiryDate.compareTo(now) > 0)
                    && (StringUtils.isNotBlank(customerQRCodePayload.getCustomerFirstName()))
                    && (StringUtils.isNotBlank(customerQRCodePayload.getCustomerLastName()))
                    & (customerQRCodePayload.getCustomerId() > 0)){
                result = true;
                this.customerQRCodePayload = customerQRCodePayload;
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
        editor.putInt(CUSTOMERID, userDetails.getCustomerId());
        editor.commit();
    }

    public UserDetails getUserDetails(SharedPreferences settings){
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(settings.getString(FIRSTNAME, EMPTY));
        userDetails.setLastName(settings.getString(LASTNAME, EMPTY));
        userDetails.setCustomerId(settings.getInt(CUSTOMERID, 0));

        return userDetails;
    }

    public String getWebHelperBaseURL(SharedPreferences settings){
        return settings.getString(WEBHELPER_BASEURL, DEFAULT_WEBHELPER_BASEURL);
    }

    public void saveWebHelperBaseURL(String webHelperBaseURL, SharedPreferences settings){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(WEBHELPER_BASEURL, webHelperBaseURL);
        editor.commit();
    }


}
