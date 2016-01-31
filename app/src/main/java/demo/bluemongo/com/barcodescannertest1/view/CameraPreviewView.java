package demo.bluemongo.com.barcodescannertest1.view;

import android.content.SharedPreferences;

import demo.bluemongo.com.barcodescannertest1.model.BusinessQRCodePayload;
import demo.bluemongo.com.barcodescannertest1.model.CustomerQRCodePayload;

/**
 * Created by glenn on 23/09/15.
 */
public interface CameraPreviewView {
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
