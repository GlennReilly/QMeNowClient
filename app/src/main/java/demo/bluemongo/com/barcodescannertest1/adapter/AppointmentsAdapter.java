package demo.bluemongo.com.barcodescannertest1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.Appointment;

/**
 * Created by glenn on 19/12/15.
 */
public class AppointmentsAdapter extends ArrayAdapter<Appointment> {
    private final Context context;
    private final List<Appointment> appointmentList;

    public AppointmentsAdapter(Context context, List<Appointment> appointmentList) {
        super(context, -1, appointmentList);
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Appointment nextAppointment = appointmentList.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewRow = inflater.inflate(R.layout.list_item_appointment, parent, false);

        TextView tvAppointmentTime = (TextView) viewRow.findViewById(R.id.tvAppointmentTime);
        TextView tvMessageToCustomer = (TextView) viewRow.findViewById(R.id.tvMessageToCustomer);
        tvAppointmentTime.setText(nextAppointment.getStrAppointmentTime());
        tvMessageToCustomer.setText(nextAppointment.getMessageToCustomer());

        return viewRow;
    }


}
