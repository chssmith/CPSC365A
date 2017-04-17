package edu.roanoke.cs.cpsc365a.SetTask;

import java.util.Random;
public class Card {

    private int color;
    private int number;
    private int shape;
    private int shade;

    //Constructors

    public Card(){}


    public Card(int Seed) {
        Random rand = new Random(Seed);
        color = rand.nextInt(4);
        number = rand.nextInt(4);
        shape = rand.nextInt(4);
        shade = rand.nextInt(4);
    }

    //Initializer

    public void Gen(int Seed) {
        Random rand = new Random(Seed);
        color = rand.nextInt(4);
        number = rand.nextInt(4);
        shape = rand.nextInt(4);
        shade = rand.nextInt(4);
    }

    //Getters

    public int getColor() {
        return (color);
    }

    public int getNumber() {
        return (number);
    }

    public int getShape() {
        return (shape);
    }

    public int getShade() {
        return (shade);
    }

    //Print

    public void Print(){
        System.out.print(color);
        System.out.print(number);
        System.out.print(shape);
        System.out.print(shade);
    }
}
