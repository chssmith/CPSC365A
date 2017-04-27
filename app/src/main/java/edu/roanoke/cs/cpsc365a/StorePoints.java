package edu.roanoke.cs.cpsc365a;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by gmgriswold on 3/22/17.
 */

class StorePoints {

    /*
        CLASS INVARIANT:  usedPoints is an ArrayList of Point objects.
                          numPoints is the amount of Point objects stored in usedPoints.
                          numTouched is an integer represeting how many points that are stored
                            in usedPoints where touched = true.
     */

    private ArrayList<Point> usedPoints;//used to hold the Point objects
    private int numPoints;              //total number of Points.
    private int numTouched;             //how many points has the user touched

    /*
        CONSTRUCTOR:
        numPoints = 0;
        usedPoints initialized.
     */

    public StorePoints() {
        usedPoints = new ArrayList<Point>();
        numPoints = 0;
        numTouched = 0;
    }

    /*
        PRE:Object is defined a defined StorePoints.  newPoint is a defined Point object
        POST:  usedPoints[numPoints] = newPoint
               numPoints is incremented by 1.
     */
    public void add_Points(Point newPoint) {
        usedPoints.add(numPoints, newPoint);
        numPoints++;
    }

    /*
        PRE: Object is a defined StorePoints.
        POST: returns numPoints
     */
    public int getSize() {
        return numPoints;
    }

    /*
        PRE:  Object is a defined StorePoints.  index is an integer representing the index value of
              usedPoints.
        POST: return usedPoints[index]
     */
    public Point getPoint(int index) {
        Point getPoint = usedPoints.get(index);
        return getPoint;
    }

    /*
        PURPOSE:  Search through the list and check if a point is in it.

        PRE:  checkPoint is a defined Point Object.
        POST: the list has been checked to see if checkPoint is in it. If it is
              checkPoint.getTouched() = true.
     */
    public void checkForPoint(Point checkPoint) {
        for(int index = 0; index < numPoints - 1; index++) {
            Point compare = usedPoints.get(index);
            if(!compare.getTouched()){
                int checkx = Math.abs(compare.getX_val() - checkPoint.getX_val());
                int checky = Math.abs(compare.getY_val() - checkPoint.getY_val());
                if((checkx < 25) && (checky < 25)){
                    usedPoints.get(index).setTouched();
                    numTouched++;
                }
            }
        }

    }



    /*
        PURPOSE: called for the button next hand
        PRE:  Object is a defined StorePoints.
        POST: sets the touched MDO of all points to false in usedPoints
    */
    public void resetPoints(View v) {
        for (int index = 0; index < numPoints - 1; index++) {
            Point reset = usedPoints.get(index);
            if (reset.getTouched()) {
                reset.resetTouched();
                numTouched--;
            }
        }
    }

    /*
        PURPOSE: This will indicate if the user has touched enough points.
        PRE: Object is a defined StorePoints
        POST: returns true iff numPoints >= (numPoints - 10)
     */
    public boolean ifFinished() {
        boolean finished = false;
        if (numTouched >= (numPoints - 5)) {
            finished = true;
        }
        return finished;
    }



}
