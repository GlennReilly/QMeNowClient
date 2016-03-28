package demo.bluemongo.com.barcodescannertest1.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.adapter.AppointmentsAdapter;
import demo.bluemongo.com.barcodescannertest1.api.AppointmentWebHelper;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentWrapper;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.presenter.AppointmentsPresenter;

/**
 * Created by glenn on 5/10/15.
 */
public class GetAppointmentsFragment extends GenericView implements RetrieveAppointmentsView {
    private OnFragmentInteractionListener mListener;
    private AppointmentsPresenter appointmentsPresenter;
    private TextView tvMessage;
    private TextView tvMessage2;
    private ProgressDialog progressDialog;
    private ListView appointmentListView;
    private List<Appointment> appointmentList;
    private AppointmentsAdapter appointmentsAdapter;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener { //These are the ways this fragment communicates with the rest of the app, via the Activity.
        void showAppointmentDetails(AppointmentWrapper appointment);
        void showMainMenu();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement GetAppointmentsFragment.OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointmentsPresenter = new AppointmentsPresenter(this);
        setPresenter(appointmentsPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_appointments,container,false);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setText(getString(R.string.getting_appointments_message));
        tvMessage2 = (TextView) view.findViewById(R.id.tvMessage2);
        appointmentListView = (ListView) view.findViewById(R.id.appointment_list);
        retrieveAppointments();

        return view;
    }

    @Override
    public void retrieveAppointments(){
        progressDialog = ProgressDialog.show(getActivity(), getString(R.string.dialogTitle),
                getString(R.string.dialogMessage), true);
        AppointmentWebHelper appointmentWebHelper = new AppointmentWebHelper(appointmentsPresenter);
        int currentBusinessId = appointmentsPresenter.getBusinessId();
        UserDetails userDetails = appointmentsPresenter.getSavedUserDetails();
        appointmentWebHelper.GetUserAppointments(currentBusinessId, userDetails);
    }




    @Override
    public void displayAppointments(final AppointmentsResponse appointmentsResponse) {
         progressDialog.dismiss();
        this.appointmentList = appointmentsResponse.getAppointmentList();
        if (this.appointmentList.size()>0){
            appointmentListView.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.GONE);
            tvMessage2.setVisibility(View.GONE);
            appointmentsAdapter = new AppointmentsAdapter(getActivity().getApplicationContext(), appointmentsResponse);
            appointmentListView.setAdapter(appointmentsAdapter);
            appointmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Appointment appointment = (Appointment)parent.getItemAtPosition(position);
                    AppointmentWrapper appointmentWrapper = new AppointmentWrapper();
                    appointmentWrapper.setAppointment(appointment);
                    appointmentWrapper.setStatusList(appointmentsResponse.getAppointmentStatusList());
                    mListener.showAppointmentDetails(appointmentWrapper);
                }
            });
        }
    }

    @Override
    public void showMessage(final String message) {
        progressDialog.dismiss();
        tvMessage2.setText(message);
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
        mListener.showMainMenu();

    }

    @Override
    public String getMessage(AppointmentsPresenter.MessageToUser messageToUser) {
        String returnMessage = "";
        if(messageToUser.equals(AppointmentsPresenter.MessageToUser.NOAPPOINTMENTSFOUND)) {
            returnMessage = getString(R.string.noAppointmentsFound);
        }

        if(messageToUser.equals(AppointmentsPresenter.MessageToUser.CUSTOMER_NOT_IN_THIS_BUSINESS)) {
            returnMessage = getString(R.string.customerDoesntExistAtThisBusiness);
        }
        return returnMessage;
    }

    @Override
    public SharedPreferences getUserDetailsSharedPreferences() {
        return getActivity().getSharedPreferences(appointmentsPresenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
