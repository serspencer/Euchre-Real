package userinterface;

import constants.Constants;
import core.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

import java.util.ArrayList;


public class AiPlayerUi extends JPanel {

    private AiPlayer ai;
    private int position;
    private ArrayList<JLabel> cards;
    private CardUi cardUi;
    private int height;
    private int width;
    private GameUi gameUi;

    public AiPlayerUi(Player aiPlayer, int pos, GameUi ui){
        this.ai = (AiPlayer)aiPlayer;
        this.position = pos;
        this.gameUi = ui;
        initComponents();
    }

    private void initComponents(){
        this.setMinimumSize(new Dimension(150, 150));

        cards = new ArrayList<>();

        if(position == 2){
//          USE BOX LAYOUT ON X
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setBorder(new TitledBorder(ai.getName()));
            this.setMinimumSize(new Dimension(635, 175));
            this.setPreferredSize(new Dimension(635, 175));
            this.setBackground(Color.WHITE);
            height = 155;
            width = 125;
        }
        else{
//          USE BOX LAYOUT ON Y
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(new TitledBorder(ai.getName()));
            this.setMinimumSize(new Dimension(175, 500));
            this.setPreferredSize(new Dimension(175, 500));
            this.setBackground(Color.WHITE);
            height = 100;
            width = 155;

        }

        displayCards();

    }

    private void displayCards(){

        cards = new ArrayList<>();

        for(int i = 0; i < Constants.NUM_CARDS_DEALT; i++){
            JLabel label = new JLabel();
            cardUi = new CardUi(ai.getPlayerHand().get(i), label, position);
            label = cardUi.getLabel();
            label.setMinimumSize(new Dimension(width, height));
            label.setPreferredSize(new Dimension(width, height));
            label.setMaximumSize(new Dimension(width, height));
//            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            label.setText("Card " + i);

            label.putClientProperty("position", i);
            label.putClientProperty("face", ai.getPlayerHand().get(i).getFaceValue());
            label.putClientProperty("suit", ai.getPlayerHand().get(i).getSuit());

            cards.add(label);
            this.add(label);

        }
    }
}
