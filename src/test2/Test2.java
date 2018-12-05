/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author beh01
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        League league = League.loadData();
        league.computeScore();
        ArrayList<Team> playoff = new ArrayList<>();
        for (Division d : Division.values()) {
            List<Team> result = league.getTeams().stream().filter(team -> team.getDivision() == d).sorted((Team o1, Team o2) -> {
                return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
            }).collect(Collectors.toList());
            System.out.println("Division: "+d);
            
            for (int i = 0; i < 3; i++) {
                playoff.add(result.get(i));
                System.out.println("\t"+result.get(i));
            }
        }
        for (Conference c : Conference.values()) {
            List<Team> result = league.getTeams().stream().filter(team -> team.getConference()==c).sorted((Team o1, Team o2) -> {
                return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
            }).collect(Collectors.toList());
            System.out.println("Wild card: "+c);
            int i =0;
            for (int j = 0; j < 2; j++) {
                while(playoff.contains(result.get(i))) {
                    i++;
                }
                playoff.add(result.get(i));
                System.out.println("\t"+result.get(i));
            }
        }
    }
}
