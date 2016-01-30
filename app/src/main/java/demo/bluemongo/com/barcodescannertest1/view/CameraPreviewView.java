package demo.bluemongo.com.barcodescannertest1.view;

import demo.bluemongo.com.barcodescannertest1.model.CustomerQRCodePayload;

/**
 * Created by glenn on 23/09/15.
 */
public interface CameraPreviewView {
    public final String ScanningForBarcodeType = "ScanningForBarcodeType";
    public enum BarcodeType{BUSINESS, CUSTOMER}
    void onBarcodeResult(String rawValue);
    void onValidBusinessBarcodeResult(String rawValue);
    void showInvalidBusinessBarcodeMessage();
    void onValidCustomerBarcodeResult(CustomerQRCodePayload rawValue);
    void showInvalidCustomerBarcodeMessage();
}
