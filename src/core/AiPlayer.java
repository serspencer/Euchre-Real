/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import constants.Constants;
import userinterface.*;

import javax.swing.*;

/**
 *
 * @author Spencer Ross
 */
public class AiPlayer extends Player{

    Game game;

    @Override
    public Card playCard() {
        return null;
    }

    @Override
    public void makeTrump() {

        AiPlayerUi aiUi = (AiPlayerUi)getUi();

        if(game.getTrumpCheck() == Constants.MAX_PASSES) {
            JOptionPane.showMessageDialog(aiUi, "You must accept trump");
            setAcceptTrump(true);
        }
        else {
            int trumpCount = 0;
            for(Card ele : this.getPlayerHand()) {
                if(ele.getSuit() == game.getTrump().getSuit() || (ele.getColor() == game.getTrump().getColor() && ele.getFaceValue() == Constants.FaceValue.JACK && ele.getSuit() != game.getTrump().getSuit())) {
                    trumpCount++;
                }
            }
            if(trumpCount >= Constants.MIN_TRUMP) {
                setAcceptTrump(true);
                JOptionPane.showMessageDialog(aiUi, "Player " + getName() + " has picked it up!");
            }
            else {
                setAcceptTrump(false);
                JOptionPane.showMessageDialog(aiUi, getName() + " has passed!");
            }
        }
    }

    public void setGame(Game game) {this.game = game;}
}
