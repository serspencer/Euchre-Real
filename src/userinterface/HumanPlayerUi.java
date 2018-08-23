package userinterface;

import constants.Constants;
import core.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HumanPlayerUi extends JPanel {

    private HumanPlayer human;
    private ArrayList<JButton> cards;
    private CardUi cardUi;
    GameUi gameUi;

    HumanPlayerUi(Player human, GameUi ui){
        this.human = (HumanPlayer)human;
        this.gameUi = ui;
        initComponents();
    }

    private void initComponents(){
        this.setBorder(new TitledBorder(human.getName()));
        this.setMinimumSize(new Dimension(635, 175));
        this.setPreferredSize(new Dimension(635, 175));
        this.setBackground(Color.WHITE);

        cards = new ArrayList<>();

//      Set Box Layout on X-Axis
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        displayCards();
    }

    private void displayCards(){

        cards = new ArrayList<>();

        for(int i = 0; i < Constants.NUM_CARDS_DEALT; i++){
            JButton cardButton = new JButton();
            cardUi = new CardUi(human.getPlayerHand().get(i), cardButton);
            cardButton = cardUi.getButton();
//            cardButton.setText("Card" + i);

            cardButton.setMinimumSize(new Dimension(125, 155));
            cardButton.setPreferredSize(new Dimension(125, 155));
            cardButton.setMaximumSize(new Dimension(125, 155));
            cardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cardButton.putClientProperty("position", i);
            cardButton.putClientProperty("face", human.getPlayerHand().get(i).getFaceValue());
            cardButton.putClientProperty("suit", human.getPlayerHand().get(i).getSuit());

            cardButton.addActionListener(new CardListener());

            cards.add(cardButton);
            this.add(cardButton);


        }
    }

    private class CardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            for(int i = 0; i < cards.size(); i++) {
                if(ae.getSource() == cards.get(i)) {
                    cards.remove(i);
                    remove(i);
//                    gameUi.addToTable(i);
                }
            }

            System.out.println("Card " + " Clicked");
            revalidate();
            repaint();
            System.out.println("Refresh");
        }
    }
}
