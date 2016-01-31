package demo.bluemongo.com.barcodescannertest1.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.presenter.UserDetailsPresenter;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetailsFragment extends GenericView implements UserDetailsView {
    private OnFragmentInteractionListener mListener;
    private UserDetailsPresenter mUserDetailspresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserDetailspresenter = new UserDetailsPresenter(this);
        super.setPresenter(mUserDetailspresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        final EditText etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) view.findViewById(R.id.etSurname);
        final EditText etCustomerId = (EditText) view.findViewById(R.id.etCustomerId);
        Button btnSubmitUserDetails = (Button) view.findViewById(R.id.btnSubmitUserDetails);
        Button btnScanUserDetails = (Button) view.findViewById(R.id.btnScanUserDetails);

        if(getArguments() != null){
            Bundle bundle = getArguments();
            String firstname = bundle.getString(QMeNowModel.FIRSTNAME);
            String lastname = bundle.getString(QMeNowModel.LASTNAME);
            int customerId = bundle.getInt(QMeNowModel.CUSTOMERID);
            etFirstName.setText(String.valueOf(firstname));
            etLastName.setText(String.valueOf(lastname));
            etCustomerId.setText(String.valueOf(customerId));
            saveUserDetails(etFirstName, etLastName, etCustomerId);
        }else{
            UserDetails userDetails = mUserDetailspresenter.getSavedUserDetails();
            etFirstName.setText(String.valueOf(userDetails.getFirstName()));
            etLastName.setText(String.valueOf(userDetails.getLastName()));
            etCustomerId.setText(String.valueOf(userDetails.getCustomerId()));
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

        return view;
    }

    @Override
    public void  saveUserDetails(EditText etFirstName, EditText etLastName, EditText etCustomerId) {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        Integer customerId = Integer.parseInt(etCustomerId.getText().toString());
        mUserDetailspresenter.saveUserDetails(firstName, lastName, customerId);
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.user_saved_successfully), Toast.LENGTH_SHORT).show();
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
        return getActivity().getSharedPreferences(mUserDetailspresenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
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
