/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

/**
 *
 * @author beh01
 */
public class Team implements Comparable<Team> {

    private String name;
    private Conference conference;
    private Division division;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Team(String name) {
        this.name = name;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return this.name + ((score!=0)?" - "+score:"");
    }

    public String toFullString() {
        return String.format("%1$30s", this.name) + "\t" + score + "\t" + conference + "\t" + division;
    }

    @Override
    public int compareTo(Team o) {
        return this.getName().compareTo(o.getName());
    }

}
