package demo.bluemongo.com.QMeNowClient.presenter;

import android.content.SharedPreferences;

import demo.bluemongo.com.QMeNowClient.model.BusinessDTO;
import demo.bluemongo.com.QMeNowClient.model.QMeNowModel;
import demo.bluemongo.com.QMeNowClient.view.CameraPreviewView;
import demo.bluemongo.com.QMeNowClient.view.GenericViewImpl;

/**
 * Created by glenn on 23/09/15.
 */
public class CameraPreviewPresenter extends GenericPresenter {
    private final CameraPreviewView view;
    private final QMeNowModel model = new QMeNowModel();

    public CameraPreviewPresenter(CameraPreviewView view) {
        super((GenericViewImpl) view);
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

            BusinessDTO businessDTO = model.getBusinessQRCodePayload().getBusinessDTO();
            int incomingBusinessId = businessDTO.getId();
            SharedPreferences businessDetailsSharedPreferences = view.getBusinessDetailsSharedPreferences();
            model.clearCacheIfDifferentBusiness(incomingBusinessId, businessDetailsSharedPreferences, view.getRealm());

            model.saveBusinessDetails(businessDTO, businessDetailsSharedPreferences);
            model.saveWebHelperBaseURL(businessDTO.getServerURL(), view.getAppSettingsSharedPreferences());
            view.showUsersAppointments(model.getBusinessQRCodePayload());
        }else{
            view.showInvalidBusinessBarcodeMessage();
        }
    }


    private void processCustomerBarcode(String barcodeContent) {
        if (model.isCustomerBarcodeValid(barcodeContent)) {
            model.saveBusinessDetails(model.getBusinessQRCodePayload().getBusinessDTO(), view.getBusinessDetailsSharedPreferences());
            model.saveWebHelperBaseURL(model.getBusinessQRCodePayload().getBusinessDTO().getServerURL(), view.getAppSettingsSharedPreferences());
            view.onValidCustomerBarcodeResult(model.getCustomerQRCodePayload());
        }else{
            view.showInvalidCustomerBarcodeMessage();
        }
    }
}
