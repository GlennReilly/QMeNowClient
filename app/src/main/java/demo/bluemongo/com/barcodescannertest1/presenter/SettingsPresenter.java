package demo.bluemongo.com.barcodescannertest1.presenter;

import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.SettingsDTO;
import demo.bluemongo.com.barcodescannertest1.view.SettingsView;

/**
 * Created by glenn on 14/01/16.
 */
public class SettingsPresenter {
    private final SettingsView view;
    private final QMeNowModel model = new QMeNowModel();

    public String getUserDetailsPrefsString() {
        return model.SETTINGS__PREFERENCES;
    }

    public SettingsPresenter(SettingsView settingsView) {
        this.view = settingsView;
    }

    public void saveSettings(SettingsDTO settingsValues) {
        QMeNowModel model = new QMeNowModel();
        model.saveWebHelperBaseURL(settingsValues.getWebHelperBaseURL(), view.getSharedPreferences());
    }

    public String getWebHelperBaseURL() {
        return model.getWebHelperBaseURL(view.getSharedPreferences());
    }
}
