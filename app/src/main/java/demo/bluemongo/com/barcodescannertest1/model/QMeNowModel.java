package demo.bluemongo.com.barcodescannertest1.model;

/**
 * Created by glenn on 24/09/15.
 */

import android.content.SharedPreferences;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import demo.bluemongo.com.barcodescannertest1.utils.InputHelper;
import io.realm.Realm;
import io.realm.RealmList;
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

    private CustomerQRCodePayload  customerQRCodePayload = new CustomerQRCodePayload();
    private BusinessQRCodePayload businessQRCodePayload = new BusinessQRCodePayload();


    public boolean isBusinessBarcodeValid(String rawValue) {
        boolean result = false;
        //String dateTimeString = "";
        //String businessName = "";
        //String content = "";

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
        realm.clear(RealmAppointmentsResponse.class);

        //..add new
        RealmAppointmentsResponse realmAppointmentsResponse = realm.createObject(RealmAppointmentsResponse.class);
        realmAppointmentsResponse = getRealmObjectFromAppointmentsResponse(realmAppointmentsResponse, appointmentsResponse, realm);
        realm.commitTransaction();
        Log.i("setAppointmentsInCache", "realmAppointmentsResponse successfully added to Realm");
    }


    public AppointmentsResponse getAppointmentsFromCache(Realm realm) {
        RealmResults<RealmAppointmentsResponse> results =
                realm.where(RealmAppointmentsResponse.class).findAll();

        AppointmentsResponse appointmentsResponse = getAppointmentsResponseFromRealmObject(results);

        return appointmentsResponse;
    }

    private AppointmentsResponse getAppointmentsResponseFromRealmObject(RealmResults<RealmAppointmentsResponse> results) {
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();

        for(RealmAppointmentsResponse realmAppointmentsResponse : results) {
/*            for (RealmAppointment appointment : realmAppointmentsResponse.getAppointmentList()) {
                Log.i("realm appointment", appointment.getStatusName());
            }*/


        RealmList<RealmAppointment> realmAppointmentList = realmAppointmentsResponse.getAppointmentList();

        List<Appointment> appointments = new ArrayList<>();
        for (RealmAppointment realmAppointment : realmAppointmentList) {
            Appointment appointmentFromRealmAppointment = getAppointmentFromRealmAppointment(realmAppointment);
            appointments.add(appointmentFromRealmAppointment);
        }
        appointmentsResponse.setAppointmentList(appointments);

        List<AppointmentStatus> appointmentStatuses = new ArrayList<>();
        for (RealmAppointmentStatus realmAppointmentStatus : realmAppointmentsResponse.getAppointmentStatusList()) {
            AppointmentStatus appointmentStatusFromRealmAppointmentStatus = getAppointmentStatusFromRealmAppointmentStatus(realmAppointmentStatus);
            appointmentStatuses.add(appointmentStatusFromRealmAppointmentStatus);
        }
        appointmentsResponse.setAppointmentStatusList(appointmentStatuses);

        BusinessDTO businessFromRealmBusiness = getBusinessDTOFromRealmBusinessDTO(realmAppointmentsResponse.getBusiness());
            appointmentsResponse.setBusinessDTO(businessFromRealmBusiness);
        }
        return appointmentsResponse;
    }

    private BusinessDTO getBusinessDTOFromRealmBusinessDTO(RealmBusinessDTO realmBusinessDTO) {
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setId(realmBusinessDTO.getId());
        businessDTO.setBusinessName(realmBusinessDTO.getBusinessName());
        businessDTO.setBackgroundColourHexCode(realmBusinessDTO.getBackgroundColourHexCode());
        businessDTO.setHeaderColourHexCode(realmBusinessDTO.getHeaderColourHexCode());
        businessDTO.setButtonColourHexCode(realmBusinessDTO.getButtonColourHexCode());
        businessDTO.setLogoFileName(realmBusinessDTO.getLogoFileName());
        return businessDTO;
    }

    private AppointmentStatus getAppointmentStatusFromRealmAppointmentStatus(RealmAppointmentStatus realmAppointmentStatus) {
        return null; //TODO
    }


    private RealmAppointmentsResponse getRealmObjectFromAppointmentsResponse(RealmAppointmentsResponse realmAppointmentsResponse,
                                                                             AppointmentsResponse appointmentsResponse,
                                                                             Realm realm) {
        List<Appointment> appointmentList = appointmentsResponse.getAppointmentList();

        RealmList<RealmAppointment> realmAppointments = new RealmList<>();
        for (Appointment appointment : appointmentList) {
            RealmAppointment realmAppointmentFromAppointment = getRealmAppointmentFromAppointment(appointment);
            realmAppointmentFromAppointment = realm.copyToRealm(realmAppointmentFromAppointment);
            realmAppointments.add(realmAppointmentFromAppointment);
        }
        realmAppointmentsResponse.setAppointmentList(realmAppointments);

        RealmList<RealmAppointmentStatus> realmAppointmentStatuses = new RealmList<>();
        for (AppointmentStatus appointmentStatus : appointmentsResponse.getAppointmentStatusList()) {
            RealmAppointmentStatus realmAppointmentStatusFromAppointmentStatus = getRealmAppointmentStatusFromAppointmentStatus(appointmentStatus);
            realmAppointmentStatusFromAppointmentStatus = realm.copyToRealm(realmAppointmentStatusFromAppointmentStatus);
            realmAppointmentStatuses.add(realmAppointmentStatusFromAppointmentStatus);
        }

        realmAppointmentsResponse.setAppointmentStatusList(realmAppointmentStatuses);
        RealmBusinessDTO realmBusinessFromBusiness = getRealmBusinessFromBusiness(appointmentsResponse.getBusinessDTO());
        realmBusinessFromBusiness = realm.copyToRealm(realmBusinessFromBusiness);
        realmAppointmentsResponse.setBusiness(realmBusinessFromBusiness);


        return realmAppointmentsResponse;
    }

    private RealmBusinessDTO getRealmBusinessFromBusiness(BusinessDTO business) {
        RealmBusinessDTO output = new RealmBusinessDTO();
        output.setId(business.getId());
        output.setBusinessName(business.getBusinessName());
        output.setBackgroundColourHexCode(business.getBackgroundColourHexCode());
        output.setButtonColourHexCode(business.getButtonColourHexCode());
        output.setHeaderColourHexCode(business.getHeaderColourHexCode());
        output.setLogoFileName(business.getLogoFileName());
        return output;
    }

    private RealmAppointmentStatus getRealmAppointmentStatusFromAppointmentStatus(AppointmentStatus appointmentStatus) {
        RealmAppointmentStatus output = new RealmAppointmentStatus();

        output.setId(appointmentStatus.getId());
        output.setName(appointmentStatus.getName());
        output.setBackgroundColourHexCode(appointmentStatus.getBackgroundColourHexCode());
        output.setBusinessId(appointmentStatus.getBusinessId());
        output.setCustomerInitiated(appointmentStatus.isCustomerInitiated());
        output.setSequenceNumber(appointmentStatus.getSequenceNumber());

        return output;
    }

    private RealmAppointment getRealmAppointmentFromAppointment(Appointment appointment) {
        RealmAppointment output = new RealmAppointment();

        output.setId(appointment.getId());
        output.setCustomerId(appointment.getCustomerId());
        output.setAppointmentTypeId(appointment.getAppointmentTypeId());
        output.setAppointmentTypeName(appointment.getAppointmentTypeName());
        output.setAppointmentTypePrefix(appointment.getAppointmentTypePrefix());
        output.setAppTypeHexCode(appointment.getAppTypeHexCode());
        output.setComplete(appointment.isComplete());
        output.setLocationHexCode(appointment.getLocationHexCode());
        output.setLocationId(appointment.getLocationId());
        output.setLocationName(appointment.getLocationName());
        output.setMessageToCustomer(appointment.getMessageToCustomer());
        output.setStatus(appointment.getStatus());
        output.setStatusHexCode(appointment.getStatusHexCode());
        output.setStatusName(appointment.getStatusName());
        output.setStrAppointmentDate(appointment.getStrAppointmentDate());
        output.setStrAppointmentTime(appointment.getStrAppointmentTime());
        output.setStrCheckInDateTime(appointment.getStrCheckInDateTime());

        return output;
    }

    private Appointment getAppointmentFromRealmAppointment(RealmAppointment realmAppointment) {
        Appointment output = new Appointment();

        output.setId(realmAppointment.getId());
        output.setCustomerId(realmAppointment.getCustomerId());
        output.setAppointmentTypeId(realmAppointment.getAppointmentTypeId());
        output.setAppointmentTypeName(realmAppointment.getAppointmentTypeName());
        output.setAppointmentTypePrefix(realmAppointment.getAppointmentTypePrefix());
        output.setAppTypeHexCode(realmAppointment.getAppTypeHexCode());
        output.setIsComplete(realmAppointment.isComplete());
        output.setLocationHexCode(realmAppointment.getLocationHexCode());
        output.setLocationId(realmAppointment.getLocationId());
        output.setLocationName(realmAppointment.getLocationName());
        output.setMessageToCustomer(realmAppointment.getMessageToCustomer());
        output.setStatus(realmAppointment.getStatus());
        output.setStatusHexCode(realmAppointment.getStatusHexCode());
        output.setStatusName(realmAppointment.getStatusName());
        output.setStrAppointmentDate(realmAppointment.getStrAppointmentDate());
        output.setStrAppointmentTime(realmAppointment.getStrAppointmentTime());
        output.setStrCheckInDateTime(realmAppointment.getStrCheckInDateTime());

        return output;
    }




}
