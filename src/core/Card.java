/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author Spencer Ross
 */

import constants.Constants.Color;
import constants.Constants.Suit;
import constants.Constants.FaceValue;

public class Card {

    private FaceValue Face;
    private Suit Suit;
    private Color Color;

    // USE HASHSET CODE SAMPLE
    public int hashCode() {
        int hashcode = 0;
        hashcode += Face.hashCode();
        hashcode += Suit.hashCode();
        hashcode += Color.hashCode();
        return hashcode;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Card) {
            Card c = (Card) obj;
            return (c.Face.equals(this.Face) && c.Suit.equals(this.Suit) && c.Color.equals(this.Color));
        }
        return false;
    }

//  GETTERS AND SETTERS
    public FaceValue getFaceValue() {
        return Face;
    }

    public void setFaceValue(FaceValue faceValue) {
        this.Face = faceValue;
    }

    public Suit getSuit() {
        return Suit;
    }

    public void setSuit(Suit Suit) {
        this.Suit = Suit;
    }

    public Color getColor() {
        return Color;
    }

    public void setColor(Color Color) {
        this.Color = Color;
    }

}
