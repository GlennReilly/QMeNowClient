package demo.bluemongo.com.barcodescannertest1.view;

import io.realm.Realm;

/**
 * Created by glenn on 26/09/15.
 */
public interface MainView {
    void setUIElementsFromSavedDetails();
    Realm getRealm();

}
