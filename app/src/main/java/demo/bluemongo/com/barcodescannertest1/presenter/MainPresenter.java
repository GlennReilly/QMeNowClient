package demo.bluemongo.com.barcodescannertest1.presenter;

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
}
