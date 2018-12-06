/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beh01
 */
public class Streak implements Comparable<Streak>{

    private ResultType type;
    private List<Team> teams;
    private Team source;

    public Streak(Team source, ResultType type, Team opponent) {
        this.type = type;
        this.source = source;
        this.teams = new ArrayList<>();
        this.teams.add(opponent);
    }

    public int getLength() {
        return teams.size();
    }

    public Team getSource() {
        return source;
    }

    public ResultType getType() {
        return type;
    }

    public List<Team> getTeams() {
        return teams;
    }

    void addTeam(Team opponent) {
        teams.add(opponent);

    }

    @Override
    public String toString() {
        return source.toFullString() + " " + type.toString() + getLength() + teams;
    }

    @Override
    public int compareTo(Streak o) {
        return Integer.valueOf(this.getLength()).compareTo(o.getLength());
    }

}
