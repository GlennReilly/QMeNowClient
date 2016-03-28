package demo.bluemongo.com.barcodescannertest1.view;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentStatus;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentWrapper;

public class AppointmentDetailsFragment extends GenericView implements AppointmentDetailsView {
    public static final String APPOINTMENT_WRAPPER_KEY = "APPOINTMENT_WRAPPER_KEY";

    private OnFragmentInteractionListener mListener;
    private AppointmentsDetailsPresenter presenter;
    private Button btnCheckin;
    private LinearLayout parentLayout;


    public AppointmentDetailsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(AppointmentWrapper appointmentWrapper) {
        AppointmentDetailsFragment appointmentDetailsFragment = new AppointmentDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(APPOINTMENT_WRAPPER_KEY, appointmentWrapper);
        appointmentDetailsFragment.setArguments(bundle);
        return appointmentDetailsFragment;
    }


    public interface OnFragmentInteractionListener {

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        }
        catch(ClassCastException cex){
            throw new ClassCastException(context.toString()
                    + " must implement AppointmentDetailsFragment.OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setView(this);
        presenter = new AppointmentsDetailsPresenter(this);
        setPresenter(presenter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AppointmentWrapper appointmentWrapper = this.getArguments().getParcelable(APPOINTMENT_WRAPPER_KEY);
        final Appointment appointment = appointmentWrapper.getAppointment();
        final List<AppointmentStatus> appointmentStatusList = appointmentWrapper.getStatusList();

        View view = inflater.inflate(R.layout.fragment_appointment_details, container, false);
        btnCheckin = (Button) view.findViewById(R.id.btnCheckin);
        parentLayout = (LinearLayout) view.findViewById(R.id.ll_main);
        Iterator<AppointmentStatus> statusIterator = appointmentStatusList.iterator();
        boolean buttonSetToVisible =false;
        while(statusIterator.hasNext() && !buttonSetToVisible){
            AppointmentStatus appointmentStatus = statusIterator.next();

            if ( StringUtils.isEmpty(appointment.getStrCheckInDateTime()) ) {
                if (appointmentStatus.isCustomerInitiated() && (appointmentStatus.getName().equals(appointment.getStatusName()))){
                    btnCheckin.setVisibility(View.VISIBLE);
                    buttonSetToVisible = true;
                    btnCheckin.setText(getString(R.string.check_in));

                    btnCheckin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkInButtonClicked(appointment, appointmentStatusList);
                        }
                    });
                }
                else {
                    btnCheckin.setVisibility(View.INVISIBLE);
                }
            }
            else{
                btnCheckin.setVisibility(View.VISIBLE);
                buttonSetToVisible = true;
                btnCheckin.setEnabled(false);
                btnCheckin.setText(getString(R.string.appointment_already_checked_in) + " " + appointment.getStrCheckInDateTime());
            }
        }

        TextView tvMessageToCustomer = (TextView) view.findViewById(R.id.messageToCustomer);
        TextView tvStrAppointmentDate = (TextView) view.findViewById(R.id.strAppointmentDate);
        TextView tvStrAppointmentTime = (TextView) view.findViewById(R.id.strAppointmentTime);
        TextView tvLocationName = (TextView) view.findViewById(R.id.locationName);
        TextView tvStatusName = (TextView) view.findViewById(R.id.statusName);
        TextView tvAppointmentTypeName = (TextView) view.findViewById(R.id.appointmentTypeName);
        TextView tvAppointmentTypePrefix = (TextView) view.findViewById(R.id.appointmentTypePrefix);

        tvMessageToCustomer.setText(appointment.getMessageToCustomer());
        tvStrAppointmentDate.setText(appointment.getStrAppointmentDate());
        tvStrAppointmentTime.setText(appointment.getStrAppointmentTime());
        tvLocationName.setText(appointment.getLocationName());

        ImageView ivLocationCircle = (ImageView) view.findViewById(R.id.locationCircle);
        Drawable drawableLocationCircle = ivLocationCircle.getDrawable();
        if(appointment.getLocationHexCode() != null){
            drawableLocationCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getLocationHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        tvStatusName.setText(appointment.getStatusName());

        ImageView ivStatusCircle = (ImageView) view.findViewById(R.id.statusCircle);
        Drawable drawableStatusCircle = ivStatusCircle.getDrawable();
        if(appointment.getStatusHexCode() != null){
            drawableStatusCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getStatusHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        tvAppointmentTypeName.setText(appointment.getAppointmentTypeName());
        tvAppointmentTypePrefix.setText(appointment.getAppointmentTypePrefix());

        ImageView ivAppointmentTypeCircle = (ImageView) view.findViewById(R.id.appointmentTypeCircle);
        Drawable drawableAppointmentTypeCircle = ivAppointmentTypeCircle.getDrawable();
        if(appointment.getAppTypeHexCode() != null){
            drawableAppointmentTypeCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getAppTypeHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        return view;
    }

    @Override
    public void setUIElementsFromSavedDetails(){


        if(presenter.getButtonBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getButtonBackgroundColour());
            btnCheckin.setBackgroundColor(colour);
        }

        if(presenter.getBackgroundBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getBackgroundBackgroundColour());
            parentLayout.setBackgroundColor(colour);
        }

        if(presenter.getHeaderBackgroundColour() !=  "") {
/*            int color = Color.parseColor(presenter.getHeaderBackgroundColour());
            String logoFileName = presenter.getLogoFileName();*/
            presenter.setGenericActionBarStuff();
        }
    }

    private void checkInButtonClicked(Appointment appointment, List<AppointmentStatus> appointmentStatusList) {
        checkInAppointment(appointment, appointmentStatusList);

    }

    @Override
    public void checkInAppointment(Appointment appointment, List<AppointmentStatus> appointmentStatusList) {
        /*
        send appointment id, current appointmentStatus, customerId?, to webClient,
         refresh appointment results?
         get confirmation boolean?
         */
        presenter.checkInAppointment(appointment, appointmentStatusList);
    }



}
