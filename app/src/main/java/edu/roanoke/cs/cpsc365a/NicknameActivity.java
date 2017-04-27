package edu.roanoke.cs.cpsc365a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        Intent Set = new Intent (this, SetActivity.class);
        startActivity(Set);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
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
    private void saveNickname(String nickname) {

        /*
        Make request to server, to add user and get UID.
          Get response.
              If success.
                  Get the UID from the response.
                  Save the user's nickname & UID to Shared Preferences
              Else
                  Inform user of the error.
        */

        //Get user's preferences.
        SharedPreferences userPrefs = getSharedPreferences(getResources().getString(R.string.USER_SETTINGS), MODE_PRIVATE);

        //Save and commit the user's nickname to the shared preferences.
        SharedPreferences.Editor editor = userPrefs.edit();
        editor.putString(getResources().getString(R.string.USER_NICKNAME), nickname);
        editor.commit();
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

