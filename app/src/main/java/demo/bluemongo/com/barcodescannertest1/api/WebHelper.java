package demo.bluemongo.com.barcodescannertest1.api;

import android.util.Log;

import java.io.IOException;

import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import demo.bluemongo.com.barcodescannertest1.model.UserDetails;
import demo.bluemongo.com.barcodescannertest1.presenter.AppointmentsPresenter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by glenn on 4/10/15.
 */
public class WebHelper {

    //private final RetrieveAppointmentsView retrieveAppointmentsView;
    private final AppointmentsPresenter appointmentsPresenter;

    public WebHelper(AppointmentsPresenter mAppointmentsPresenter) {
        appointmentsPresenter = mAppointmentsPresenter;
    }

/*    public WebHelper(RetrieveAppointmentsView retrieveAppointmentsView) {
        this.retrieveAppointmentsView = retrieveAppointmentsView;
    }*/

    public void GetUserAppointments(UserDetails userDetails){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.1.1.7:8080/") //http://10.1.1.7:8080/
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        UserAppointmentService userAppointmentService = retrofit.create(UserAppointmentService.class);
        //Call<List<Appointment>> retrofitResponse = userAppointmentService.retrieveAppointments(7);
        //Call<List<String>> retrofitResponse = userAppointmentService.doTest(7);
        Call<AppointmentsResponse> retrofitResponse = userAppointmentService.getAppointments(7);
        // Call<List<Appointment>> retrofitResponse = userAppointmentService.getAppointmentsTest(7);
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
/*                            Appointment firstAppointmentTest = appointmentsResponse.getAppointmentList().get(0);
                            String result = firstAppointmentTest.getMessageToCustomer();*/
                            //retrieveAppointmentsView.displayAppointments(result);
                            //appointmentsPresenter.appointmentResultsCallback(result);
                            appointmentsPresenter.showAppointmentsList(appointmentsResponse);
                        }
                    }
                } else {
                    int statusCode = response.code();
                    Log.e("error statusCode", String.valueOf(statusCode));
                }
            }

/*            retrofitResponse.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Response<List<String>> response, Retrofit retrofit) {
                    try {
                        if(response != null && response.errorBody() != null) {
                            Log.i("onResponse error?", response.errorBody().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<String> retrievedAppointmentList = response.body();
                    if (retrievedAppointmentList.size()>0) {
                        String firstAppointmentTest = retrievedAppointmentList.get(0);
                        String result = firstAppointmentTest;
                        retrieveAppointmentsView.displayAppointments(result);
                    }
                }*/


/*                retrofitResponse.enqueue(new Callback<List<Appointment>>() {
                    @Override
                    public void onResponse(Response<List<Appointment>> response, Retrofit retrofit) {
                        try {
                            if(response != null && response.errorBody() != null) {
                                Log.i("onResponse error?", response.errorBody().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        List<Appointment> retrievedAppointmentList = response.body();
                        if (retrievedAppointmentList.size()>0) {
                            Appointment firstAppointmentTest = retrievedAppointmentList.get(0);
                            String result = firstAppointmentTest.getMessageToCustomer();
                            retrieveAppointmentsView.displayAppointments(result);
                        }
                    }*/


            @Override
            public void onFailure(Throwable t) {
                appointmentsPresenter.setMessage("Something went wrong: " + t.getMessage());
            }
        });
    }
}
