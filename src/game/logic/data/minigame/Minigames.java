package game.logic.data.minigame;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static game.logic.data.minigame.MinigameAns.GAVE_UP;

public class Minigames implements Serializable {

    private static final long serialVersionUID =1L;

    private Minigame typing, math, current;

    private int nMinigamePlays;

    private MinigameLog Log;

    private MinigameAns answerMinigame;

    public Minigames() {
       typing = new TypingMinigame();
       math = new MathMinigame();
       nMinigamePlays = 1;

        if(Math.random()/Math.nextDown(1.0)<0.50)
            current = math;
        else
            current = typing;

    }

    public void switchMinigames(){

        if(nMinigamePlays % 2 == 0) {
            if (current == math)
                current = typing;
            else
                current = math;
        }

    }

    public String GetQuestion() {

        return current.GetQuestion();

    }

    public MinigameAns InsertAnswer(String answer, String playerName,int turn){

        answerMinigame = current.InsertAnswer(answer);


        switch (answerMinigame){

            case WON -> {
                ++nMinigamePlays;
                addLog(playerName,turn,true);
                switchMinigames();
            }
            case TIME_OVER, GAVE_UP -> {
                ++nMinigamePlays;
                addLog(playerName, turn, false);
                switchMinigames();
            }

        }

          return answerMinigame;
    }

    public void addLog(String playerName,int turn,boolean won){

        Log = new MinigameLog(playerName,current.toString(),won,turn);

    }
    public void ClearLog(){
        Log = null;

    }
    public String getLog(){
        if(Log != null){

            return Log.toString();
        }
        return null;
    }

    public String getMinigameInfo(){
        return current.Info();
    }

    public MinigameAns getAnswer()
    {
        MinigameAns aux = answerMinigame;
        answerMinigame = null;
        return aux;
    }
}
