/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.util.LinkedList;

/**
 *
 * @author beh01
 */
public class Team implements Comparable<Team> {

    private String name;
    private Conference conference;
    private Division division;
    private LinkedList<Streak> streaks = new LinkedList<>();

    public String getName() {
        return name;
    }

    public LinkedList<Streak> getStreaks() {
        return streaks;
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

    public Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String toFullString() {
        return String.format("%1$30s", this.name) + "\t" + conference + "\t" + division + "\n";
    }

    @Override
    public int compareTo(Team o) {
        return this.getName().compareTo(o.getName());
    }

    public void AddToStreak(ResultType type, Team opponent) {
        Streak s = null;
        if (streaks.size() > 0) {
            s = streaks.getLast();
        }
        if (s == null || s.getType() != type) {
            streaks.add(new Streak(this, type, opponent));
        } else {
            s.addTeam(opponent);
        }
    }
}
