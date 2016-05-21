package demo.bluemongo.com.QMeNowClient.api;

import demo.bluemongo.com.QMeNowClient.model.AppointmentCheckInDTO;
import demo.bluemongo.com.QMeNowClient.model.AppointmentsResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by glenn on 4/10/15.
 */


public interface UserAppointmentService {


    @GET("/QMeNow/FlexibleUIConfig/api/v1/AppointmentsToday/{businessId}/")
    Call<AppointmentsResponse> getAppointments(@Path("businessId") int businessId, @Query("customerId") int customerId,
                                               @Query("firstName") String firstName, @Query("lastName") String lastName );

    @PUT("/QMeNow/FlexibleUIConfig/api/v1/Appointment/{id}/CheckIn")
    Call<AppointmentsResponse> checkInAppointment(@Path("id") int id, @Body AppointmentCheckInDTO appointmentCheckIn);




/*    @GET("/FlexibleUIConfig/api/v1/AppointmentsTodayTest/{customerId}")
    Call<List<Appointment>> getAppointmentsTest(@Path("customerId") int customerId);

    @GET("/FlexibleUIConfig/api/v1/Appointments?customerId={customerId}&firstName={firstName}&lastName={lastName}")
    Call<List<Appointment>> getAppointmentsByUserIDAndName(@Query("customerId") int customerId, @Query("firstName") String firstName, @Query("lastName") String lastName);

    @GET("/FlexibleUIConfig/api/v1/test/{userId}")
    Call<List<String>> doTest(@Path("userId") Integer userId);*/




/*    @GET("/SpringMVCJsonTest/FlexibleUIConfig/user/getAppointmentsTest/1234")
    Call<List<Appointment>> getUserAppointmentsTest();

    @GET("/SpringMVCJsonTest/FlexibleUIConfig/user/retrieveAppointmentsFromWeb/{userId}")
    Call<List<Appointment>> getUserAppointments(@Path("userId") int userId);

    @GET("/SpringMVCJsonTest/FlexibleUIConfig/user/getUserMatches?firstName={firstName}&lastName={lastName}")
    Call<List<UserDetails>> getUserMatchesByName(@Path("firstName") String firstName, @Path("lastName") String lastName);*/

}
