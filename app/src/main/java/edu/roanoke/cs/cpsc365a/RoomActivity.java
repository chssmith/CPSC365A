//  Connor Ricks
//
//  Please do not make changes to this activity, or its XML without consulting me on the reason why.
//

package edu.roanoke.cs.cpsc365a;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        if (!room.isEmpty() && room.matches("[0-9]+")) {
            enterRoom();
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
    private void enterRoom() {
        System.out.println("ENTERING A ROOM!");

        /*
        Make request to server, for the given room.
          Get response.
              If valid room.
                  Get the task from the response.
                  Present the task to the user, passing the room number along.
              Else
                  Inform user of the incorrect room.
        */
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
