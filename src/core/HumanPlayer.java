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
public class HumanPlayer extends Player{

    Game game;

    @Override
    public Card playCard() {
        return null;
    }

    @Override
    public void makeTrump() {

        if(game.getTrumpCheck() == Constants.MAX_PASSES) {
            JOptionPane.showMessageDialog(game.getGameUi().getTablePanel(), "You must accpet trump");
            setAcceptTrump(true);
        }
        else {
            //Simplify
            int temp = JOptionPane.showConfirmDialog(game.getGameUi().getTablePanel(),"Do you accept Trump?");
            if(temp == JOptionPane.YES_OPTION) {
                setAcceptTrump(true);
//                JOptionPane.showMessageDialog(hUi, "You Choose Trump");
            }
            else
                setAcceptTrump(false);
        }
    }

    public void setGame(Game game) { this.game = game;}
}
