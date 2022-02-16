package game.logic.data.minigame;

import java.io.Serializable;

public enum MinigameAns implements Serializable {

    INVALID_ANSWER {
        @Override
        public String toString() {
            return "Invalid response, please introduce a valid number";
        }
    },WON{
        @Override
        public String toString() {
            return"You won the Minigame and got rewarded with a special piece!";
        }
    },WRONG_ANSWER{
        @Override
        public String toString() {
            return "That was the wrong answer!";
        }
    },TIME_OVER{
        @Override
        public String toString() {
            return "The time was already over, so you lost the minigame!";
        }
    },RIGHT_ANSWER{
        @Override
        public String toString() {
            return "That's the right answer!";
        }
    },
    GAVE_UP{
        public String toString() {
            return "You gave up :(";
        }
    }


}
