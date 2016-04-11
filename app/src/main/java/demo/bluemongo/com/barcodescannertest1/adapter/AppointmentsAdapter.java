package demo.bluemongo.com.barcodescannertest1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;

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
        TextView tvAppointmentTime = (TextView) viewRow.findViewById(R.id.tvAppointmentTime);
        TextView tvAppointmentRefNum = (TextView) viewRow.findViewById(R.id.tvAppointmentRefNum);
        TextView tvAppointmentStatus = (TextView) viewRow.findViewById(R.id.tvAppointmentStatus);
        tvAppointmentTime.setText(nextAppointment.getStrAppointmentTime());
        tvAppointmentRefNum.setText(nextAppointment.getAppointmentTypePrefix() + nextAppointment.getId());
        tvAppointmentStatus.setText(nextAppointment.getStatusName());

        return viewRow;
    }


}
