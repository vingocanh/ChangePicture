package com.vna.change.retrofit;

import com.vna.change.retrofit.model.DataReponcesHinhAnh;
import com.vna.change.retrofit.model.DataReponcesUser;
import com.vna.change.retrofit.model.User;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface API {



    @GET("getBackground?page=1&type=love%2F2160x3840")
    Observable<DataReponcesHinhAnh> readPicture();

    @GET("getBackground?page=1&type=nature%2F2160x3840")
    Observable<DataReponcesHinhAnh> readNature();

    @GET("getBackground?page=1&type=love%2F2160x3840")
    Observable<DataReponcesHinhAnh> readTime();

    //@GET("getBackground?page=1&type={type}%2F2160x3840")
    @GET("getBackground?page=1")
    Observable<DataReponcesHinhAnh> read(@Query("type") String type);




    @POST("api/v1/user/login")
    Observable<DataReponcesUser> login(@Query("username") String username,
                                       @Query("password") String password);



    @POST("api/v1/user/register")
    Observable<DataReponcesUser> register(@Body User account);
}
