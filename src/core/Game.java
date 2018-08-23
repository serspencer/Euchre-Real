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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import constants.Constants;
import userinterface.*;

import javax.swing.*;

public class Game {



    //  Field Variables
    private Card trump;
    private Player lead;
    private Player dealer;
    private Player trickWinner;
    private int round;
    private ArrayList<Team> teams;
    private ArrayList<Player> tableLayout;
    private int currentDealer;
    private int leadPlayer;
    private Deck deck;
    private Scanner scan;
    private GameUi ui;
    private int trumpCheck;
    private Team trumpTeam;

    //Custom Game Constructor
    public Game() {
        createTeams();
//        outputTeams();
        generateDeck();
        setTable();
        dealHand();
        displayHands();
//        play();
    }

    public void newGame() {
        clearHand();
        generateDeck();
        setTable();
        dealHand();
        displayHands();
    }

    private void clearHand() {
        for(Player ele: getTableLayout()) {
            ele.getPlayerHand().clear();
        }
    }

    private void createTeams() {

        teams = new ArrayList<>(2);
        Team TeamOne = new Team();
        Team TeamTwo = new Team();
        TeamOne.setTeamName("1");
        TeamTwo.setTeamName("2");
        getTeams().add(TeamOne);
        getTeams().add(TeamTwo);

        //  Request Human Name
        scan = new Scanner(System.in);
        String name = JOptionPane.showInputDialog("Enter your name");

        //  Create Human Players
        HumanPlayer human = new HumanPlayer();
        human.setName(name);
        human.setGame(this);
        TeamOne.getTeam().add(human);
        System.out.println("Human Player added to Team " + TeamOne.getTeamName());

        //  Create AI Players
        for(int c = 0; c < Constants.NUM_AI_PLAYERS; c++) {
            AiPlayer AI = new AiPlayer();
            AI.setName("AI " + (c+1));
            AI.setGame(this);
            if(/*c+1 % 2 == 0*/c + 1 == Constants.NUM_AI_PLAYERS/2) {
                TeamOne.getTeam().add(AI);
                System.out.println((c + 1) + "Player " + AI.getName() + " added to team " + TeamOne.getTeamName());
            }
            else/*(c + 1 > Constants.NUM_AI_PLAYERS/2)*/ {
                TeamTwo.getTeam().add(AI);
                System.out.println("Player " + AI.getName() + " added to team " + TeamTwo.getTeamName());
            }
        }
    }

    private void outputTeams() {
        //Enhanced For loop
        for( Team element : getTeams())
            element.outputTeams();
    }

    private void generateDeck() {
        deck = new Deck();
    }

    private void setTable() {
        tableLayout = new ArrayList<>();

        Team teamOne = teams.get(Constants.ONE);
        Team teamTwo = teams.get(Constants.TWO);

//      Creates Players at the Table
        Player teamOnePlayerOne = teamOne.getTeam().get(Constants.ONE);
        Player teamOnePlayerTwo = teamOne.getTeam().get(Constants.TWO);
        Player teamTwoPlayerOne = teamTwo.getTeam().get(Constants.ONE);
        Player teamTwoPlayerTwo = teamTwo.getTeam().get(Constants.TWO);



//      Assigns Players Seats
        getTableLayout().add(0, teamOnePlayerOne);
        getTableLayout().add(1, teamTwoPlayerOne);
        getTableLayout().add(2, teamOnePlayerTwo);
        getTableLayout().add(3, teamTwoPlayerTwo);

//      Outputs Players to Counsel
        System.out.println();
        System.out.println("Players at the table are");
        for( Player ele : getTableLayout()) {
            System.out.println("Player: " + ele.getName());
        }

    }

    private void setDealerAndLead() {
        Random rand = new Random();
        currentDealer = rand.nextInt(Constants.NUM_PLAYERS);
        dealer = getTableLayout().get(currentDealer);
        leadPlayer = (currentDealer + 1) % 4;
    }

    private void dealHand() {
        setDealerAndLead();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Dealer is " + dealer.getName());
        System.out.println("-----------------------------------------------------");
        int currentPlayer = leadPlayer;
        Iterator<Card> itr = deck.getCardList().iterator();

//        System.out.println();
//        System.out.println("DEAL One");
//        System.out.println("-----------------------------------------------------");
//      Deal 1
        for( Player element : getTableLayout())
            dealOne(currentPlayer++ % 4, itr);

//        System.out.println();
//        System.out.println("DEAL TWO");
//        System.out.println("-----------------------------------------------------");
//      Deal 2
        for( Player element : getTableLayout())
            dealTwo(currentPlayer++ % 4, itr);

//      Setting Trump
        trump = itr.next();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Trump is the " + trump.getFaceValue() + " of " + trump.getSuit());
        System.out.println("-----------------------------------------------------");
    }

    private void dealOne(int playerIdx, Iterator<Card> itr) {
        for( int c = 0; c < Constants.DEAL_ONE; c++) {
            if(itr.hasNext()) {
                Card newCard = itr.next();

//                System.out.println("Dealing " + newCard.getFaceValue() + " of " + newCard.getSuit() + " to player " + tableLayout.get(playerIdx).getName());

                getTableLayout().get(playerIdx).getPlayerHand().add(newCard);
                itr.remove();
            }
        }
    }

    private void dealTwo(int playerIdx, Iterator<Card> itr) {
        for(int c = 0; c < Constants.DEAL_TWO; c++) {
            if(itr.hasNext()) {
                Card newCard = itr.next();

//                System.out.println("Dealing " + newCard.getFaceValue() + " of " + newCard.getSuit() + " to player " + tableLayout.get(playerIdx).getName());

                getTableLayout().get(playerIdx).getPlayerHand().add(newCard);
                itr.remove();
            }
        }
    }

    private void displayHands() {
        for( Team element : teams)
            element.outputHands();
    }

    public void play() {
        trumpCheck();
//        for ( Player ele : getTableLayout()) {
//            ele.makeTrump();
//        }
    }

    public void trumpCheck() {

        trumpCheck = 0;

        int curr = leadPlayer;

        while(trumpCheck < Constants.NUM_PLAYERS) {

            Player temp = tableLayout.get(curr);

            temp.makeTrump();

            if(temp.getAcceptTrump()) {
                for(Team ele: teams) {
                    if(ele.getTeam().contains(temp)) {
                        trumpTeam = ele;
                        JOptionPane.showMessageDialog(ui.getTablePanel(), "Team " + trumpTeam.getTeamName() + " is trump");
                    }
                }
                break;
            }
            else {
                trumpCheck++;
            }
            curr = (curr + 1) % 4;
        }

    }

    //  GETTERS AND SETTERS
    public Player getLead() {
        return lead;
    }

    public void setLead(Player lead) {
        this.lead = lead;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public Player getTrickWinner() {
        return trickWinner;
    }

    public void setTrickWinner(Player trickWinner) {
        this.trickWinner = trickWinner;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public Card getTrump()
    {
        return trump;
    }

    public void setTrump(Card trump) {
        this.trump = trump;
    }

    public ArrayList<Player> getTableLayout() {
        return tableLayout;
    }

    public void setGameUi(GameUi ui) {this.ui = ui;}

    public GameUi getGameUi() {return ui;}

    public int getTrumpCheck() {return trumpCheck;}

    public void setTrumpCheck(int trump) {this.trumpCheck = trump;}
}
