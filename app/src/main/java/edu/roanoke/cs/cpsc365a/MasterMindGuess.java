package edu.roanoke.cs.cpsc365a;

/**
 * Created by mcwynn on 3/26/17.
 */

public class MasterMindGuess {
    /*-- CI -----------------------------------------------------------------------
    |  pegColors[0] ... pegColors[pegsInGuess] are defined.
    |  hint[0] ... hint[pegsInGuess] are defined such that 0 <= hint[i] < 3.
    |------------------------------------------------------------------------------
    */

    private int pegColors[];
    private int hint[];

    public MasterMindGuess (int pegsInGuess) {
        pegColors = new int [pegsInGuess];
        hint = new int [pegsInGuess];
        // ASSERT: pegColors[0] ... pegColors[pegsInGuess] = 0.
        //         hint[0] ... hint[pegsInGuess] = 0.
        //         CI is satisfied.
    }

    public int getPegColor (int pegIndex) {
        int pegColor = 0;
        if (pegIndex < pegColors.length) {
            pegColor = pegColors[pegIndex];
        }

        return pegColor;
    }

    public void setPegColor (int pegIndex, int newColor) {
        if (pegIndex < pegColors.length) {
            this.pegColors[pegIndex] = newColor;
        }
    }

    public int getHint (int hintIndex) {
        return hint[hintIndex];
    }

    public void setHint (int hintIndex, int newValue) {
        this.hint[hintIndex] = newValue;
    }
}
