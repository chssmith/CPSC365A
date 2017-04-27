package edu.roanoke.cs.cpsc365a.TracingTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.roanoke.cs.cpsc365a.R;

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
                if(button.getText() == "Submit") {
                    if(drawView.store.ifFinished()) {
                        int finishTime = drawView.getNonDomFinishTime();
                        drawView.saveDataInt(finishTime);
                        System.exit(0);
                    }
                }
                else {
                    if(drawView.store.ifFinished()) {
                        drawView.store.resetPoints(v);
                        button.setText("Submit");
                        drawView.setnonDomTrace();
                        drawView.invalidate();
                        //int finishTime = drawView.getDomFinishTime();
                        //drawView.saveDataInt(finishTime);
                    }
                }
            }

        });
    }
}
