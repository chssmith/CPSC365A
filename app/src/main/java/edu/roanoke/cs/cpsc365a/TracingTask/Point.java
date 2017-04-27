package edu.roanoke.cs.cpsc365a.TracingTask;



/**
 * Created by gmgriswold on 3/22/17.
 */

class Point {
    /*
        CLASS INVARIANT: x_val and y_val are integers representing x and y coordinates,

                         touched is a boolean representing if the user has touched this point.
                            touched is true iff the user has touched the point.
     */

    private int x_val; //x coordinate of point
    private int y_val; //y coordinate of point
    private boolean touched; //has this point been touched by the user

    /*
        DEFAULT CONSTRUCTOR:

        x_val, y_val and legNum = 0
        touched = false

        ALL values have been set. User must remember to change these values if they are going to
        use this point.

     */
    Point() {
        x_val = 0;
        y_val = 0;
        touched = false;
    }

    /*
      CONSTRUCTOR:
      x_val = x_point
      y_val = y_point
      touched = touch

      All values have been set.

     */
    Point(int x_point, int y_point, boolean touch) {
        x_val = x_point;
        y_val = y_point;
        touched = touch;
    }

    /*
     PRE:  xval and yval are both ints representing an x coord and a y coord
     POST: x_val = xval
           y_val = yval
     */
    public void setXandY(int xval, int yval) {
        x_val = xval;
        y_val = yval;
    }
    /*
      PRE: Object is a valid point.
      POST: sets value to true after the point has been touched.
     */
    public void setTouched() {
        touched = true;
    }

    /*
        PURPOSE: This function will be used upon resetting values for after the user completes their
          first trace.

        PRE: Object is a valid point where touched = true.
        POST: touched = false;
     */
    public void resetTouched() {
        touched = false;
    }


    /*
       PRE: Object is a defined point.
       POST: returns x_val
     */
    public int getX_val() {
        return x_val;
    }

    /*
        PRE: Object is a defined point.
        POST: returns y_val;
     */
    public int getY_val() {
        return y_val;
    }

    /*
        PRE: Object is a defined point.
        POST: returns touched
    */

    public boolean getTouched() {
        return touched;
    }

}
