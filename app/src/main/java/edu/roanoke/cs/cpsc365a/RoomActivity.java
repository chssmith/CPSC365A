//  Connor Ricks
//
//  Please do not make changes to this activity, or its XML without consulting me on the reason why.
//

package edu.roanoke.cs.cpsc365a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomActivity extends AppCompatActivity {

    //--------------------------------------------------
    //
    //  Activity Properties
    //
    //--------------------------------------------------

    //--------------------------------------------------
    //
    //  Activity Core Handling
    //
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
    }

    //--------------------------------------------------
    //
    //  Click listeners
    //
    //--------------------------------------------------

    //  Description:
    //  The "Enter!" button was pressed. Handles entering a room session to perform a task.
    //
    //  Parameters:
    //  view is a Button object.
    //
    //  Possible Errors:
    //  If the room string is empty or contains anything but numerical characters, informs the user
    //  of an invalid format.
    public void handleEnter(View view) {
        String room =  ((EditText)findViewById(R.id.roomField)).getText().toString();
        if (!room.isEmpty()) {
            enterRoom(room);
        }
        else {
            informUser("Rooms are numberical");
        }
    }

    //--------------------------------------------------
    //
    //  Enter! Handling
    //
    //--------------------------------------------------

    //  Description:
    //  Contacts the server, attempting to get information about a room number.
    //
    //  Possible Errors:
    //  No internet connection, invalid room number, invalid room format.
    private void enterRoom(String room) {
        System.out.println("WE ENTERED A ROOOOOOOOOOOOM");
        StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
        Call<RoomResponse> call = apiService.enterRoom(room);
        call.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(Call<RoomResponse>call, Response<RoomResponse> response) {

                //Get user's preferences.
                SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);

                //Save and commit the user's nickname to the shared preferences.
                SharedPreferences.Editor editor = userPrefs.edit();
                editor.putString(Cons.ROOM_ID, response.body().room);
                editor.commit();

                String roomType = response.body().task;
                if (roomType.equals("1")) {
                    System.out.println("TEXT ENTRY");
                    Intent i = new Intent(getBaseContext(), TextActivity.class);
                    startActivity(i);
                }
                else if (roomType.equals("2")) {
                    System.out.println("SET");
                    Intent i = new Intent(getBaseContext(), SetActivity.class);
                    startActivity(i);
                }
                else if (roomType.equals("3")) {
                    System.out.println("TRACING");
                    Intent i = new Intent(getBaseContext(), DrawViewActivity.class);
                    startActivity(i);
                }
                else if (roomType.equals("4")) {
                    System.out.println("MASTERMIND");
                    Intent i = new Intent(getBaseContext(), MasterMindActivity.class);
                    startActivity(i);
                }
                else if (roomType.equals("5")) {
                    System.out.println("MATH");
                    Intent i = new Intent(getBaseContext(), QuizActivity.class);
                    startActivity(i);
                }



            }

            @Override
            public void onFailure(Call<RoomResponse>call, Throwable t) {
                // Log error here since request failed
                System.out.println(t.toString());
            }
        });

    }

    //  Description:
    //  Informs the user that the entered nickname is invalid.
    //
    //  Parameters:
    //  message is a String object.
    private void informUser(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
