package edu.roanoke.cs.cpsc365a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterMindActivity extends AppCompatActivity {
    /*-- CI -----------------------------------------------------------------------
    |  activeGuess is defined.
    |  0 <= curGuessPegIndex <= PEGS_IN_GUESS
    |  answer[0] ... answer[PEGS_IN_GUESS - 1] are defined.
    |  successfulGuess = true if a correct guess was made, false otherwise.
    |  0 <= totalGuesses.
    |  0 <= elapsedTime <= SECONDS_TO_PLAY.
    |------------------------------------------------------------------------------
    */
    //-- CONSTANTS ---------------------------------
    private static final int NUM_COLORS = 6;        // number of possible colors.
    private static final int PEGS_IN_GUESS = 4;     // number of pegs in a guess.
    private static final int CORRECT_PEG = 2;       // id given to a correct peg placement.
    private static final int SECONDS_TO_PLAY = 90;  // total time to complete task (in seconds).
    private static final int DOUBLE_DIGIT_SEC = 10; // holds the threshold to add a leading zero.
    private static final int MILLIS_IN_SEC = 1000;
    private static final int MILLIS_IN_MIN = 60000;
    //----------------------------------------------

    private CountDownTimer timer;
    private TextView countDownText;
    private List<MasterMindGuess> model = new ArrayList<>();
    private GuessAdapter adapter = null;

    private MasterMindGuess activeGuess;            // holds the active guess.
    private int curGuessPegIndex = 0;               // holds the current peg index in the
                                                    //   active guess.
    private int[] answer = new int[PEGS_IN_GUESS];  // holds the randomly generated answer.
    private boolean successfulGuess = false;        // true if the user make a correct guess,
                                                    //   false otherwise.
    private int totalGuesses = 0;                   // holds the total number of guesses made.
    private int elapsedTime = 0;                    // holds the total number of seconds in task.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_mind);

        // set on click listeners for the buttons.
        //   guess button
        Button guessButton = (Button) findViewById(R.id.GuessButton);
        guessButton.setOnClickListener(onGuess);
        //   peg buttons
        Button pegButton1 = (Button) findViewById(R.id.pegGuess1);
        Button pegButton2 = (Button) findViewById(R.id.pegGuess2);
        Button pegButton3 = (Button) findViewById(R.id.pegGuess3);
        Button pegButton4 = (Button) findViewById(R.id.pegGuess4);
        Button pegButton5 = (Button) findViewById(R.id.pegGuess5);
        pegButton1.setOnClickListener(setPeg);
        pegButton2.setOnClickListener(setPeg);
        pegButton3.setOnClickListener(setPeg);
        pegButton4.setOnClickListener(setPeg);
        pegButton5.setOnClickListener(setPeg);

        // add adapter to ListView.
        ListView list = (ListView)findViewById(R.id.guesses);
        adapter = new GuessAdapter();
        list.setAdapter(adapter);

        // instantiate the first guess.
        InstantiateGuess();
        // generate random answer.
        generateAnswer ();
        // create count down timer.
        createTimer ();
    }


    //------------------------------------------
    // OnCreate Functions
    //------------------------------------------

    // PRE:  Call during onCreate().
    // POST: CountDownTimer is created and started.
    private void createTimer () {
        countDownText = (TextView)findViewById(R.id.timerText);

        timer = new CountDownTimer(SECONDS_TO_PLAY * MILLIS_IN_SEC, MILLIS_IN_SEC) {
            @Override
            public void onTick(long millisUntilFinished) {
                // ASSERT: one (more) second has elapsed. Update the timer.
                String timeRemainingText;
                long minutesRemaining = millisUntilFinished / MILLIS_IN_MIN;
                long secondsRemaining = (millisUntilFinished % MILLIS_IN_MIN) / MILLIS_IN_SEC;

                // add minutes to text
                if (secondsRemaining < DOUBLE_DIGIT_SEC) {
                    // add the leading 0 before secondsRemaining. (i.e. 0:09)
                    timeRemainingText = (Long.toString(minutesRemaining)).concat(":0");
                }
                else {
                    // does not require the leading 0.
                    timeRemainingText = (Long.toString(minutesRemaining)).concat(":");
                }
                // add seconds to text
                timeRemainingText = timeRemainingText.concat(Long.toString(secondsRemaining));
                // set the TextView to show timeRemainingText.
                countDownText.setText(timeRemainingText);
                elapsedTime++;
            }

            @Override
            public void onFinish() {
                // ASSERT: The timer has hit 0. The game has ended in failure.
                countDownText.setText("0:00");
                elapsedTime++;
                SaveData(0); // send a 0 to the database signaling an unsuccessful game.
            }
        };

        timer.start();
    }

    // PRE:  Call during onCreate().
    // POST: A random answer has been generated.
    //       answer[0] ... answer[PEGS_IN_GUESS - 1] are defined.
    private void generateAnswer () {
        List<Integer> possibleColors = new ArrayList<>();
        int colorsRemaining = NUM_COLORS - 1;  // (-1) because the empty peg is color 0.
        Random r = new Random();

        // add all possible colors to possibleColors list.
        for (int colorIndex = 1; colorIndex < NUM_COLORS; colorIndex++) {
            possibleColors.add(colorIndex);
        }

        // add colors to answer.
        for (int answerIndex = 0; answerIndex < PEGS_IN_GUESS; answerIndex++) {
            // get a random integer for the index in possibleColors.
            int randomIndex = r.nextInt(colorsRemaining);
            // assign the answer peg a color from possibleColors.
            answer[answerIndex] = possibleColors.get(randomIndex);
            // remove the chosen color from possibleColors.
            possibleColors.remove(randomIndex);
            colorsRemaining--;
        }
    }


    //------------------------------------------
    // Guessing and Evaluating Functions
    //------------------------------------------

    // PRE:  activeGuess has been evaluated.
    // POST: A new MasterMindGuess is added to the guesses list and set as activeGuess.
    private void InstantiateGuess () {
        // create a new guess.
        MasterMindGuess newGuess = new MasterMindGuess(PEGS_IN_GUESS);
        // set activeGuess to reference newGuess so we can easily update it.
        activeGuess = newGuess;
        // add newGuess to the list of guesses.
        adapter.add(newGuess);
        // reset current peg index.
        curGuessPegIndex = 0;
    }

    // PRE:  All pegs in activeGuess are not empty.
    // POST: If guess was correct successfulGuess = true,
    //       else a new guess is created and set to activeGuess.
    //       Hints have been determined and displayed next to evaluated guess.
    private void evaluateGuess () {
        int numBothCorrect = 0;
        int numColorCorrect = 0;
        totalGuesses++;

        for (int pegIndex = 0; pegIndex < PEGS_IN_GUESS; pegIndex++) {
            int pegCorrectness = evaluatePeg (pegIndex);
            if (pegCorrectness == 1) {
                // ASSERT: correct color, incorrect position.
                numColorCorrect++;
            }
            else if (pegCorrectness == CORRECT_PEG){
                // ASSERT: correct color and position.
                numBothCorrect++;
            }
        }

        // create hints out of peg order to create anonymity.
        for (int hintIndex = 0; hintIndex < (numBothCorrect + numColorCorrect); hintIndex++) {
            if (hintIndex < numBothCorrect) {
                // create hints for both correct.
                activeGuess.setHint(hintIndex, CORRECT_PEG);
            }
            else {
                // create hints for just color.
                activeGuess.setHint(hintIndex, 1);
            }
        }

        if (numBothCorrect == PEGS_IN_GUESS) {
            // ASSERT: The user's guess was correct. They win the game.
            successfulGuess = true;
            // update screen to show final guess being correct (all black hints).
            adapter.notifyDataSetChanged();
            // cancel timer.
            timer.cancel();
            // upload data to the database.
            SaveData(1); // send a 1 to the database signaling a successful game.
        }
    }

    // PRE:  0 <= guessIndex < PEGS_IN_GUESS.
    // POST: RV = 0 if incorrect color and position.
    //            1 if correct color, incorrect position.
    //            2 if correct color and position.
    private int evaluatePeg (int guessIndex) {
        int hintValue = 0;  // RV, holds the level of correctness of this peg.
        int guessColor = activeGuess.getPegColor(guessIndex);

        if (guessColor == answer[guessIndex]) {
            // ASSERT: The peg at guessIndex has the correct position and color.
            hintValue = CORRECT_PEG;
        }
        else {
            // check to see if the current peg color is in the answer in any position.
            for (int pegIndex = 0; pegIndex < PEGS_IN_GUESS; pegIndex++) {
                if (guessColor == answer[pegIndex]) {
                    // ASSERT: The peg at guessIndex has the correct color, but not position.
                    hintValue = 1;
                }
            }
        }
        return hintValue;
    }


    //------------------------------------------
    // Database Interaction
    //------------------------------------------

    // PRE:  This task has ended by a successful guess or the timer has reached 0.
    //       successfulGuess is defined as 0 or 1.
    // POST: This task's data has been stored in the Stats on Stats database.
    private void SaveData (int data) {
        //Get user's preferences.
        SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);
        String id = userPrefs.getString(Cons.USER_ID, "");
        String room = userPrefs.getString(Cons.ROOM_ID, "");

        StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
        Call<DataResponse> call = apiService.submitData(id, room, (float)data);
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


    //------------------------------------------
    // On Click Listenersa
    //------------------------------------------

    // PRE:  Handles a click on the Guess button (a guess attempt).
    // POST: If validGuess the guess has been evaluated and hints displayed.
    //       If the guess was correct the game has ended, else a new guess has been created
    //       and set to be the active guess.
    private View.OnClickListener onGuess = new View.OnClickListener() {
        public void onClick(View v) {
            // handle guess validation.
            boolean validGuess = (curGuessPegIndex == PEGS_IN_GUESS);

            if (validGuess) {
                // ASSERT: All pegs are filled.
                // check guess correctness and give hint.
                evaluateGuess();
                // ASSERT: successfulGuess = true if the guess was correct, false otherwise.

                // if incorrect, instantiate a new guess.
                if (! successfulGuess) {
                    // ASSERT: The player's guess was incorrect.
                    InstantiateGuess();
                }
            }
        }
    };

    // PRE:  activeGuess is defined.
    // POST: If the clicked peg color is not already in activeGuess and the activeGuess has
    //       an empty peg (curGuessPegIndex < PEGS_IN_GUESS) the current peg in activeGuess
    //       has been set to the clicked color.
    private View.OnClickListener setPeg = new View.OnClickListener() {
        public void onClick(View v) {
            boolean containsClickedColor = false; // true if the active guess already contains
                                                  //   the clicked peg color, false otherwise.
            int clickedColor = 0;                 // holds the peg color clicked by the user.

            // get id of the clicked peg button.
            int clickedId = v.getId();

            // set the active guess's next open peg to the color clicked.
            if (curGuessPegIndex < PEGS_IN_GUESS) {
                // ASSERT: There is a guess peg open to add a color to.
                if (clickedId == R.id.pegGuess1) {
                    clickedColor = 1;
                }
                else if (clickedId == R.id.pegGuess2) {
                    clickedColor = 2;
                }
                else if (clickedId == R.id.pegGuess3) {
                    clickedColor = 3;
                }
                else if (clickedId == R.id.pegGuess4) {
                    clickedColor = 4;
                }
                else if (clickedId == R.id.pegGuess5) {
                    clickedColor = 5;
                }

                // check if clicked peg already exists in active guess.
                for (int pegIndex = 0; pegIndex < PEGS_IN_GUESS; pegIndex++) {
                    if (clickedColor == activeGuess.getPegColor(pegIndex)) {
                        // ASSERT: Active guess contains the clicked color.
                        containsClickedColor = true;
                    }
                }

                // add the clicked color to the active guess.
                if (! containsClickedColor) {
                    // ASSERT: Active guess does not contain the clicked color.
                    activeGuess.setPegColor(curGuessPegIndex, clickedColor);
                    // increment active guess's current peg index.
                    curGuessPegIndex++;
                    // update adapter (update peg images/colors).
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };


    //------------------------------------------
    // ListView Adapter Classes
    //------------------------------------------

    // Custom adapter for a Master Mind Guess.
    // Uses GuessHolder for the holder.
    private class GuessAdapter extends ArrayAdapter<MasterMindGuess> {
        GuessAdapter() {
            super(MasterMindActivity.this, R.layout.master_mind_guess, model);
        }

        public View getView (int position, View convertView, ViewGroup parent) {
            View row = convertView;
            GuessHolder holder;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();

                row = inflater.inflate(R.layout.master_mind_guess, parent, false);
                holder = new GuessHolder(row);
                row.setTag(holder);
            }
            else {
                holder = (GuessHolder)row.getTag();
            }

            holder.populateFrom(model.get(position));
            return (row);
        }
    }

    // Holder for the GuessAdapter
    private static class GuessHolder {
        /*-- CI -----------------------------------------------------------------------
        |  peg0, peg1, peg2, peg3 are defined.
        |  hint0, hint1, hint2, hint3 are defined.
        |------------------------------------------------------------------------------
        */

        private ImageView peg0 = null;
        private ImageView peg1 = null;
        private ImageView peg2 = null;
        private ImageView peg3 = null;
        private ImageView hint0 = null;
        private ImageView hint1 = null;
        private ImageView hint2 = null;
        private ImageView hint3 = null;

        GuessHolder(View row) {
            // get peg and hint objects.
            peg0 = (ImageView)row.findViewById(R.id.peg0);
            peg1 = (ImageView)row.findViewById(R.id.peg1);
            peg2 = (ImageView)row.findViewById(R.id.peg2);
            peg3 = (ImageView)row.findViewById(R.id.peg3);
            hint0 = (ImageView)row.findViewById(R.id.hint0);
            hint1 = (ImageView)row.findViewById(R.id.hint1);
            hint2 = (ImageView)row.findViewById(R.id.hint2);
            hint3 = (ImageView)row.findViewById(R.id.hint3);
            // ASSERT: CI is satisfied.
        }

        // PRE:  guess is defined.
        // POST: The peg and hint colors have been updated in the ListView
        //       based on the values in guess.
        void populateFrom (MasterMindGuess guess) {
            // set peg colors (background images).
            setPegImage (peg0, guess.getPegColor(0));
            setPegImage (peg1, guess.getPegColor(1));
            setPegImage (peg2, guess.getPegColor(2));
            setPegImage (peg3, guess.getPegColor(3));
            // set hint colors (background images).
            setHintColor (hint0, guess.getHint(0));
            setHintColor (hint1, guess.getHint(1));
            setHintColor (hint2, guess.getHint(2));
            setHintColor (hint3, guess.getHint(3));
        }

        // PRE:  peg is defined. 0 <= color <= 5.
        // POST: peg's color has been updated in the ListView.
        void setPegImage (ImageView peg, int color) {
            if (color == 0) {
                peg.setBackgroundResource(R.drawable.peg_empty);
            }
            else if (color == 1) {
                peg.setBackgroundResource(R.drawable.peg_red);
            }
            else if (color == 2) {
                peg.setBackgroundResource(R.drawable.peg_blue);
            }
            else if (color == 3) {
                peg.setBackgroundResource(R.drawable.peg_green);
            }
            else if (color == 4) {
                peg.setBackgroundResource(R.drawable.peg_purple);
            }
            else {
                peg.setBackgroundResource(R.drawable.peg_orange);
            }
        }

        // PRE:  hint is defined. 0 <= color <= 3.
        // POST: hint's color has been updated in the ListView.
        void setHintColor (ImageView hint, int color) {
            if (color == 0) {
                hint.setBackgroundResource(R.drawable.hint_empty);
            }
            else if (color == 1) {
                hint.setBackgroundResource(R.drawable.hint_white);
            }
            else {
                hint.setBackgroundResource(R.drawable.hint_black);
            }
        }
    }


}
