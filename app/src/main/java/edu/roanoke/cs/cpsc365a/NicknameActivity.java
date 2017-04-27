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


public class NicknameActivity extends AppCompatActivity {

    //--------------------------------------------------
    //
    //  Activity Properties
    //
    //--------------------------------------------------

    private static final int MAX_NICK_SIZE = 16;

    //--------------------------------------------------
    //
    //  Activity Core Handling
    //
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);
        if ((userPrefs.getString(Cons.USER_NICKNAME, "") != "") && (userPrefs.getString(Cons.USER_ID, "") != "")) {
            proceedToRoomMenu();
        }
    }

    //--------------------------------------------------
    //
    //  Click listeners
    //
    //--------------------------------------------------

    //  Description:
    //  The "Go!" button was pressed. Handles saving the nickname, and moving into the application.
    //
    //  Parameters:
    //  view is a Button object.
    //
    //  Possible Errors:
    //  If the nickname is too long, or contains spaces, the application will inform the user
    //  of the invalid nickname, and not proceed.
    public void handleGo(View view){
        String nickname = ((EditText)findViewById(R.id.nicknameField)).getText().toString();
        if (!nickname.isEmpty() && !nickname.contains(" ") && nickname.length() < MAX_NICK_SIZE) {
            saveNickname(nickname);
            proceedToRoomMenu();
        }
        else {
            informUser("Nicknames must be no more than 16 characters, and contain no spaces!");
        }
    }

    //--------------------------------------------------
    //
    //  Go! Handling
    //
    //--------------------------------------------------

    //  Description:
    //  Saves the user's entered nickname to the user's settings preferences.
    //
    //  Parameters:
    //  nickname is the user's nickname.
    private void saveNickname(final String nickname) {

        StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
        Call<UserResponse> call = apiService.createUser(nickname, "1");
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse>call, Response<UserResponse> response) {


                UserResponse r = response.body();
                
                //Get user's preferences.
                SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);

                //Save and commit the user's nickname to the shared preferences.
                SharedPreferences.Editor editor = userPrefs.edit();
                editor.putString(Cons.USER_NICKNAME, nickname);
                editor.putString(Cons.USER_ID, r.ID);
                editor.commit();

            }

            @Override
            public void onFailure(Call<UserResponse>call, Throwable t) {
                // Log error here since request failed
                System.out.println(t.toString());
            }
        });
    }

    //  Description:
    //  Takes the user to the room activity.
    private void proceedToRoomMenu() {
        Intent i = new Intent(getBaseContext(), RoomActivity.class);
        startActivity(i);
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

