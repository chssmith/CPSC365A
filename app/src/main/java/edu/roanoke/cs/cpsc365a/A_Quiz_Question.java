package edu.roanoke.cs.cpsc365a;

/**
 * Created by Peter on 4/17/2017.
 */

public class A_Quiz_Question {
    private String Question;
    private int answer;
    private boolean correct;


    //Pre: Default Constructor for the A_Quiz object.
    //     Takes a string object, quest, representing the arithmetic question.
    //     Also takes an integer, ans, which is the correct answer for the question.
    //     By default, the boolean value is set to false until correct answer is given.
    //Post: Creates a new A_Quiz object with answer = ans and
    //      Question equivalent to quest.
    public A_Quiz_Question(String quest, int ans) {
        Question = quest;
        answer = ans;
        correct = false;
    }

    public boolean isCorrect(){
        return correct;
    }
    //Pre: Takes an integer, UserAns, which represents the integer
    //     input by the user, attempting to answer the question.
    //Post: If the user inputed the correct answer, the method
    public boolean checkCorrect(int UserAns) {
        if (UserAns == answer) {
            correct = true;
        }
        return correct;
    }

    public String getQuestion () {
        return Question;
    }

    public void setCorrectness (boolean boo) {
        correct = boo;
    }

    public int getAnswer () {
        return answer;
    }
}



