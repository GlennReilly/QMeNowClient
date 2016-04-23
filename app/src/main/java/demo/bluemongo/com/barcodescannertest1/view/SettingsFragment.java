package demo.bluemongo.com.barcodescannertest1.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.SettingsDTO;
import demo.bluemongo.com.barcodescannertest1.presenter.SettingsPresenter;


/**
 * Created by glenn on 13/01/16.
 */
public class SettingsFragment extends GenericViewImpl implements SettingsView {
    private SettingsDTO settingsValues = new SettingsDTO();
    SettingsPresenter presenter;
    TextView etWebHelperBaseURL;
    private OnFragmentInteractionListener mListener;
    private Button btnSave;
    private LinearLayout parentLayout;

    public interface OnFragmentInteractionListener {

        void showMainMenu();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SettingsPresenter(this);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);
        btnSave = (Button) view.findViewById(R.id.btnSaveSettings);
        etWebHelperBaseURL = (EditText) view.findViewById(R.id.editTextWebHelperBaseURL);
        etWebHelperBaseURL.setText(presenter.getWebHelperBaseURL());
        parentLayout = (LinearLayout) view.findViewById(R.id.ll_main);
        setUIElementsFromSavedDetails();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
        return view;
    }

    @Override
    public void setUIElementsFromSavedDetails(){


        if(presenter.getButtonBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getButtonBackgroundColour());
            btnSave.setBackgroundColor(colour);
        }

        if(presenter.getBackgroundBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getBackgroundBackgroundColour());
            parentLayout.setBackgroundColor(colour);
        }

        if(presenter.getHeaderBackgroundColour() !=  "") {
            int color = Color.parseColor(presenter.getHeaderBackgroundColour());
            presenter.setGenericUIStuff();
        }
    }


    private void saveSettings() {
        settingsValues = getSettingsValues();
        presenter.saveSettings(settingsValues);
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.settings_saved_successfully), Toast.LENGTH_SHORT).show();
        mListener.showMainMenu();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public SettingsDTO getSettingsValues() {
        settingsValues.setWebHelperBaseURL(etWebHelperBaseURL.getText().toString());
        return settingsValues;
    }

    @Override
    public SharedPreferences getUserDetailsSharedPreferences() {
        return getActivity().getSharedPreferences(presenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
    }
}
