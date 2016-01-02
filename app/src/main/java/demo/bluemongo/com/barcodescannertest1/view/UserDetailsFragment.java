package demo.bluemongo.com.barcodescannertest1.view;

import android.app.Activity;
import android.app.Fragment;
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
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.presenter.UserDetailsPresenter;
import demo.bluemongo.com.barcodescannertest1.view.UserDetailsView;

/**
 * Created by glenn on 27/09/15.
 */
public class UserDetailsFragment extends Fragment implements UserDetailsView {
    private OnFragmentInteractionListener mListener;
    private UserDetailsPresenter mUserDetailspresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserDetailspresenter = new UserDetailsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        final EditText etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) view.findViewById(R.id.etSurname);
        final EditText etCustomerId = (EditText) view.findViewById(R.id.etCustomerId);
        Button btnSubmitUserDetails = (Button) view.findViewById(R.id.btnSubmitUserDetails);

        UserDetails userDetails = mUserDetailspresenter.getSavedUserDetails();
        etFirstName.setText(userDetails.getFirstName());
        etLastName.setText(userDetails.getLastName());
        etCustomerId.setText(userDetails.getCustomerId());


        btnSubmitUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDetails(etFirstName, etLastName, etCustomerId);
            }
        });

        return view;
    }

    @Override
    public void saveUserDetails(EditText etFirstName, EditText etLastName, EditText etCustomerId) {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String customerId = etCustomerId.getText().toString();
        mUserDetailspresenter.saveUserDetails(firstName, lastName, customerId);
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.user_saved_successfully), Toast.LENGTH_SHORT).show();
        mListener.showMainMenu();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return getActivity().getSharedPreferences(mUserDetailspresenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
    }

    public interface OnFragmentInteractionListener {
        void showMainMenu();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
