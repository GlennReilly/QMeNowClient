package demo.bluemongo.com.QMeNowClient.view;

import android.content.SharedPreferences;

import demo.bluemongo.com.QMeNowClient.model.BusinessQRCodePayload;
import demo.bluemongo.com.QMeNowClient.model.CustomerQRCodePayload;

/**
 * Created by glenn on 23/09/15.
 */
public interface CameraPreviewView extends GenericView {
    public final String ScanningForBarcodeType = "ScanningForBarcodeType";
    public enum BarcodeType{BUSINESS, CUSTOMER}

    void onBarcodeResult(String rawValue);
    void showUsersAppointments(BusinessQRCodePayload businessQRCodePayload);
    void showInvalidBusinessBarcodeMessage();
    void onValidCustomerBarcodeResult(CustomerQRCodePayload customerQRCodePayload);
    void showInvalidCustomerBarcodeMessage();
    SharedPreferences getUserDetailsSharedPreferences();
    SharedPreferences getBusinessDetailsSharedPreferences();
    SharedPreferences getAppSettingsSharedPreferences();
}
