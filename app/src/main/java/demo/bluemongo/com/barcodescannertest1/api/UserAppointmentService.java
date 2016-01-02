package demo.bluemongo.com.barcodescannertest1.api;

import java.util.List;

import demo.bluemongo.com.barcodescannertest1.model.Appointment;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentsResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by glenn on 4/10/15.
 */


public interface UserAppointmentService {


    @GET("/FlexibleUIConfig/api/v1/AppointmentsToday/{customerId}")
    //Call<List<Appointment>> retrieveAppointments(@Path("customerId") int customerId);
    Call<AppointmentsResponse> getAppointments(@Path("customerId") int customerId);

    @GET("/FlexibleUIConfig/api/v1/AppointmentsTodayTest/{customerId}")
    Call<List<Appointment>> getAppointmentsTest(@Path("customerId") int customerId);

    @GET("/FlexibleUIConfig/api/v1/Appointments?customerId={customerId}&firstName={firstName}&lastName={lastName}")
    Call<List<Appointment>> getAppointmentsByUserIDAndName(@Query("customerId") int customerId, @Query("firstName") String firstName, @Query("lastName") String lastName);

    @GET("/FlexibleUIConfig/api/v1/test/{userId}")
    Call<List<String>> doTest(@Path("userId") Integer userId);


/*    @GET("/SpringMVCJsonTest/FlexibleUIConfig/user/getAppointmentsTest/1234")
    Call<List<Appointment>> getUserAppointmentsTest();

    @GET("/SpringMVCJsonTest/FlexibleUIConfig/user/retrieveAppointments/{userId}")
    Call<List<Appointment>> getUserAppointments(@Path("userId") int userId);

    @GET("/SpringMVCJsonTest/FlexibleUIConfig/user/getUserMatches?firstName={firstName}&lastName={lastName}")
    Call<List<UserDetails>> getUserMatchesByName(@Path("firstName") String firstName, @Path("lastName") String lastName);*/

}
