package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.view.CameraPreviewView;

/**
 * Created by glenn on 23/09/15.
 */
public class CameraPreviewPresenter {
    private final CameraPreviewView view;
    private final QMeNowModel model = new QMeNowModel();

    public CameraPreviewPresenter(CameraPreviewView view) {
        this.view = view;
    }

    public void showCameraPreview() {

    }

    public void processBarcodeValue(String barcodeContent) {
        if (model.isBarcodeValid(barcodeContent)) {
         view.onValidBarcodeResult(barcodeContent);
        }else{
            view.showInvalidBarcodeMessage();
        }

    }
}
