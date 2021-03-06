package demo.bluemongo.com.QMeNowClient.view;


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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import demo.bluemongo.com.QMeNowClient.R;
import demo.bluemongo.com.QMeNowClient.model.Appointment;
import demo.bluemongo.com.QMeNowClient.model.AppointmentStatus;
import demo.bluemongo.com.QMeNowClient.model.AppointmentWrapper;
import demo.bluemongo.com.QMeNowClient.presenter.AppointmentsDetailsPresenter;
import demo.bluemongo.com.QMeNowClient.utils.InputHelper;

public class AppointmentDetailsFragment extends GenericViewImpl implements AppointmentDetailsView {
    public static final String APPOINTMENT_WRAPPER_KEY = "APPOINTMENT_WRAPPER_KEY";

    private OnFragmentInteractionListener mListener;
    private AppointmentsDetailsPresenter presenter;
    private Button btnCheckin;
    private LinearLayout parentLayout;
    private LinearLayout layout_refnum;
    private LinearLayout layout_message;
    private LinearLayout layout_date_time;
    private RelativeLayout layout_location;
    private RelativeLayout layout_status;
    private RelativeLayout layout_appointment_type;


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

            btnCheckin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkInButtonClicked(btnCheckin, appointment, appointmentStatusList);
                }
            });

            if (appointmentStatus.isCustomerInitiated() && (appointmentStatus.getName().equals(appointment.getStatusName()))){
                btnCheckin.setVisibility(View.VISIBLE);
                buttonSetToVisible = true;

                if ( StringUtils.isEmpty(appointment.getStrCheckInDateTime()) ) {
                    btnCheckin.setText(getString(R.string.check_in));
                }
                else{
                    try{
                        Date checkinDate = InputHelper.getDateFromISO8601String(appointment.getStrCheckInDateTime());
                        final String pattern = "dd/MM/yyyy HH:mm";
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        btnCheckin.setText(getString(R.string.appointment_checked_in_at) + " " + sdf.format(checkinDate));
                    }catch (Exception ex) {
                        btnCheckin.setText(getString(R.string.appointment_checked_in_at) + " " + appointment.getStrCheckInDateTime());
                    }
                }

            }
            else {
                btnCheckin.setVisibility(View.INVISIBLE);
            }
        }

        TextView tvAppointmentRefNum = (TextView) view.findViewById(R.id.tvAppointmentRefNum);
        //TextView tvMessageToCustomer = (TextView) view.findViewById(R.id.messageToCustomer);
        TextView tvStrAppointmentDate = (TextView) view.findViewById(R.id.strAppointmentDate);
        TextView tvStrAppointmentTime = (TextView) view.findViewById(R.id.strAppointmentTime);
        TextView tvLocationName = (TextView) view.findViewById(R.id.locationName);
        TextView tvStatusName = (TextView) view.findViewById(R.id.statusName);
        TextView tvAppointmentTypeName = (TextView) view.findViewById(R.id.appointmentTypeName);


        //tvMessageToCustomer.setText(appointment.getMessageToCustomer());
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
        tvAppointmentRefNum.setText(appointment.getAppointmentTypePrefix() + appointment.getId());

        ImageView ivAppointmentTypeCircle = (ImageView) view.findViewById(R.id.appointmentTypeCircle);
        Drawable drawableAppointmentTypeCircle = ivAppointmentTypeCircle.getDrawable();
        if(appointment.getAppTypeHexCode() != null){
            drawableAppointmentTypeCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getAppTypeHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        layout_refnum = (LinearLayout) view.findViewById(R.id.layout_refnum);
        //layout_message = (LinearLayout) view.findViewById(R.id.layout_message);
        layout_date_time = (LinearLayout) view.findViewById(R.id.layout_date_time);
        layout_location = (RelativeLayout) view.findViewById(R.id.layout_location);
        layout_status = (RelativeLayout) view.findViewById(R.id.layout_status);
        layout_appointment_type = (RelativeLayout) view.findViewById(R.id.layout_appointment_type);

        setUIElementsFromSavedDetails();
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
            float[] hsvArr = new float[3];
            Color.colorToHSV(colour, hsvArr);
            float brightness = hsvArr[2];

            float difference = 1 - brightness;
            hsvArr[2] += difference;
            //int lighterShade = Color.HSVToColor(hsvArr); //brighter

            float difference2 = brightness/8;
            hsvArr[2] -= difference2;
            int darkerShade = Color.HSVToColor(hsvArr); //darker

            //layout_refnum.setBackgroundColor(darkerShade);
            layout_date_time.setBackgroundColor(darkerShade);
            layout_status.setBackgroundColor(darkerShade);
            parentLayout.setBackgroundColor(colour);
        }

        if(presenter.getHeaderBackgroundColour() !=  "") {
/*            int color = Color.parseColor(presenter.getHeaderBackgroundColour());
            String logoFileName = presenter.getLogoFileName();*/
            presenter.setGenericUIStuff();
        }
    }

    @Override
    public void notifyCheckinResult(boolean result) {
       if(result){
           btnCheckin.setText(getString(R.string.check_in_successful));
       }
        else{
           btnCheckin.setText(getString(R.string.check_in_failed));
       }
    }

    private void checkInButtonClicked(Button btnCheckin, Appointment appointment, List<AppointmentStatus> appointmentStatusList) {
        if ( StringUtils.isEmpty(appointment.getStrCheckInDateTime()) ) {
            btnCheckin.setText(getString(R.string.btn_check_in_progress));
            checkInAppointment(appointment, appointmentStatusList);
        }else {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.appointment_already_checked_in), Toast.LENGTH_LONG).show();
        }
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
