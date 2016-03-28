package demo.bluemongo.com.barcodescannertest1.api;

import android.util.Log;

import java.util.Calendar;
import java.util.List;

import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentCheckInDTO;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentStatus;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.view.AppointmentsDetailsPresenter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by glenn on 4/10/15.
 */
public class AppointmentDetailWebHelper {


    private AppointmentsDetailsPresenter appointmentsDetailsPresenter;

    public AppointmentDetailWebHelper(AppointmentsDetailsPresenter appointmentsDetailsPresenter) {
            this.appointmentsDetailsPresenter = appointmentsDetailsPresenter;
    }

    public void checkInAppointment(Appointment appointment, List<AppointmentStatus> appointmentStatusList) {
        String webHelperBaseURL = appointmentsDetailsPresenter.getWebHelperBaseURL(); //http://10.1.1.7:8080/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(webHelperBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserAppointmentService userAppointmentService = retrofit.create(UserAppointmentService.class);
        AppointmentCheckInDTO appointmentCheckInDTO = new AppointmentCheckInDTO();
        appointmentCheckInDTO.setAppointmentId(appointment.getId());
        appointmentCheckInDTO.setCheckInDateTime(Calendar.getInstance().getTime());
        appointmentCheckInDTO.setCustomerId(appointment.getCustomerId());
        Call<AppointmentsResponse> retrofitResponse = userAppointmentService.checkInAppointment(appointment.getId(), appointmentCheckInDTO);

        retrofitResponse.enqueue(new Callback<AppointmentsResponse>() {
            @Override
            public void onResponse(Response<AppointmentsResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.i("checkIn successful",response.body().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("checkIn failed", "checkIn failed");
            }
        });
    }

}
