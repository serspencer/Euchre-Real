/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.ArrayList;

/**
 *
 * @author Spencer Ross
 */
public class Team {

    private ArrayList<Player> players;
    private int score;
    private int tricks;
    private String teamName;

//  Instantiate ArrayList<Player> with constructor
    Team() {
        players = new ArrayList<Player>();
    }

    void outputTeams() {
        System.out.println("Team " + teamName + " includes players:");

//      Enhanced For Loop
        for(Player element : players)
            System.out.println("Player: " + element.getName());
    }

    void outputHands() {
//        System.out.println();
//        System.out.println("        Team " + teamName);
        for(Player element : players) {
            element.sortBySuit();
            if (element instanceof HumanPlayer)
                element.displayHand();
        }
    }

//  GETTERS AND SETTERS
    public ArrayList<Player> getPlayer() {
        return players;
    }

    public void setPlayer(ArrayList<Player> players) {
        this.players = players;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTricks() {
        return tricks;
    }

    public void setTricks(int tricks) {
        this.tricks = tricks;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Player> getTeam()
    {
        return players;
    }

    public void setTeam(ArrayList<Player> teamOne)
    {
        this.players = teamOne;
    }

}
