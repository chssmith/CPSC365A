package edu.roanoke.cs.cpsc365a.TracingTask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import edu.roanoke.cs.cpsc365a.Cons;
import edu.roanoke.cs.cpsc365a.StatsAPI;
import edu.roanoke.cs.cpsc365a.StatsAPIInterface;

import static android.R.attr.data;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by gmgriswold on 3/13/17.
 */

public class DrawingView extends View {
    /*
        CLASS INVARIANT:
                drawPaint, canvasPaint and bluePaint are paint objects used to draw the View.
                redPath is used to store the path the user traces.
                blackPath is used to store the original star.
                canvasBitmap is a bitmap object
                store is a StorePoints object used to store the point objects which are created from
                    user touch.
                newPoint is a point object which has an (x,y) value. where x is the x coordinate and
                    y is the y coordinate.
                added is a boolean to track if the points of the star have been added to store.
                domTrace is true iff user is on the dominant trace
                nonDomTrace is true iff user is on the non dominant trace
                domTraceStart is start time of the dominant trace
                domTraceEnd  is end time of the dominant trace
                nonDomTraceStart is start time of non dominant trace
                nonDomTraceEnd is end time of non dominant trace
     */
    private Paint drawPaint, canvasPaint, bluePaint;
    private Path redPath;
    private Path blackPath;
    private Bitmap canvasBitmap;
    StorePoints store;
    Point newPoint;
    boolean domTrace;
    boolean nonDomTrace;
    boolean added; //this will be used for the purpose of keeping track if the points of the star
        //have been added into store.  True if they have been, false otherwise.
    long domTraceStart;    //dom hand trace start time
    long domTraceEnd;      //dom hand trace end time
    long nonDomTraceStart; //non-dom hand trace start time
    long nonDomTraceEnd;   //non-dome hand trace end time

    /*
        Constructor
     */
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawingView();
        createTutorial(context);
    }

    /*
       PRE:  a DrawingView has been created
       POST: Member Data Objects are initialized
    */
    private void setupDrawingView() {
        drawPaint = new Paint();
        bluePaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(true);
        bluePaint.setStrokeWidth(20);
        bluePaint.setStyle(Paint.Style.STROKE);
        bluePaint.setStrokeJoin(Paint.Join.ROUND);
        bluePaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        store = new StorePoints();
        newPoint = new Point();
        added = false;
        domTrace = true;
        nonDomTrace = false;
        setUpTimes();
    }


    /*
        domTraceStart, domTraceEnd, nonDomTraceStart, nonDomTraceEnd, newTime are initialized
     */
    public void setUpTimes() {
        domTraceStart = 0;
        domTraceEnd = 0;
        nonDomTraceStart = 0;
        nonDomTraceEnd = 0;
    }


    /*
        CREATING ALERT DIALOGS
     */
    public void createTutorial (Context context) {
        AlertDialog.Builder instructions = new AlertDialog.Builder(context);
        instructions.setTitle("Instructions");
        instructions.setMessage("Use your dominant hand to trace the image. Hit the back button to begin");
        instructions.setCancelable(true);
        AlertDialog alert = instructions.create();
        alert.show();
    }

    public AlertDialog createNonDomInst(Context context) {
        AlertDialog.Builder next_instructions = new AlertDialog.Builder(context);
        next_instructions.setTitle("Non-dominant hand");
        next_instructions.setMessage("Good Job you have completed the trace! Hit back and press next hand button to do the trace with your next hand.");
        next_instructions.setCancelable(true);
        AlertDialog alert = next_instructions.create();
        return(alert);
    }

    public AlertDialog createFinalInst(Context context) {
        AlertDialog.Builder next_instructions = new AlertDialog.Builder(context);
        next_instructions.setTitle("Completed");
        next_instructions.setMessage("Good Job you are finished. Hit back and press Submit to exit the activity.");
        next_instructions.setCancelable(true);
        AlertDialog alert = next_instructions.create();
        return(alert);
    }

    /*
       ENDING ALERT DIALOGS
     */


    //@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);
        //create canvasBitmap
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    }


    public void setnonDomTrace() {
        nonDomTrace = true;
        nonDomTraceStart = System.currentTimeMillis();
    }
    /*
        PRE: canvas is a defined chanvas, toPaint is a defined paint
        POST: creates the points of the star and stores them in store
              lines have been drawn between each consecutive points in store.
     */
    private void drawLine(Canvas canvas, Paint toPaint){
        float mid = getWidth() / 2;
        float min = Math.min(getWidth(), getHeight());
        float half = min / 2;
        mid = mid - half;
        int totalPoints = store.getSize();
        if(!added) {
            addPointsOfStar(mid, half);
            //Only add points one time.
        }
        redPath = new Path(); //initialize redPath
        blackPath = new Path(); //initialize blackPath
        for(int index = 0; index < (totalPoints - 1); index++) {
            Point part1 = store.getPoint(index);    //create a start point
            Point part2 = store.getPoint(index + 1); //create an end point
            if(part1.getTouched()){ //if user has touched the point, draw this line segment a different color
                redPath.moveTo((float) part1.getX_val(), (float) part1.getY_val());
                redPath.lineTo((float) part2.getX_val(), (float) part2.getY_val());
                redPath.close();
                canvas.drawPath(redPath, bluePaint);
                //draw the path.
            }
            else {
                //draw the points black
                blackPath.moveTo((float) part1.getX_val(), (float) part1.getY_val());
                blackPath.lineTo((float) part2.getX_val(), (float) part2.getY_val());
                blackPath.close();
                canvas.drawPath(blackPath, toPaint);
            }
        }

    }

    /*
        PRE:  mid and half are floats
        POST: creates points at the tip of each star.
              sends starting and ending points of each star to loadPoints function
              added is set to true indicating the points have been added into store, ensuring
               the sure does not keep adding to store.
     */
    private void addPointsOfStar(float mid, float half) {
        //top left to top right
        Point Start = new Point ((int)(mid+half * 0.5f), (int)(half * 0.84f), false);//top Left
        Point End = new Point ((int)(mid + half * 1.5f), (int) (half * 0.84f), false);//top right
        loadPoints(Start, End, 4);
        //top right to bottom left
        Point Start1 = new Point ((int)(mid+half * 1.5f), (int)(half * 0.84f), false); //top right
        Point End1 = new Point ((int)(mid + half * 0.68f), (int) (half * 1.45f), false);//bot left
        loadPoints(Start1, End1, 4);
        //bottom left to top tip
        Point Start2 = new Point ((int)(mid + half * 0.68f), (int) (half * 1.45f), false);//bot left
        Point End2 = new Point ((int)(mid + half * 1.0f), (int) (half * 0.5f), false);//top tip
        loadPoints(Start2, End2, 4);
        //top tip to bottom right
        Point Start3 = new Point ((int)(mid + half * 1.0f), (int) (half * 0.5f), false);//top tip
        Point End3 = new Point ((int)(mid + half * 1.32f), (int) (half * 1.45f), false);//bottom right
        loadPoints(Start3, End3, 4);
        //bottom right to top tip
        Point Start4 = new Point ((int)(mid + half * 1.32f), (int) (half * 1.45f), false);
        Point End4 = new Point ((int)(mid + half * 0.5f), (int) (half * 0.84f), false);
        loadPoints(Start4, End4, 4);
        added = true;
    }

    /*
        draws the star upon loading the app, and after every invalidate call.  These invalidate calls
          are located in onTouchEvent and the onClick function in MainActivity
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        drawLine(canvas, drawPaint);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }

    /*
        PRE: user needs to touch the screen
        POST: gets the x and y coordinate of user touch. Adds the point
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(domTraceStart == 0) {
            domTraceStart = System.currentTimeMillis();
        }
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        newPoint.setXandY(touchX, touchY);
        store.checkForPoint(newPoint);
        if(domTrace) {
            if (store.ifFinished()) {
                //if user has finished the trace, show the alert.
                AlertDialog nextInst = createNonDomInst(getContext());
                nextInst.show();
                domTrace = false;
                domTraceEnd = System.currentTimeMillis();
            }
        }
        if (nonDomTrace) {
            if(store.ifFinished()) {
                //the user just finished their last trace. Notify them to click submit.
                AlertDialog finalInst = createFinalInst(getContext());
                finalInst.show();
                nonDomTrace = false;
                nonDomTraceEnd = System.currentTimeMillis();
            }
        }
        invalidate();
        return true;
        }

    /* PRE:: start and end are two points representing the start of a line and the end of the line.
       These will be two corresponding points on the drawn star of the canvas, but not the same
       point.  numTimes > 0 and represents the depth of the recursion.  The depth of recursion will be,
       If you want more points increase numTimes, if you want less points, then decrease numTimes.
       POST:  store will have been populated with the points along the path of the star. The number
       of which depend on the size of numTimes.
    */
    private void loadPoints(Point start, Point end, int numTimes) {
        if(numTimes == 0) {
            Point MidPoint = getMidPoint(start, end);
            store.add_Points(MidPoint);
        }
        else {
            Point midPoint = getMidPoint(start, end);
            numTimes--;
            loadPoints(start, midPoint, numTimes);
            store.add_Points(midPoint);
            loadPoints(midPoint, end, numTimes);
        }
    }

    /*
        PRE:  start and end are both defined Point objects.
        POST: returns a new Point object which is the midpoint between start and end.
     */
    private Point getMidPoint(Point start, Point end) {
        float newX = (start.getX_val() + end.getX_val()) / 2; //get the average of x coords
        float newY = (start.getY_val() + end.getY_val()) / 2; //get the average of y coords
        Point retPoint = new Point((int)newX, (int)newY, false);
        return (retPoint);
    }

    public int getDomFinishTime() {
        long finalDomTime = (domTraceEnd - domTraceStart) / 1000;
        int finishtime = (int)finalDomTime;
        return (finishtime);

    }

    public int getNonDomFinishTime() {
        long finalDomTime = (nonDomTraceEnd - nonDomTraceStart) / 1000;
        int finishtime = (int)finalDomTime;
        return (finishtime);
    }

    //NEEDS THE RIGHT PACKAGE IMPORTED
    public void saveDataInt(int time){
        //Get user's preferences.
        SharedPreferences userPrefs = getSharedPreferences(Cons.USER_SETTINGS, MODE_PRIVATE);
        String id = userPrefs.getString(Cons.USER_ID, "");
        String room = userPrefs.getString(Cons.ROOM_ID, "");

        StatsAPIInterface apiService = StatsAPI.getClient().create(StatsAPIInterface.class);
        Call<DataResponse> call = apiService.submitData(id, room, time);
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

