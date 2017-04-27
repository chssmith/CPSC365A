package edu.roanoke.cs.cpsc365a.SetTask;

public class Board{

    int maxCards = 9;

    int activeCards;
    int activeSeed;
    int FoundSetsSize;
    Card[] Cards;
    Card[][] FoundSets;


    //Conruction of the board
    Board(int Seed) {
        int activeCards = 0;
        int activeSeed = Seed;
        boolean unique = false;

        //Initializing the Board so all cards are unique and there are at least 3 sets
        while (!unique && (FoundSetsSize < 3)) {
            int FoundSetsSize = 0;
            Cards = new Card[maxCards];
            FoundSets = new Card[maxCards][3];

            //Creation of 9 random cards
            for (int index = 0; index < maxCards; index++) {
                Cards[index] = new Card(activeSeed + index);
            }
            activeSeed = activeSeed + maxCards;//change seed so the same cards aren't generated next loop

            //Checking that all 9 cards are unique
            unique = true;
            for (int index = 0; index < maxCards; index++) {
                for (int jndex = index+1; jndex < maxCards; jndex++) {
                    if (!Uniqueness(Cards[index], Cards[jndex])) {
                        unique = false;
                    }
                }
            }

            //Checking for Sets in our cards
            for (int index = 0; index < maxCards; index++) {
                for (int jndex = index+1; jndex < maxCards; jndex++) {
                    for (int kndex = jndex+1; kndex < maxCards; kndex++) {
                        if (isSet(Cards[index], Cards[jndex], Cards[kndex])){
                            FoundSets[FoundSetsSize][0] = Cards[index];
                            FoundSets[FoundSetsSize][1] = Cards[jndex];
                            FoundSets[FoundSetsSize][2] = Cards[kndex];
                            FoundSetsSize += 1;


                        }
                    }
                }
            }
        }

        for (int index = 0; index < maxCards; index ++){
            Cards[index].Print();
            System.out.println();
        }

    }
    //Returns True iff Card1 and Card 2 have unique attributes.
    boolean Uniqueness(Card Card1, Card Card2){
        boolean returnVal = true;

        int color1 = Card1.getColor();
        int number1 = Card1.getNumber();
        int shape1 = Card1.getShape();
        int shade1 = Card1.getShade();
        int color2 = Card2.getColor();
        int number2 = Card2.getNumber();
        int shape2 = Card2.getShape();
        int shade2 = Card2.getShade();

        if((color1 == color2) && (number1 == number2) && (shape1 == shape2) && (shade1 == shade2)){
            returnVal = false;
        }

        return(returnVal);
    }

    //Returns True iff Card1, Card2, and Card3 create a "Set"
    boolean isSet(Card Card1, Card Card2, Card Card3){
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
