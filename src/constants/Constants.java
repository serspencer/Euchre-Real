/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package constants;

/**
 *
 * @author Spencer Ross
 */
public class Constants {

    public static final int NUM_AI_PLAYERS = 3;
    public static final int NUM_PLAYERS = 4;
    public static final int NUM_CARDS = 24;
    public static final int NUM_CARDS_DEALT = 5;
    public static final int NUM_ROUNDS_PER_HAND = 5;
    public static final int ONE = 0;
    public static final int TWO = 1;
    public static final int DEAL_ONE = 2;
    public static final int DEAL_TWO = 3;
    public static final int POSITION_1 = 0;
    public static final int POSITION_2 = 1;
    public static final int POSITION_3 = 2;
    public static final int POSITION_4 = 3;
    public static final int MAX_PASSES = 3;
    public static final int MIN_TRUMP = 3;


    public enum Color{
        RED, BLACK
    }

    public enum Suit{
        CLUBS (0), DIAMONDS (1), HEARTS (3), SPADES (2);

        private int rank;

        public int getRank() {
            return rank;
        }

        private Suit(int rank) {
            this.rank = rank;
        }
    }

    public enum FaceValue{
        NINE (9), TEN (10), JACK (11), QUEEN (12), KING (13), ACE (14);

        private int value;

        public int getValue() {
            return value;
        }

        private FaceValue(int value) {
            this.value = value;
        }
    }
}
