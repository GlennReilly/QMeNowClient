package demo.bluemongo.com.QMeNowClient.model;

/**
 * Created by glenn on 24/09/15.
 */

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import demo.bluemongo.com.QMeNowClient.utils.InputHelper;
import io.realm.Realm;
import io.realm.RealmResults;


public class QMeNowModel {
    public static final String USER_DETAILS_PREFERENCES = "USER_DETAILS_PREFERENCES";
    public static final String BUSINESS_DETAILS_PREFERENCES = "BUSINESS_DETAILS_PREFERENCES";
    public static final String SETTINGS__PREFERENCES = "SETTINGS__PREFERENCES";

    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String CUSTOMERID = "customerId";
    public static final String BUSINESS_ID = "businessId";
    public static final String ERROR_CUSTOMER_NOT_IN_THIS_BUSINESS = "CUSTOMER_NOT_IN_THIS_BUSINESS";
    public final String EMPTY = "";
    public final String WEBHELPER_BASEURL = "WebHelperBaseUrl";
    public final String DEFAULT_WEBHELPER_BASEURL = "http://10.1.1.7:8080/";

    public final String BUSINESS_NAME = "BUSINESS_NAME";
    public final String LOGO_FILE_NAME = "LOGO_FILE_NAME";
    public final String BUTTON_COLOUR_HEX_CODE = "BUTTON_COLOUR_HEX_CODE";
    public final String HEADER_COLOUR_HEX_CODE = "HEADER_COLOUR_HEX_CODE";
    public final String BACKGROUND_COLOUR_HEX_CODE = "BACKGROUND_COLOUR_HEX_CODE";
    private final RealmAppointmentAdapter realmAppointmentAdapter = new RealmAppointmentAdapter();

    private CustomerQRCodePayload  customerQRCodePayload = new CustomerQRCodePayload();
    private BusinessQRCodePayload businessQRCodePayload = new BusinessQRCodePayload();


    public boolean isBusinessBarcodeValid(String rawValue) {
        boolean result = false;

        try {
            JSONObject jsonObject = new JSONObject(rawValue);
            JSONObject jsonBusinessDTO = jsonObject.getJSONObject("businessDTO");

            businessQRCodePayload.setDateTimeString(jsonObject.getString("dateTimeString"));
            Date barcodeDate = InputHelper.getDateFromISO8601String(businessQRCodePayload.getDateTimeString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(barcodeDate);
            //calendar.add(Calendar.HOUR_OF_DAY, 1);
            calendar.add(Calendar.HOUR_OF_DAY, 12);
            Date barcodeDateExpiryDate = calendar.getTime();
            Date now = new Date();
            if (barcodeDateExpiryDate.compareTo(now) > 0){   //.. does the barcode expire in the future?
                //barcodeDateWithAddedTime is later than now, so still within grace period.
                businessQRCodePayload.setDateTimeString(jsonObject.getString("dateTimeString"));
                businessQRCodePayload.setContent(jsonObject.getString("Content"));
                businessQRCodePayload.getBusinessDTO().setId(jsonBusinessDTO.getInt("id"));
                businessQRCodePayload.setBusinessName(jsonBusinessDTO.getString("businessName"));
                businessQRCodePayload.setButtonColourHexCode(jsonBusinessDTO.getString("buttonColourHexCode"));
                businessQRCodePayload.setHeaderColourHexCode(jsonBusinessDTO.getString("headerColourHexCode"));
                businessQRCodePayload.setBackgroundColourHexCode(jsonBusinessDTO.getString("backgroundColourHexCode"));
                businessQRCodePayload.setLogoFileName(jsonBusinessDTO.getString("logoFileName"));
                businessQRCodePayload.getBusinessDTO().setServerURL(jsonBusinessDTO.getString("serverURL"));
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
            JSONObject jsonBusinessDTO = jsonObject.getJSONObject("businessDTO");
            customerQRCodePayload.getBusinessDTO().setBusinessName(jsonBusinessDTO.getString("businessName"));
            customerQRCodePayload.getBusinessDTO().setButtonColourHexCode(jsonBusinessDTO.getString("buttonColourHexCode"));
            customerQRCodePayload.getBusinessDTO().setHeaderColourHexCode(jsonBusinessDTO.getString("headerColourHexCode"));
            customerQRCodePayload.getBusinessDTO().setBackgroundColourHexCode(jsonBusinessDTO.getString("backgroundColourHexCode"));
            customerQRCodePayload.getBusinessDTO().setLogoFileName(jsonBusinessDTO.getString("logoFileName"));

            customerQRCodePayload.setDateTimeString(jsonObject.getString("dateTimeString"));
            customerQRCodePayload.setCustomerFirstName(jsonObject.getString("customerFirstName"));
            customerQRCodePayload.setCustomerLastName(jsonObject.getString("customerLastName"));
            customerQRCodePayload.setCustomerId(jsonObject.getInt("customerId"));

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
                    customerQRCodePayload.setDateTimeString(jsonObject.getString("dateTimeString"));
                    customerQRCodePayload.setCustomerFirstName(jsonObject.getString("customerFirstName"));
                    customerQRCodePayload.setCustomerLastName(jsonObject.getString("customerLastName"));
                    customerQRCodePayload.setCustomerId(jsonObject.getInt("customerId"));
                    customerQRCodePayload.setContent(jsonObject.getString("Content"));
                    customerQRCodePayload.getBusinessDTO().setServerURL(jsonBusinessDTO.getString("serverURL"));
                    result = true;
            }
            else{
                customerQRCodePayload = new CustomerQRCodePayload();
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
        editor.apply();
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
        editor.apply();
    }

    public BusinessQRCodePayload getBusinessQRCodePayload() {
        return businessQRCodePayload;
    }

    public CustomerQRCodePayload getCustomerQRCodePayload() {
        return customerQRCodePayload;
    }

    public void saveBusinessDetails(BusinessDTO businessDTO, SharedPreferences businessDetailsSharedPreferences) {

        SharedPreferences.Editor editor = businessDetailsSharedPreferences.edit();
        editor.putInt(BUSINESS_ID, businessDTO.getId());
        editor.putString(BUSINESS_NAME, businessDTO.getBusinessName());
        editor.putString(BUTTON_COLOUR_HEX_CODE, businessDTO.getButtonColourHexCode());
        editor.putString(HEADER_COLOUR_HEX_CODE, businessDTO.getHeaderColourHexCode());
        editor.putString(BACKGROUND_COLOUR_HEX_CODE, businessDTO.getBackgroundColourHexCode());
        editor.putString(LOGO_FILE_NAME, businessDTO.getLogoFileName());

        editor.apply();
    }

    public void removeUserDetails(SharedPreferences settings) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(FIRSTNAME, "");
        editor.putString(LASTNAME, "");
        editor.putInt(CUSTOMERID, 0);
        editor.apply();
    }



    public void setAppointmentsInCache(AppointmentsResponse appointmentsResponse, Realm realm) {

        realm.beginTransaction();
        //clear existing..
        realm.delete(RealmAppointmentsResponse.class);


        //..add new
        RealmAppointmentsResponse realmAppointmentsResponse = realm.createObject(RealmAppointmentsResponse.class);
        realmAppointmentsResponse = realmAppointmentAdapter.getRealmObjectFromAppointmentsResponse(realmAppointmentsResponse, appointmentsResponse, realm);
        realm.commitTransaction();
        Log.i("setAppointmentsInCache", "realmAppointmentsResponse successfully added to Realm");
    }

    public void removeRealmAppointmentFromCache(List<RealmAppointment> realmAppointments, RealmAppointment realmAppointment, Realm realm){
        realmAppointments.remove(realmAppointment);
        Log.i("removeRealmAppointment", "realmAppointments successfully removed from Realm");
    }


    public AppointmentsResponse getAppointmentsFromCache(Realm realm) {
        RealmResults<RealmAppointmentsResponse> results = realm.where(RealmAppointmentsResponse.class).findAll();
        //only keep those appointments fro today and later, remove any earlier ones
        Calendar cal = Calendar.getInstance();
        InputHelper.resetTimeOfDate(cal);

    //testing only >>
        //Log.i("QMeNowModel - testing","cal.getTime().toString() before: " + cal.getTime().toString());
        //cal.add(Calendar.DATE, 1);
        //Log.i("QMeNowModel - testing","cal.getTime().toString() after: " + cal.getTime().toString());
    //<< testing only

        Date dateAtMidnight = cal.getTime();

        RealmAppointmentsResponse realmAppointmentsResponse;
        realm.beginTransaction();
        for(int j=0; j< results.size(); j++) {
            realmAppointmentsResponse = results.get(j);

                for(int i=0; i < realmAppointmentsResponse.getAppointmentList().size(); i++){
                    RealmAppointment realmAppointment = realmAppointmentsResponse.getAppointmentList().get(i);
                    if (!StringUtils.isBlank(realmAppointment.getStrAppointmentDate())){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            Date appointmentDate = sdf.parse(realmAppointment.getStrAppointmentDate());
                            Log.i("QMeNowModel","realmAppointment.getStrAppointmentDate(): " + realmAppointment.getStrAppointmentDate());
                            if (appointmentDate.before(dateAtMidnight)){
                                Log.i("QMeNowModel","appointment is before today");
                                removeRealmAppointmentFromCache(realmAppointmentsResponse.getAppointmentList(), realmAppointment, realm);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
        realm.commitTransaction();

        AppointmentsResponse appointmentsResponseFromRealmObject = realmAppointmentAdapter.getAppointmentsResponseFromRealmObject(results);
        return appointmentsResponseFromRealmObject;
    }


    public void updateAppointmentCheckInTimeInCache(Realm realm, Appointment appointment) {
        RealmResults<RealmAppointmentsResponse> results = realm.where(RealmAppointmentsResponse.class).findAll();
        Calendar cal = Calendar.getInstance();

        RealmAppointmentsResponse realmAppointmentsResponse;
        realm.beginTransaction();
        for(int j=0; j< results.size(); j++) {
            realmAppointmentsResponse = results.get(j);

            for(int i=0; i < realmAppointmentsResponse.getAppointmentList().size(); i++){
                RealmAppointment realmAppointment = realmAppointmentsResponse.getAppointmentList().get(i);
                if (realmAppointment.getId() == appointment.getId()){
                    Date now = cal.getTime();
                    String nowString = InputHelper.getISO8601StringFromDate(now);
                    realmAppointment.setStrCheckInDateTime(nowString);
                    Log.i("QMeNowModel","updated check-in date for appointment with id:" + appointment.getId());
                }
            }
        }
        realm.commitTransaction();
    }

    public void clearRealmAppointmentCache(Realm realm) {
        realm.beginTransaction();
        realm.delete(RealmAppointmentsResponse.class);
        realm.commitTransaction();
    }

    @NonNull
    public void clearCacheIfDifferentBusiness(int incomingBusinessId, SharedPreferences businessDetailsSharedPreferences, Realm realm) {
        int existingBusinessId = businessDetailsSharedPreferences.getInt(BUSINESS_ID,0);
        if (existingBusinessId != incomingBusinessId){
            clearRealmAppointmentCache(realm);
        }
    }
}
