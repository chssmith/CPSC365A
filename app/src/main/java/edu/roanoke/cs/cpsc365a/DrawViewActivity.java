package edu.roanoke.cs.cpsc365a;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawViewActivity extends Activity {
    private DrawingView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawview_activity_main);
        drawView = (DrawingView) findViewById(R.id.drawing);
        final Button button = (Button) findViewById(R.id.resetHand);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (button.getText() == "Submit") {
                    if (drawView.store.ifFinished()) {
                        int finishTime = drawView.getNonDomFinishTime();
                        saveData(finishTime, true);
                    }
                } else {
                    if (drawView.store.ifFinished()) {
                        drawView.store.resetPoints(v);
                        button.setText("Submit");
                        drawView.setnonDomTrace();
                        drawView.invalidate();
                        int finishTime = drawView.getDomFinishTime();
                        saveData(finishTime, false);
                    }
                }
            }

        });
    }

    //Boolean determines if the room activity should be presented or not after submitting.
    private void saveData(int time, final Boolean dismiss) {
        //Get user's preferences.
        SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);
        String id = userPrefs.getString(Cons.USER_ID, "");
        String room = userPrefs.getString(Cons.ROOM_ID, "");

        StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
        Call<DataResponse> call = apiService.submitData(id, room, (float) time);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse>call, Response<DataResponse> response) {
                if (dismiss) {
                    Intent i = new Intent(getBaseContext(), RoomActivity.class);
                    startActivity(i);
                }
            }
            @Override
            public void onFailure(Call<DataResponse>call, Throwable t) {
                // Log error here since request failed
                System.out.println(t.toString());
            }
        });
    }
}
