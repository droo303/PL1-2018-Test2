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

    public String getName() {
        return name;
    }

    public Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Team o) {
        return this.getName().compareTo(o.getName());
    }

}
