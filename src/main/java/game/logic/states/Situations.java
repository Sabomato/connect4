package game.logic.states;

public enum Situations {
    START{
        @Override
        public String toString() {
            return "";
        }
    },HUMAN_OR_VIRTUAL{
        @Override
        public String toString() {
            return "Choose if you want virtual, human or both players.";
        }
    }
    ,PLAYER_NAMES{
        @Override
        public String toString() {
            return "Choose each player's name( they can't be the same).";
        }
    }, WAIT_PLAY_HUMAN {
        @Override
        public String toString() {
            return "Play a normal piece, a special piece(if you have one) or use credits to undo a play.";
        }
    },WAIT_PLAY_VIRTUAL{
        @Override
        public String toString() {
            return "Virtual Player playing";
        }
    }
    ,PLAY_MINIGAME, PLAY_REPLAY{
        @Override
        public String toString() {
            return "Choose to replay one of 5 last games.";
        }
    },GAME_WON{
        @Override
        public String toString() {
            return "The game is over, go back to start menu.";
        }
    }
}
