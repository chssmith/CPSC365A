package edu.roanoke.cs.cpsc365a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sjwilson on 4/27/17.
 */

public class TextActivity extends AppCompatActivity {
    Button submitButton;
    EditText entry;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        submitButton = (Button)findViewById(R.id.button);
        entry = (EditText)findViewById(R.id.editText2);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float data = Float.parseFloat(entry.getText().toString());
                SaveData(data);
            }
        });
    }

    public void SaveData(float MyData){
        //Get user's preferences.
        SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);
        String id = userPrefs.getString(Cons.USER_ID, "");
        String room = userPrefs.getString(Cons.ROOM_ID, "");

        StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
        Call<DataResponse> call = apiService.submitData(id, room, (float)MyData);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse>call, Response<DataResponse> response) {
                Intent i = new Intent(getBaseContext(), RoomActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<DataResponse>call, Throwable t) {
                // Log error here since request failed
                System.out.println(t.toString());
            }
        });
    }
}
