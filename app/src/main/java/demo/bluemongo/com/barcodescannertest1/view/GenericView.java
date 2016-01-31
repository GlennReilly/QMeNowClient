package demo.bluemongo.com.barcodescannertest1.view;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;

import demo.bluemongo.com.barcodescannertest1.presenter.GenericPresenter;

/**
 * Created by glenn on 16/01/16.
 */
public class GenericView extends Fragment {
    private GenericView view;
    private GenericPresenter presenter;


    public SharedPreferences getUserDetailsSharedPreferences() {
        Context cxt = getActivity();
        return cxt.getSharedPreferences(presenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
    }

    public SharedPreferences getBusinessDetailsSharedPreferences() {
        Context cxt = getActivity();
        return cxt.getSharedPreferences(presenter.getBusinessDetailsPrefsString(), Context.MODE_PRIVATE);
    }

    public SharedPreferences getAppSettingsSharedPreferences() {
        Context cxt = getActivity();
        return cxt.getSharedPreferences(presenter.getAppSettingsPrefsString(), Context.MODE_PRIVATE);
    }

    public void setView(GenericView view) {
        this.view = view;
    }

    public void setPresenter(GenericPresenter presenter) {
        this.presenter = presenter;
    }

    public GenericPresenter getPresenter() {
        return presenter;
    }
}
