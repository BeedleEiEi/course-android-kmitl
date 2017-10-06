package lab07.student58070023.lazyinstagram.api;

/**
 * Created by student on 10/6/2017 AD.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LazyInstagramApi {
    public String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile") //เอาไปต่อท้าย url
    Call<UserProfile> getProfile(@Query("user") String userName); //Query UserName ที่เป็น Key จาก base url

}
