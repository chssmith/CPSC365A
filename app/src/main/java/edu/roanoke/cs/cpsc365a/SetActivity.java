package edu.roanoke.cs.cpsc365a;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

//Key
//Color: 1 - red, 2 - green, 3 - purple
//Number: 1 - one, 2 - two, 3 - three
//Shape: 1 - diamond, 2 - pill, 3 - squiggly
//Shade: 1 - empty, 2 - shaded, 3, filled

public class SetActivity extends AppCompatActivity {

    ArrayList<Card> SelectedCards;
    ArrayList<ArrayList<Card>> FoundSets;

    Card Card1;
    Card Card2;
    Card Card3;
    Card Card4;
    Card Card5;
    Card Card6;
    Card Card7;
    Card Card8;
    Card Card9;
    Card Card10;
    Card Card11;
    Card Card12;

    TextView text;

    ImageButton DisplayCard1;
    ImageButton DisplayCard2;
    ImageButton DisplayCard3;
    ImageButton DisplayCard4;
    ImageButton DisplayCard5;
    ImageButton DisplayCard6;
    ImageButton DisplayCard7;
    ImageButton DisplayCard8;
    ImageButton DisplayCard9;
    ImageButton DisplayCard10;
    ImageButton DisplayCard11;
    ImageButton DisplayCard12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        SelectedCards = new ArrayList<Card>();
        FoundSets = new ArrayList<ArrayList<Card>>();

        Card1 = new Card(2, 1, 1, 3);
        Card2 = new Card(1, 1, 1, 3);
        Card3 = new Card(2, 1, 1, 2);
        Card4 = new Card(1, 1, 1, 2);
        Card5 = new Card(2, 3, 2, 3);
        Card6 = new Card(1, 3, 2, 3);
        Card7 = new Card(3, 3, 3, 1);
        Card8 = new Card(2, 3, 1, 2);
        Card9 = new Card(2, 2, 3, 3);
        Card10 = new Card(3, 2, 3, 3);
        Card11 = new Card(3, 2, 3, 1);
        Card12 = new Card(1, 2, 1, 2);

        text = (TextView) findViewById(R.id.MyText);

        DisplayCard1 = (ImageButton) findViewById(R.id.Card1);
        DisplayCard2 = (ImageButton) findViewById(R.id.Card2);
        DisplayCard3 = (ImageButton) findViewById(R.id.Card3);
        DisplayCard4 = (ImageButton) findViewById(R.id.Card4);
        DisplayCard5 = (ImageButton) findViewById(R.id.Card5);
        DisplayCard6 = (ImageButton) findViewById(R.id.Card6);
        DisplayCard7 = (ImageButton) findViewById(R.id.Card7);
        DisplayCard8 = (ImageButton) findViewById(R.id.Card8);
        DisplayCard9 = (ImageButton) findViewById(R.id.Card9);
        DisplayCard10 = (ImageButton) findViewById(R.id.Card10);
        DisplayCard11 = (ImageButton) findViewById(R.id.Card11);
        DisplayCard12 = (ImageButton) findViewById(R.id.Card12);


        //Listener added
        DisplayCard1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Check if the card has been selected
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card1) {
                        selected = true;
                    }
                }
                //Execute protocal for selected/not selected
                if (selected) {
                    DisplayCard1.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card1);
                } else {
                    DisplayCard1.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card1);
                }

                //Doing Everything Else
                DoEverythingElse();
            }
        });


        DisplayCard2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card2) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard2.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card2);
                } else {
                    DisplayCard2.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card2);
                }
                DoEverythingElse();
            }
        });


        DisplayCard3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card3) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard3.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card3);
                } else {
                    DisplayCard3.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card3);
                }
                DoEverythingElse();
            }
        });


        DisplayCard4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card4) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard4.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card4);
                } else {
                    DisplayCard4.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card4);
                }
                DoEverythingElse();
            }
        });


        DisplayCard5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card5) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard5.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card5);
                } else {
                    DisplayCard5.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card5);
                }
                DoEverythingElse();
            }
        });


        DisplayCard6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card6) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard6.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card6);
                } else {
                    DisplayCard6.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card6);
                }
                DoEverythingElse();
            }
        });


        DisplayCard7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card7) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard7.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card7);
                } else {
                    DisplayCard7.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card7);
                }

                DoEverythingElse();
            }
        });


        DisplayCard8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card8) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard8.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card8);
                } else {
                    DisplayCard8.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card8);
                }
                DoEverythingElse();
            }
        });


        DisplayCard9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card9) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard9.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card9);
                } else {
                    DisplayCard9.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card9);
                }
                DoEverythingElse();
            }
        });


        DisplayCard10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card10) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard10.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card10);
                } else {
                    DisplayCard10.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card10);
                }
                DoEverythingElse();
            }
        });


        DisplayCard11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card11) {
                        selected = true;
                    }
                }
                if (selected) {
                    DisplayCard11.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card11);
                } else {
                    DisplayCard11.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card11);
                }
                DoEverythingElse();
            }
        });


        DisplayCard12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for (int index = 0; index < SelectedCards.size(); index++) {
                    if (SelectedCards.get(index) == Card12) {
                        selected = true;
                    }
                }

                if (selected) {
                    DisplayCard12.setColorFilter(Color.argb(0, 0, 0, 0));
                    SelectedCards.remove(Card12);
                } else {
                    DisplayCard12.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(Card12);
                }

                DoEverythingElse();
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                SaveData(FoundSets.size());
                finish();
            }
        }, 60*1000);

    }


    public void DoEverythingElse() {
        if (SelectedCards.size() == 3) {
            if (HelperFunctions.isSet(SelectedCards.get(0), SelectedCards.get(1), SelectedCards.get(2))) {
                boolean duplicateSetFound = false;
                int index = 0;
                while (index < FoundSets.size() && !duplicateSetFound) {
                    if (FoundSets.get(index).contains(SelectedCards.get(0)) && FoundSets.get(index).contains(SelectedCards.get(1)) && FoundSets.get(index).contains(SelectedCards.get(2))) {
                        duplicateSetFound = true;
                    }
                    index++;
                }
                if (duplicateSetFound) {
                    text.setText("Already Found Set.");
                } else {
                    text.setText("Set Found!");
                    ArrayList<Card> copyCards = new ArrayList<Card>();
                    copyCards.add(SelectedCards.get(0));
                    copyCards.add(SelectedCards.get(1));
                    copyCards.add(SelectedCards.get(2));
                    FoundSets.add(copyCards);
                }
            } else {
                text.setText("Not a Set!");
            }
            DisplayCard1.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard2.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard3.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard4.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard5.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard6.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard7.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard8.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard9.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard10.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard11.setColorFilter(Color.argb(0, 0, 0, 0));
            DisplayCard12.setColorFilter(Color.argb(0, 0, 0, 0));

            SelectedCards.clear();
        }
    }

    public void SaveData(int MyData){}

}





