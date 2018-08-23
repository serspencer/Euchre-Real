/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.*;

import constants.Constants;
import constants.Constants.*;


/**
 *
 * @author Spencer Ross
 */
public class Deck {

    private Set<Card> deck;
    private List<Card> cardList;

    Deck() {
        generateDeck();
        displayDeck();
        shuffleDeck();
        displayDeck();
        shuffleDeck();
        displayCardList();
    }

    private void generateDeck() {
        setDeck(new HashSet<>(Constants.NUM_CARDS));

        for(Constants.FaceValue face : Constants.FaceValue.values()) {
            for(Constants.Suit suit : Constants.Suit.values()) {
                Card newCard = new Card();
                newCard.setFaceValue(face);
                newCard.setSuit(suit);
                if(newCard.getSuit() == Suit.DIAMONDS || newCard.getSuit() == Suit.HEARTS)
                    newCard.setColor(Constants.Color.RED);
                else
                    newCard.setColor(Constants.Color.BLACK);

                if(!getDeck().contains(newCard))
                    getDeck().add(newCard);
            }
        }
    }

    private void displayDeck() {
        System.out.println();
        for(Card element : getDeck())
            System.out.println("Face Value: " + element.getFaceValue() + " Suit: " + element.getSuit() + " Color: "+ element.getColor());
    }

    private void shuffleDeck() {
        cardList = new ArrayList<Card>(getDeck());

        Collections.shuffle(cardList);
        System.out.println();

        setDeck(new HashSet<Card>(cardList));
    }

    private void displayCardList() {
        for( Card element : cardList) {
            System.out.println("Suit: " + element.getSuit() + " Face Value: " + element.getFaceValue() + " Color: " + element.getColor());
        }
    }

//  GETTERS AND SETTERS
    public Set<Card> getDeck() {
        return deck;
    }

    public void setDeck(Set<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
