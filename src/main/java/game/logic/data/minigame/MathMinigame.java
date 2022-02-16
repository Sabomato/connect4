package game.logic.data.minigame;

import java.util.ArrayList;
import java.util.Scanner;


public class MathMinigame extends Minigame{

    private static  final int NCALCULATIONS = 5;

    private ArrayList<Calculation> results;
    private boolean firstQuestion = true;

    public MathMinigame() {
        this.results = new ArrayList<>(NCALCULATIONS);
        nAnswer = 0;
    }


    protected void IniciateGame() {
        StartWritingTime();

        for(int i = 0; i< NCALCULATIONS;++i){

            results.add(new Calculation());
        }
    }

    @Override
    public String GetQuestion() {

        if(nAnswer == 0)
            IniciateGame();

        return results.get(nAnswer).toString();

    }

    @Override
    public MinigameAns InsertAnswer(String answer) {
        int answerInt;
        if(answer.equals("End"))
        {
            endTurn();
            return MinigameAns.GAVE_UP;
        }


        firstQuestion = false;
        //System.out.println("nAnswer" + nAnswer );

        if(WritingTime()>= 30)
        {
            endTurn();
            return MinigameAns.TIME_OVER;
        }

        if(!verifyAnswer(answer))
            return MinigameAns.INVALID_ANSWER;

        try {
            answerInt = Integer.parseInt(answer);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return null;

        }



        int rightAnswer = results.get(nAnswer).getResult();

        if(rightAnswer != answerInt)
            return MinigameAns.WRONG_ANSWER;

        if (nAnswer == 4){

            endTurn();
            return MinigameAns.WON;
        }

        ++nAnswer;
        return MinigameAns.RIGHT_ANSWER;

    }

    private boolean verifyAnswer(String answer){

        Scanner sc = new Scanner(answer);
        if(!sc.hasNextInt()){
            sc.close();
            return false;
        }

        sc.close();
        return true;
    }

    private void endTurn(){
        firstQuestion = true;
        results.clear();
        nAnswer = 0;
    }
    @Override
    public String toString() {
        return "Math Minigame";
    }

    @Override
    public String Info() {
        if(firstQuestion)
            return this + ":Get this 5 calculations right in less then 30 seconds and win!";
        return "";
    }
}
