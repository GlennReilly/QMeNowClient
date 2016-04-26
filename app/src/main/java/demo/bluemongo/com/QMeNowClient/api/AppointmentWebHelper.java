package demo.bluemongo.com.QMeNowClient.api;

import android.util.Log;

import java.io.IOException;

import demo.bluemongo.com.QMeNowClient.model.AppointmentsResponse;
import demo.bluemongo.com.QMeNowClient.model.QMeNowModel;
import demo.bluemongo.com.QMeNowClient.model.UserDetails;
import demo.bluemongo.com.QMeNowClient.presenter.AppointmentsPresenter;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by glenn on 4/10/15.
 */
public class AppointmentWebHelper {
    enum Errors{CUSTOMER_NOT_IN_THIS_BUSINESS}

    private AppointmentsPresenter appointmentsPresenter;


    public AppointmentWebHelper(AppointmentsPresenter appointmentsPresenter) {
        this.appointmentsPresenter = appointmentsPresenter;
    }


    public void GetUserAppointments(int currentBusinessId, UserDetails userDetails){
        String webHelperBaseURL = appointmentsPresenter.getWebHelperBaseURL(); //http://10.1.1.7:8080/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(webHelperBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        UserAppointmentService userAppointmentService = retrofit.create(UserAppointmentService.class);
        Call<AppointmentsResponse> retrofitResponse = userAppointmentService.getAppointments(currentBusinessId, userDetails.getCustomerId());

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

                        if (appointmentsResponse.getErrorsList().size() > 0){
                            for(String errorMessage: appointmentsResponse.getErrorsList() ){
                                Log.e("appntmntsRspnse error", errorMessage);
                                switch (errorMessage){
                                    case QMeNowModel.ERROR_CUSTOMER_NOT_IN_THIS_BUSINESS:
                                        appointmentsPresenter.setMessage(AppointmentsPresenter.MessageToUser.CUSTOMER_NOT_IN_THIS_BUSINESS);
                                        break;
                                }
                            }
                        }
                        else {
                            if (appointmentsResponse.getAppointmentList().size() > 0) {
                                appointmentsPresenter.setAppointmentsInCache(appointmentsResponse);
                                appointmentsPresenter.showAppointmentsList(appointmentsResponse);
                            } else {
                                appointmentsPresenter.setMessage(AppointmentsPresenter.MessageToUser.NO_APPOINTMENTS_FOUND);

                            }
                        }
                    }
                } else {
                    int statusCode = response.code();
                    Log.e("error statusCode", String.valueOf(statusCode));
                }
            }


            @Override
            public void onFailure(Throwable t) {
                Log.e("webHelper failure", t.getMessage());
                appointmentsPresenter.setMessage("Something went wrong: " + t.getMessage());
            }
        });
    }
}
