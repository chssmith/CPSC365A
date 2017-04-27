package edu.roanoke.cs.cpsc365a;

public class HelperFunctions {

    //Returns True iff Card1, Card2, and Card3 create a "Set"
    static boolean isSet(Card Card1, Card Card2, Card Card3){
        boolean NumberSet = false;
        boolean ColorSet = false;
        boolean ShapeSet = false;
        boolean ShadeSet = false;

        if (((Card1.getNumber() != Card2.getNumber()) && (Card2.getNumber() != Card3.getNumber()) && (Card1.getNumber() != Card3.getNumber())) || ((Card1.getNumber() == Card2.getNumber()) && (Card2.getNumber() == Card3.getNumber()) && (Card1.getNumber() == Card3.getNumber()))){
            NumberSet = true;
        }
        if (((Card1.getColor() != Card2.getColor()) && (Card2.getColor() != Card3.getColor()) && (Card1.getColor() != Card3.getColor())) || ((Card1.getColor() == Card2.getColor()) && (Card2.getColor() == Card3.getColor()) && (Card1.getColor() == Card3.getColor()))) {
            ColorSet = true;
        }
        if (((Card1.getShape() != Card2.getShape()) && (Card2.getShape() != Card3.getShape()) && (Card1.getShape() != Card3.getShape())) || ((Card1.getShape() == Card2.getShape()) && (Card2.getShape() == Card3.getShape()) && (Card1.getShape() == Card3.getShape()))) {
            ShapeSet = true;
        }
        if (((Card1.getShade() != Card2.getShade()) && (Card2.getShade() != Card3.getShade()) && (Card1.getShade() != Card3.getShade())) || ((Card1.getShade() == Card2.getShade()) && (Card2.getShade() == Card3.getShade()) && (Card1.getShade() == Card3.getShade()))) {
            ShadeSet = true;
        }

        return(NumberSet && ColorSet && ShapeSet && ShadeSet);
    }

}
