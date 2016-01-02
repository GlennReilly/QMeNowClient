package demo.bluemongo.com.barcodescannertest1.view;

import android.app.Fragment;
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

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.adapter.AppointmentsAdapter;
import demo.bluemongo.com.barcodescannertest1.api.WebHelper;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.presenter.AppointmentsPresenter;

/**
 * Created by glenn on 5/10/15.
 */
public class GetAppointmentsFragment extends Fragment implements RetrieveAppointmentsView {
    private OnFragmentInteractionListener mListener;
    private AppointmentsPresenter mAppointmentsPresenter;
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
        void showAppointmentDetails(Appointment appointment);
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
        mAppointmentsPresenter = new AppointmentsPresenter(this);
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
        WebHelper webHelper = new WebHelper(mAppointmentsPresenter);
        UserDetails userDetails = mAppointmentsPresenter.getSavedUserDetails();
        progressDialog = ProgressDialog.show(getActivity(), getString(R.string.dialogTitle),
                getString(R.string.dialogMessage), true);
        webHelper.GetUserAppointments(userDetails);
    }


    @Override
    public void displayAppointments(List<Appointment> appointmentsList) {
         progressDialog.dismiss();
        this.appointmentList = appointmentsList;
        if (appointmentsList.size()>0){
            appointmentListView.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.GONE);
            tvMessage2.setVisibility(View.GONE);
            appointmentsAdapter = new AppointmentsAdapter(getActivity().getApplicationContext(), appointmentsList);
            appointmentListView.setAdapter(appointmentsAdapter);
            appointmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Appointment appointment = (Appointment)parent.getItemAtPosition(position);
                    //Toast.makeText(getActivity().getApplicationContext(), appointment.getStrAppointmentTime(),Toast.LENGTH_SHORT).show();
                    mListener.showAppointmentDetails(appointment);
                }
            });
        }
    }

    @Override
    public void showMessage(String message) {
        progressDialog.dismiss();
        tvMessage2.setText(message);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return getActivity().getSharedPreferences(mAppointmentsPresenter.getUserDetailsPrefsString(), Context.MODE_PRIVATE);
    }





    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
