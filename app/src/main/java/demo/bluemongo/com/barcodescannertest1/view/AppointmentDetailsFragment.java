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
import android.widget.ImageView;
import android.widget.TextView;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;

public class AppointmentDetailsFragment extends Fragment implements AppointmentDetailsView {
    public static final String APPOINTMENT_KEY = "APPOINTMENT_KEY";
    //private Appointment appointment = null;
    private OnFragmentInteractionListener mListener;
    private AppointmentsDetailsPresenter mAppointmentsDetailsPresenter;


    public AppointmentDetailsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Appointment appointment) {
        AppointmentDetailsFragment appointmentDetailsFragment = new AppointmentDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(APPOINTMENT_KEY, appointment);
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
        mAppointmentsDetailsPresenter = new AppointmentsDetailsPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Appointment appointment = this.getArguments().getParcelable(APPOINTMENT_KEY);

        View view = inflater.inflate(R.layout.fragment_appointment_details, container, false);
        TextView tvMessageToCustomer = (TextView) view.findViewById(R.id.messageToCustomer);
        TextView tvStrAppointmentDate = (TextView) view.findViewById(R.id.strAppointmentDate);
        TextView tvStrAppointmentTime = (TextView) view.findViewById(R.id.strAppointmentTime);
        TextView tvLocationName = (TextView) view.findViewById(R.id.locationName);
        TextView tvStatusName = (TextView) view.findViewById(R.id.statusName);
        TextView tvIsComplete = (TextView) view.findViewById(R.id.isComplete);
        TextView tvAppointmentTypeName = (TextView) view.findViewById(R.id.appointmentTypeName);
        TextView tvAppointmentTypePrefix = (TextView) view.findViewById(R.id.appointmentTypePrefix);
        TextView tvStatusHexCode = (TextView) view.findViewById(R.id.statusHexCode);
        TextView tvAppTypeHexCode = (TextView) view.findViewById(R.id.appTypeHexCode);
        TextView tvLocationHexCode = (TextView) view.findViewById(R.id.messageToCustomer);

        tvMessageToCustomer.setText(appointment.getMessageToCustomer());
        tvStrAppointmentDate.setText(appointment.getStrAppointmentDate());
        tvStrAppointmentTime.setText(appointment.getStrAppointmentTime());

        tvLocationName.setText(appointment.getLocationName());
        tvLocationHexCode.setText(appointment.getLocationHexCode());

        ImageView ivLocationCircle = (ImageView) view.findViewById(R.id.locationCircle);
        Drawable drawableLocationCircle = ivLocationCircle.getDrawable();
        if(appointment.getLocationHexCode() != null){
            tvLocationName.setBackgroundColor(Color.parseColor(appointment.getLocationHexCode()));
            drawableLocationCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getLocationHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        tvStatusName.setText(appointment.getStatusName());
        tvStatusHexCode.setText(appointment.getStatusHexCode());

        ImageView ivStatusCircle = (ImageView) view.findViewById(R.id.statusCircle);
        Drawable drawableStatusCircle = ivStatusCircle.getDrawable();
        if(appointment.getStatusHexCode() != null){
            tvStatusName.setBackgroundColor(Color.parseColor(appointment.getStatusHexCode()));
            drawableStatusCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getStatusHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        tvIsComplete.setText(String.valueOf(appointment.isComplete()));
        tvAppointmentTypeName.setText(appointment.getAppointmentTypeName());
        tvAppointmentTypePrefix.setText(appointment.getAppointmentTypePrefix());
        tvAppTypeHexCode.setText(appointment.getAppTypeHexCode());

        ImageView ivAppointmentTypeCircle = (ImageView) view.findViewById(R.id.appointmentTypeCircle);
        Drawable drawableAppointmentTypeCircle = ivAppointmentTypeCircle.getDrawable();
        if(appointment.getAppTypeHexCode() != null){
            tvAppointmentTypeName.setBackgroundColor(Color.parseColor(appointment.getAppTypeHexCode()));
            drawableAppointmentTypeCircle.setColorFilter(new PorterDuffColorFilter(Color.parseColor(appointment.getAppTypeHexCode()), PorterDuff.Mode.MULTIPLY));
        }

        return view;
    }


}
