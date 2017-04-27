package edu.roanoke.cs.cpsc365a.SetTask;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        final ArrayList<String> SelectedCards = new ArrayList<String>();

        ImageButton Card1 = (ImageButton) findViewById(R.id.Card1);
        ImageButton Card2 = (ImageButton) findViewById(R.id.Card2);
        ImageButton Card3 = (ImageButton) findViewById(R.id.Card3);
        ImageButton Card4 = (ImageButton) findViewById(R.id.Card4);
        ImageButton Card5 = (ImageButton) findViewById(R.id.Card5);
        ImageButton Card6 = (ImageButton) findViewById(R.id.Card6);
        ImageButton Card7 = (ImageButton) findViewById(R.id.Card7);
        ImageButton Card8 = (ImageButton) findViewById(R.id.Card8);
        ImageButton Card9 = (ImageButton) findViewById(R.id.Card9);

        Card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });Card6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });
        Card9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean selected = false;
                for(int index = 0; index < 3; index ++) {
                    if(SelectedCards.get(index) == this.ID){
                        selected = true;
                    }
                }

                if (selected){
                    this.setColorFilter(Color.argb(0,0,0,0));
                    SelectedCards.remove(this.ID);
                }else {
                    this.setColorFilter(Color.argb(133, 0, 0, 0));
                    SelectedCards.add(this.ID);
                }
            }
        });

    }
}
