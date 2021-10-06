package game.logic.data.minigame;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TypingMinigame extends Minigame{

    private static final int NRAND_WORDS = 5,NWORDS = 100;
    private static final String FILE_NAME ="dependencies//100WordsMinigame.txt";
    private ArrayList<String> Words;
    private int nCharsInWords;
    private boolean firstQuestion = true;

    public TypingMinigame() {

        Words = new ArrayList<>(NRAND_WORDS);
    }

    /*
        protected void IniciateGame()throws IOException {
            StartWritingTime();
            get5RandomWordsFromFile();
        }
    */

    private void getNRandomWordsFromFile()  {
        Words.clear();

        int Wordn;
        BufferedReader in = null;
        String aux;

        try {
            in = new BufferedReader(new FileReader(FILE_NAME));

            for (int i = 0; i < NRAND_WORDS; ++i) {

                int min = (NWORDS / NRAND_WORDS) * i;
                int max = (NWORDS / NRAND_WORDS) * (i+1);

                Wordn = Calculation.getRandNumber(min,max);

                for (int j = min; j < max; ++j) {
                    if(j == Wordn){
                       if( (aux = in.readLine()) == null)
                           throw new IOException("File ended before expected");
                        Words.add(aux);
                    }
                    else
                        if (in.readLine() == null)
                            throw new IOException("File ended before expected");

                }

            }
        }catch(IOException e){
            Words.clear();
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null)
                    in.close();
            }catch(IOException e){
                Words.clear();
                e.printStackTrace();
            }
        }

    }

    public void getNCharsInWords(){
        nCharsInWords = 0;
        for (String word : Words) {
            nCharsInWords += word.length();
        }
    }

    public String GetQuestion(){

        if(nAnswer == 0 && firstQuestion){

            StartWritingTime();
            getNRandomWordsFromFile();
            getNCharsInWords();
        }

        return Words.get(nAnswer);
    }

    @Override
    public MinigameAns InsertAnswer(String answer) {

        if(answer.equals("End"))
        {
            endTurn();
            return MinigameAns.GAVE_UP;
        }

        firstQuestion = false;

        if(WritingTime()>= nCharsInWords/2)
        {
            endTurn();
            return MinigameAns.TIME_OVER;
        }

        if(!Words.get(nAnswer).equals(answer))
            return MinigameAns.WRONG_ANSWER;

        if (nAnswer == 4){
            endTurn();
            return MinigameAns.WON;
        }

        ++nAnswer;
        return MinigameAns.RIGHT_ANSWER;

    }

    private void endTurn(){
        firstQuestion = true;
        Words.clear();
        nAnswer = 0;
    }

    @Override
    public String toString() {
        return "Typing Minigame";
    }

    @Override
    public String Info() {
        if(firstQuestion)
            return this + ": Write 5 random words faster than half of it's characters in seconds and win!";

        return " ";
    }
}



