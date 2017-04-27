package edu.roanoke.cs.cpsc365a;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    @GET("response/create")
    Call<DataResponse> submitData(@Query("room_fkey") String username, @Query("user_fkey") String userType, @Body Integer data);
}


class UserResponse {

    @SerializedName("id")
    public String ID;

    @SerializedName("Error")
    public String error;
}

class RoomResponse {

    @SerializedName("ID")
    private String ID;

    @SerializedName("room_code")
    private String room;

    @SerializedName("task_fkey")
    private String task;
}

class DataResponse {

    @SerializedName("user_fkey")
    private String user;

    @SerializedName("room_fkey")
    private String room;

    @SerializedName("task_fkey")
    private String task;

    @SerializedName("id")
    private String ID;

    @SerializedName("Error")
    public String error;
}