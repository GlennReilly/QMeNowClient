package demo.bluemongo.com.QMeNowClient.presenter;

import demo.bluemongo.com.QMeNowClient.model.AppointmentsResponse;
import demo.bluemongo.com.QMeNowClient.view.GenericView;
import demo.bluemongo.com.QMeNowClient.view.GenericViewImpl;

/**
 * Created by glenn on 27/09/15.
 */
public class MainPresenter extends GenericPresenter {
    private final GenericView view;


    public MainPresenter(GenericView view){
        super((GenericViewImpl) view);
        this.view = view;
    }

    public boolean isAppointmentsCacheNotEmpty() {
        boolean result = false;
        AppointmentsResponse appointmentsResponse = model.getAppointmentsFromCache(view.getRealm());
        if(appointmentsResponse == null){
            result = false;
        }
        else {
            if (appointmentsResponse.getAppointmentList().size() == 0) {
                result = false;
            }
            else {
                result = true;
            }
        }

        return result;
    }



/*    public String getFooterBackgroundColour() {
        SharedPreferences businessDetailsSharedPreferences = ((GenericViewImpl)view).getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.FOOTER_COLOUR_HEX_CODE, "");
    }*/
}
