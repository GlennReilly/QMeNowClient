package demo.bluemongo.com.barcodescannertest1.view;

/**
 * Created by glenn on 23/09/15.
 */
public interface CameraPreviewView {
    void onBarcodeResult(String rawValue);

    void onValidBarcodeResult(String rawValue);

    void showInvalidBarcodeMessage();
}
