/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

import javax.swing.JOptionPane;
import core.Game;
import userinterface.GameUi;

/**
 *
 * @author Spencer Ross
 */
public class Euchre {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Euchre!");
        
        JOptionPane.showMessageDialog(null, "Let's Play Euchre!");
        
        Game newGame = new Game();
        GameUi ui = new GameUi(newGame);

    }
    
}
