/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.util.Collections;
import java.util.List;

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
        List<Streak> s = league.computeStreaks();
        Collections.sort(s);
        int longestStreak = s.get(s.size() - 1).getLength();
        for (Streak streak : s) {
            if (streak.getLength() == longestStreak) {
                System.out.println(streak);
            }
        }

    }
}
