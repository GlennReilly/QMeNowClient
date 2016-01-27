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

    public void processBarcodeValue(CameraPreviewView.BarcodeType barcodeType, String barcodeContent) {
        switch(barcodeType){
            case BUSINESS: processBusinessBarcode(barcodeContent);
                break;
            case CUSTOMER: processCustomerBarcode(barcodeContent);
                break;
        }
    }


    private void processBusinessBarcode(String barcodeContent) {
        if (model.isBusinessBarcodeValid(barcodeContent)) {
            view.onValidBusinessBarcodeResult(barcodeContent);
        }else{
            view.showInvalidBusinessBarcodeMessage();
        }
    }

    private void processCustomerBarcode(String barcodeContent) {
        if (model.isCustomerBarcodeValid(barcodeContent)) {
            view.onValidCustomerBarcodeResult(model.getQRCodePayload());
        }else{
            view.showInvalidCustomerBarcodeMessage();
        }
    }
}
