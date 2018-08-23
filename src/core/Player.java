/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import userinterface.GameUi;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Spencer Ross
 */
public abstract class Player implements IPlayer{

    private String name;
    private ArrayList<Card> playerHand;
    private JPanel ui;
    boolean acceptTrump;

    Player() {
        playerHand = new ArrayList<Card>();
    }

    void displayHand() {
        System.out.println();
        System.out.println("Player " + name + "'s hand");
        for( Card element : playerHand)
            System.out.println("Face: " + element.getFaceValue() + " Suit: " + element.getSuit());
    }

    void sortBySuit() {
        ArrayList<Card> sortedHand = new ArrayList<Card>();

        while( playerHand.size() > 0) {
            int currentPos = 0;
            Card firstCard = playerHand.get(currentPos);

            for(int i = 1; i < playerHand.size(); i++) {
                Card nextCard = playerHand.get(i);

//              Compare if First Card is > than next Card
                if(firstCard.getSuit().getRank() > nextCard.getSuit().getRank()) {
                    currentPos = i;
                    firstCard = nextCard;
                }
                if(firstCard.getSuit().getRank() == nextCard.getSuit().getRank()) {
                    if(firstCard.getFaceValue().getValue() > nextCard.getFaceValue().getValue()) {
                        currentPos = i;
                        firstCard = nextCard;
                    }
                }
            }

            playerHand.remove(currentPos);
            sortedHand.add(firstCard);
        }
        playerHand = sortedHand;
    }

//  GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public JPanel getUi() {
        return ui;
    }

    public void setUi(JPanel ui) {
        this.ui = ui;
    }

    public boolean getAcceptTrump() {return acceptTrump;}

    public void setAcceptTrump(boolean accept) {this.acceptTrump = accept;}

}
