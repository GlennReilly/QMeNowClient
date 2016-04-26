package demo.bluemongo.com.QMeNowClient.view;

import io.realm.Realm;

/**
 * Created by glenn on 23/04/16.
 */
public interface GenericView {
    void setUIElementsFromSavedDetails();
    Realm getRealm();
}
