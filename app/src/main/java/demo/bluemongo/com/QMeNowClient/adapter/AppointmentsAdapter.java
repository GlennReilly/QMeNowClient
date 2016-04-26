package demo.bluemongo.com.QMeNowClient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import demo.bluemongo.com.QMeNowClient.R;
import demo.bluemongo.com.QMeNowClient.model.Appointment;
import demo.bluemongo.com.QMeNowClient.model.AppointmentsResponse;

/**
 * Created by glenn on 19/12/15.
 */
public class AppointmentsAdapter extends ArrayAdapter<Appointment> {
    private final Context context;
    //private final List<Appointment> appointmentList;
    private final AppointmentsResponse appointmentResponse;

    public AppointmentsAdapter(Context context, AppointmentsResponse appointmentsResponse) {
        super(context, -1, appointmentsResponse.getAppointmentList());
        this.context = context;
        this.appointmentResponse = appointmentsResponse;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Appointment nextAppointment = appointmentResponse.getAppointmentList().get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewRow = inflater.inflate(R.layout.list_item_appointment, parent, false);
        TextView tvAppointmentRefNum = (TextView) viewRow.findViewById(R.id.tvAppointmentRefNum);
        TextView tvAppointmentTime = (TextView) viewRow.findViewById(R.id.tvAppointmentTime);

        TextView tvAppointmentLocationLabel = (TextView) viewRow.findViewById(R.id.tvAppointmentLocationLabel);
        TextView tvAppointmentLocation = (TextView) viewRow.findViewById(R.id.tvAppointmentLocation);

        TextView tvAppointmentStatusLabel = (TextView) viewRow.findViewById(R.id.tvAppointmentStatusLabel);
        TextView tvAppointmentStatus = (TextView) viewRow.findViewById(R.id.tvAppointmentStatus);


        tvAppointmentRefNum.setText(nextAppointment.getAppointmentTypePrefix() + nextAppointment.getId());
        tvAppointmentTime.setText(nextAppointment.getStrAppointmentTime());

        tvAppointmentLocationLabel.setText(context.getString(R.string.label_location));
        tvAppointmentLocation.setText(nextAppointment.getLocationName());
        tvAppointmentStatusLabel.setText(context.getString(R.string.label_status));
        tvAppointmentStatus.setText(nextAppointment.getStatusName());

        return viewRow;
    }


}
