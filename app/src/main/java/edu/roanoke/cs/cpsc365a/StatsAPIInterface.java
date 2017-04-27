package edu.roanoke.cs.cpsc365a;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by connorricks on 4/26/17.
 */

public interface StatsAPIInterface {

    @GET("user/create")
    Call<UserResponse> createUser(@Query("username") String username, @Query("user_type") String userType);

    @GET("room/show/{code}")
    Call<RoomResponse> enterRoom(@Path("code") String code);

    @POST("response/create")
    Call<DataResponse> submitData(@Query("room_fkey") String username, @Query("user_fkey") String userType, @Body float data);
}


class UserResponse {

    @SerializedName("id")
    public String ID;

    @SerializedName("Error")
    public String error;
}

class RoomResponse {

    @SerializedName("ID")
    public String ID;

    @SerializedName("room_code")
    public String room;

    @SerializedName("task_fkey")
    public String task;
}

class DataResponse {

    @SerializedName("user_fkey")
    public String user;

    @SerializedName("room_fkey")
    public String room;

    @SerializedName("task_fkey")
    public String task;

    @SerializedName("id")
    public String ID;

    @SerializedName("Error")
    public String error;
}