package demo.bluemongo.com.barcodescannertest1.view;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import demo.bluemongo.com.barcodescannertest1.presenter.GenericPresenter;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by glenn on 16/01/16.
 */
public class GenericView extends Fragment {
    public static final String RETRIEVE_FROM_CACHE = "RETRIEVE_FROM_CACHE";
    private GenericView view;
    private GenericPresenter presenter;
    private RealmConfiguration realmConfig;
    private Realm realm;


    public SharedPreferences getUserDetailsSharedPreferences() {
        Context cxt = getActivity();
        SharedPreferences sharedPreferences = cxt.getSharedPreferences(presenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
        return sharedPreferences;
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


    public void setupRealm() {
        // Create the Realm configuration
        realmConfig = new RealmConfiguration.Builder(getActivity()).build();
        // Open the Realm for the UI thread.
        realm = Realm.getInstance(realmConfig);
    }


    public Realm getRealm(){
        if (realm == null) {
            setupRealm();
            Log.i("GetAppointmentsFragment", "recreating realm");
        }
        return realm;
    }
}
