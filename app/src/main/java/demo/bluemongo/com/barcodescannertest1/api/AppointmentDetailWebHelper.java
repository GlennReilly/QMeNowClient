package demo.bluemongo.com.barcodescannertest1.api;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentStatus;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
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


    public void GetUserAppointments(UserDetails userDetails){
        String webHelperBaseURL = appointmentsDetailsPresenter.getWebHelperBaseURL();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(webHelperBaseURL) //http://10.1.1.7:8080/
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        UserAppointmentService userAppointmentService = retrofit.create(UserAppointmentService.class);

        Call<AppointmentsResponse> retrofitResponse = userAppointmentService.getAppointments(7);
        Log.i("test tag", "test msg");

        retrofitResponse.enqueue(new Callback<AppointmentsResponse>() {
            @Override
            public void onResponse(Response<AppointmentsResponse> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    try {
                        if(response != null && response.errorBody() != null) {
                            Log.i("onResponse error?", response.errorBody().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (response.body() != null) {
                        AppointmentsResponse appointmentsResponse = response.body();

                        if (appointmentsResponse.getAppointmentList().size() > 0) {
                            //appointmentsPresenter.showAppointmentsList(appointmentsResponse);
                        }
                    }
                } else {
                    int statusCode = response.code();
                    Log.e("error statusCode", String.valueOf(statusCode));
                }
            }



            @Override
            public void onFailure(Throwable t) {
                appointmentsDetailsPresenter.setMessage("Something went wrong: " + t.getMessage());
            }
        });
    }

    public void progressAppointmentStatus(Appointment appointment, List<AppointmentStatus> appointmentStatusList) {

    }
}
