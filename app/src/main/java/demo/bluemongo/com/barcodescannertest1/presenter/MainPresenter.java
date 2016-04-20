package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.view.GenericView;
import demo.bluemongo.com.barcodescannertest1.view.MainView;

/**
 * Created by glenn on 27/09/15.
 */
public class MainPresenter extends GenericPresenter {
    private final MainView view;


    public MainPresenter(MainView view){
        super((GenericView) view);
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
        SharedPreferences businessDetailsSharedPreferences = ((GenericView)view).getBusinessDetailsSharedPreferences();
        return businessDetailsSharedPreferences.getString(model.FOOTER_COLOUR_HEX_CODE, "");
    }*/
}
