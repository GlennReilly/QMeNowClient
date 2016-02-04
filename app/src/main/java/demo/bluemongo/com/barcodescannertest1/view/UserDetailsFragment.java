package demo.bluemongo.com.barcodescannertest1.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.presenter.UserDetailsPresenter;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetailsFragment extends GenericView implements UserDetailsView {
    private OnFragmentInteractionListener mListener;
    private UserDetailsPresenter presenter;
    private Button btnSubmitUserDetails;
    private Button btnScanUserDetails;
    private Button btnRemoveUserDetails;
    private LinearLayout parentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserDetailsPresenter(this);
        super.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        final EditText etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) view.findViewById(R.id.etSurname);
        final EditText etCustomerId = (EditText) view.findViewById(R.id.etCustomerId);
        btnSubmitUserDetails = (Button) view.findViewById(R.id.btnSubmitUserDetails);
        btnScanUserDetails = (Button) view.findViewById(R.id.btnScanUserDetails);
        btnRemoveUserDetails = (Button) view.findViewById(R.id.btnRemoveUserDetails);
        parentLayout = (LinearLayout) view.findViewById(R.id.ll_main);

        if(getArguments() != null){
            Bundle bundle = getArguments();
            String firstname = bundle.getString(getPresenter().getModel().FIRSTNAME);
            String lastname = bundle.getString(getPresenter().getModel().LASTNAME);
            int customerId = bundle.getInt(getPresenter().getModel().CUSTOMERID);
            etFirstName.setText(String.valueOf(firstname));
            etLastName.setText(String.valueOf(lastname));
            etCustomerId.setText(String.valueOf(customerId));
            saveUserDetails(etFirstName, etLastName, etCustomerId);
        }else{
            UserDetails userDetails = presenter.getSavedUserDetails();
            etFirstName.setText(String.valueOf(userDetails.getFirstName()));
            etLastName.setText(String.valueOf(userDetails.getLastName()));

            if(userDetails.getCustomerId().equals(null) || userDetails.getCustomerId().equals(0)){
                etCustomerId.setText("");
            }
            else {
                etCustomerId.setText(String.valueOf(userDetails.getCustomerId()));
            }
        }


        btnSubmitUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDetails(etFirstName, etLastName, etCustomerId);
                mListener.showMainMenu();
            }
        });

        btnScanUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openCameraPreview(CameraPreviewView.BarcodeType.CUSTOMER);
            }
        });

        btnRemoveUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUserDetails();
                mListener.showMainMenu();
            }
        });

        setUIElementsFromSavedDetails();
        return view;
    }

    @Override
    public void setUIElementsFromSavedDetails(){


        if(presenter.getButtonBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getButtonBackgroundColour());
            btnSubmitUserDetails.setBackgroundColor(colour);
            btnScanUserDetails.setBackgroundColor(colour);
            btnRemoveUserDetails.setBackgroundColor(colour);
        }

        if(presenter.getBackgroundBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getBackgroundBackgroundColour());
            parentLayout.setBackgroundColor(colour);
        }

        if(presenter.getHeaderBackgroundColour() !=  "") {
            int color = Color.parseColor(presenter.getHeaderBackgroundColour());

            ActionBar actionBar = getActivity().getActionBar();
            actionBar.setTitle(presenter.getBusinessName());
            actionBar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    @Override
    public void  saveUserDetails(EditText etFirstName, EditText etLastName, EditText etCustomerId) {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        Integer customerId = Integer.parseInt(etCustomerId.getText().toString());
        presenter.saveUserDetails(firstName, lastName, customerId);
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.user_saved_successfully), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeUserDetails(){
        presenter.removeUserDetails();
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.user_removed_successfully), Toast.LENGTH_SHORT).show();
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
    public SharedPreferences getUserDetailsSharedPreferences() {
        return getActivity().getSharedPreferences(presenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
    }

    public interface OnFragmentInteractionListener {
        void showMainMenu();
        void openCameraPreview(CameraPreviewView.BarcodeType customerBarcodeType);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
