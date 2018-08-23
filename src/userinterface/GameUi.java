package userinterface;

import constants.Constants;
import core.*;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;


public class GameUi extends JFrame{

    private final Game game;
    private JFrame frame;
    private JPanel tablePanel;
    private JPanel aiOnePanel;
    private JPanel aiTwoPanel;
    private JPanel hpPanel;
    private JPanel aiThreePanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel scorePanel;
    private JPanel trumpPanel;
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenu helpMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem rulesMenuItem;
    private JLabel teamOneScoreLbl;
    private JLabel teamOneScore;
    private JLabel teamTwoScoreLbl;
    private JLabel teamTwoScore;
    private JLabel trumpCard;

    private ArrayList<Card> cardsPlayed;
    private ArrayList<JLabel> cardsPlayedLbl;

    public GameUi(Game newGame) {
        this.game = newGame;
        initComponents();

        game.setGameUi(this);
        game.play();
    }

//  Adds all components to the JFrame
    private void initComponents(){
        frame = new JFrame("Eurche");
        frame.setSize(1000, 1000);
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setMaximumSize(new Dimension(1000, 1000));
        BorderLayout bLayout = new BorderLayout();
        bLayout.setHgap(5);
        bLayout.setVgap(15);
        frame.setLayout(bLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//      Menu Bar Set-Up
        initMenuBar();

//      Card Panel Set-Up
        initCardPanel();

//      North Panel Set-Up, Contains AI-2, Score, and Trump
        initNorthPanel();

//      South Panel Set-Up, Contains #Trump Cards out, Human Player, Something
        initSouthPanel();

//      Table Set-Up
        initTablePanel();

        //Add Everything to frame
        frame.setJMenuBar(menuBar);
        frame.add(northPanel, BorderLayout.PAGE_START);
        frame.add(aiOnePanel, BorderLayout.LINE_START);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(aiThreePanel, BorderLayout.LINE_END);
        frame.add(southPanel, BorderLayout.PAGE_END);

//      Set Frame Visibility
        frame.pack();
        frame.setVisible(true);
        
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();

        gameMenu = new JMenu("Game");

//  Game Menu Set-Up
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setMnemonic('N');
        newGameMenuItem.addActionListener(new NewGameListener());

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(new ExitListener());

        gameMenu.setMnemonic('G');
        gameMenu.add(newGameMenuItem);
        gameMenu.add(exitMenuItem);
        menuBar.add(gameMenu);

//  Help Menu Set-Up
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(new AboutListener());

        rulesMenuItem = new JMenuItem("Rules");
        aboutMenuItem.setMnemonic('R');

        helpMenu.setMnemonic('H');
        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);
        menuBar.add(helpMenu);

    }

    private void initCardPanel() {
        aiOnePanel = new AiPlayerUi(game.getTableLayout().get(Constants.POSITION_2), Constants.POSITION_2, this);
        aiTwoPanel = new AiPlayerUi(game.getTableLayout().get(Constants.POSITION_3), Constants.POSITION_3, this);
        aiThreePanel = new AiPlayerUi(game.getTableLayout().get(Constants.POSITION_4), Constants.POSITION_4, this);
        hpPanel = new HumanPlayerUi(game.getTableLayout().get(Constants.POSITION_1), this);

        game.getTableLayout().get(Constants.POSITION_1).setUi(hpPanel);
        game.getTableLayout().get(Constants.POSITION_2).setUi(aiOnePanel);
        game.getTableLayout().get(Constants.POSITION_3).setUi(aiTwoPanel);
        game.getTableLayout().get(Constants.POSITION_4).setUi(aiThreePanel);
    }

    private void initNorthPanel() {
        northPanel = new JPanel();
        FlowLayout fLayout = new FlowLayout();
        fLayout.setHgap(7);
        northPanel.setLayout(fLayout);

        initTrumpPanel();
        initScorePanel();

        northPanel.add(scorePanel);
        northPanel.add(aiTwoPanel);
        northPanel.add(trumpPanel);
    }

    private void initSouthPanel() {
        southPanel = new JPanel();
        FlowLayout fLayout = new FlowLayout();
        fLayout.setHgap(7);
        southPanel.setLayout(fLayout);

        JPanel trumpCount = new JPanel();
        trumpCount.setMinimumSize(new Dimension(125, 175));
        trumpCount.setPreferredSize(new Dimension(125, 175));
        trumpCount.setBorder(new TitledBorder("Trump Count"));
        trumpCount.setBackground(Color.BLUE);


        // southPanel.add(trumpCount);
        southPanel.add(hpPanel);
        //southPanel.add(scorePanel);
    }

    private void initTrumpPanel() {
        trumpCard = new JLabel();
        CardUi cardUi = new CardUi(game.getTrump(), trumpCard);
        trumpCard = cardUi.getLabel();

        trumpCard.putClientProperty("face", game.getTrump().getFaceValue());
        trumpCard.putClientProperty("suit", game.getTrump().getSuit());

        trumpPanel = new JPanel();
        trumpPanel.setMinimumSize(new Dimension(125, 175));
        trumpPanel.setPreferredSize(new Dimension(125, 175));
        trumpPanel.setBorder(new TitledBorder("Trump"));
        trumpPanel.setBackground(Color.WHITE);
        trumpPanel.setLayout(new BoxLayout(trumpPanel, BoxLayout.X_AXIS));
        trumpPanel.add(trumpCard);

    }

    private void initScorePanel() {
        scorePanel = new JPanel();
        scorePanel.setMinimumSize(new Dimension(125, 175));
        scorePanel.setPreferredSize(new Dimension(125, 175));
        scorePanel.setBorder(new TitledBorder("Score"));
        scorePanel.setLayout(new GridLayout(2,2));

        teamOneScoreLbl = new JLabel("Team " + game.getTeams().get(Constants.ONE).getTeamName());
        teamOneScoreLbl.setHorizontalAlignment((int)JPanel.CENTER_ALIGNMENT);
        teamOneScore = new JLabel(String.valueOf(game.getTeams().get(Constants.POSITION_1).getScore()));
        teamOneScore.setHorizontalAlignment((int)JPanel.CENTER_ALIGNMENT);
        scorePanel.add(teamOneScoreLbl);
        scorePanel.add(teamOneScore);

        teamTwoScoreLbl = new JLabel("Team " + game.getTeams().get(Constants.TWO).getTeamName());
        teamTwoScoreLbl.setHorizontalAlignment((int)JPanel.CENTER_ALIGNMENT);
        teamTwoScore  = new JLabel(String.valueOf(game.getTeams().get(Constants.POSITION_2).getScore()));
        teamTwoScore.setHorizontalAlignment((int)JPanel.CENTER_ALIGNMENT);
        scorePanel.add(teamTwoScoreLbl);
        scorePanel.add(teamTwoScore);

        scorePanel.add(teamOneScoreLbl);
        scorePanel.add(teamOneScore);
        scorePanel.add(teamTwoScoreLbl);
        scorePanel.add(teamTwoScore);
    }

    private void initTablePanel() {
        tablePanel = new JPanel();
        tablePanel.setBorder(new TitledBorder("Euchre"));
        Color tableGreen = new Color(38, 171, 42);
        BorderLayout tableLayout = new BorderLayout();
//        tableLayout.setHgap(10);
        tableLayout.setVgap(10);
        tablePanel.setLayout(tableLayout);
        tablePanel.setBackground(tableGreen);

        cardsPlayedLbl = new ArrayList<>(4);
        for(int i = 0; i < Constants.NUM_PLAYERS; i++) {
            JLabel tempLbl = new JLabel();

            //add dimension code
            if(i < 2) {
                tempLbl.setPreferredSize(new Dimension(125, 155));
                tempLbl.setMinimumSize(new Dimension(125, 155));
                tempLbl.setMaximumSize(new Dimension(125, 155));
            } else {
                tempLbl.setPreferredSize(new Dimension(155, 125));
                tempLbl.setMinimumSize(new Dimension(155, 125));
                tempLbl.setMaximumSize(new Dimension(155, 125));
            }

            tempLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            CardUi tempUi = new CardUi(tempLbl);
            tempLbl = tempUi.getLabel();
            cardsPlayedLbl.add(tempLbl);
        }

        FlowLayout cardLayout = new FlowLayout();

        JPanel southCardPanel = new JPanel();
        southCardPanel.setLayout(cardLayout);
        southCardPanel.setBackground(tableGreen);
        southCardPanel.add(cardsPlayedLbl.get(0));

        JPanel northCardPanel = new JPanel();
        northCardPanel.setLayout(cardLayout);
        northCardPanel.setBackground(tableGreen);
        northCardPanel.add(cardsPlayedLbl.get(1));

        JPanel westCardPanel = new JPanel();
        westCardPanel.setLayout(cardLayout);
        westCardPanel.setBackground(tableGreen);
        westCardPanel.add(cardsPlayedLbl.get(2));

        JPanel eastCardPanel = new JPanel();
        eastCardPanel.setLayout(cardLayout);
        eastCardPanel.setBackground(tableGreen);
        eastCardPanel.add(cardsPlayedLbl.get(3));

        tablePanel.add(southCardPanel, BorderLayout.PAGE_END);
        tablePanel.add(northCardPanel, BorderLayout.PAGE_START);
        tablePanel.add(westCardPanel, BorderLayout.LINE_START);
        tablePanel.add(eastCardPanel, BorderLayout.LINE_END);
    }

    private void refresh() {
        System.out.println("refresh");


        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        refreshComponents();

        System.out.println("refreshed");


        game.setGameUi(this);
        game.play();

    }

    private void refreshComponents() {
//      Menu Bar Set-Up
        initMenuBar();

//      Card Panel Set-Up
        initCardPanel();

//      North Panel Set-Up, Contains AI-2, Score, and Trump
        initNorthPanel();

//      South Panel Set-Up, Contains #Trump Cards out, Human Player, Something
        initSouthPanel();
//        hpPanel.setLayout(new FlowLayout());

//      Table Set-Up
        initTablePanel();

        //Add Everything to frame
        frame.setJMenuBar(menuBar);
        frame.add(northPanel, BorderLayout.PAGE_START);
        frame.add(aiOnePanel, BorderLayout.LINE_START);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(aiThreePanel, BorderLayout.LINE_END);
        frame.add(southPanel, BorderLayout.PAGE_END);

//      Set Frame Visibility
        frame.pack();
        frame.setVisible(true);


    }

//    public void addToTable(int cardNum) {
//
//        JLabel tmpLbl = cardsPlayedLbl.get(0);
//        CardUi tmp = new CardUi(game.getTableLayout().get(0).getPlayerHand().get(cardNum), tmpLbl);
//        tmpLbl = tmp.getLabel();
//        cardsPlayedLbl.set(0, tmpLbl);
//        game.getTableLayout().get(0).getPlayerHand().remove(cardNum);
//
//        tablePanel.revalidate();
//        tablePanel.repaint();
//    }

//  Listener for Exit Confirm
    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int response = JOptionPane.showConfirmDialog(frame, "Confirm to exit Euchre?",
            "Exit?", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

//  Listener for About
    private class AboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Euchre version 2.0.1\nSpencer Ross\nSummer 2018");
        }
    }

//  Listener for NewGame
    private class NewGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int response = JOptionPane.showConfirmDialog(frame, "Confirm new game",
                    "Exit?", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                game.newGame();
                refresh();
            }
        }
    }

    public JPanel getTablePanel() { return tablePanel;}
}