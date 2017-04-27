package edu.roanoke.cs.cpsc365a;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<A_Quiz_Question> questions_list = new ArrayList<A_Quiz_Question>();
    ArrayAdapter<A_Quiz_Question> adapter = null;
    public int curr_index = 0;
    public TextView questionNum;
    public TextView questioned;
    public long start_time;
    public long end_time;
    public long total_time;

    ArrayList<A_Quiz_Question> submitter = new ArrayList<A_Quiz_Question>();


    protected void onCreate(Bundle savedInstanceState) {
        start_time = SystemClock.currentThreadTimeMillis();
        System.err.println("in on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        A_Quiz_Question one = new A_Quiz_Question("7 + 3 =", 10);
        A_Quiz_Question two = new A_Quiz_Question("5 * 4 =", 20);
        A_Quiz_Question three = new A_Quiz_Question("10 * 10 =", 100);
        A_Quiz_Question four = new A_Quiz_Question("15 + 7 =", 22);
        A_Quiz_Question five = new A_Quiz_Question("19 + 19 =", 38);
        A_Quiz_Question six = new A_Quiz_Question("15 * 5 =", 75);
        A_Quiz_Question seven = new A_Quiz_Question("77 + 7 =", 84);
        questions_list.add(one);
        questions_list.add(two);
        questions_list.add(three);
        questions_list.add(four);
        questions_list.add(five);
        questions_list.add(six);
        questions_list.add(seven);


        questionNum = (TextView) findViewById(R.id.question_number);
        questioned = (TextView) findViewById(R.id.question);
        int curr_index = 0;


        questionNum.setText(curr_index + 1);
        questioned.setText(questions_list.get(curr_index).getQuestion());


        Button submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText answered = (EditText) findViewById(R.id.ans_entry);
        int the_answer = Integer.parseInt(answered.getText().toString());
        A_Quiz_Question comparQuest = questions_list.get(curr_index);
        comparQuest.setCorrectness(comparQuest.checkCorrect(the_answer));
        submitter.add(comparQuest);
        curr_index += 1;
        if (curr_index < 7) {
            questionNum.setText(curr_index + 1);
            questioned.setText(questions_list.get(curr_index).getQuestion());

        }

        if (curr_index == 7) {
            //Get user's preferences.
            SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);
            String id = userPrefs.getString(Cons.USER_ID, "");
            String room = userPrefs.getString(Cons.ROOM_ID, "");

            StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
            Call<DataResponse> call = apiService.submitData(id, room, 0); //NEEDS TO BE NUMBER CORRECT
            call.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse>call, Response<DataResponse> response) {
                    System.out.println("SUBMITTED!");
                }

                @Override
                public void onFailure(Call<DataResponse>call, Throwable t) {
                    // Log error here since request failed
                    System.out.println(t.toString());
                }
            });
        }

    }

}




    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;


    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;


        //return super.onOptionsItemSelected(item);






