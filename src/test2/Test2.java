    /*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
    */
    package test2;

import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.LinkedHashMap;
import java.util.LinkedList;
    import java.util.List;
    import java.util.Map;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    /**
    *
    * @author beh01
    */
    public class Test2 {

    private static Map<String, List<String>> divisions = new LinkedHashMap<>();
    private static Map<String, Integer> score = new LinkedHashMap<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        League league = League.loadData();
       // league.printResult();

       
       for (Game game : league.getGames()){
           Result first = null, second = null;
           Integer winner = 2,loser = 0;
           if (game.getHomeTeam().getScore() >= game.getVisitors().getScore()){
               first = game.getHomeTeam();
               second = game.getVisitors();
           } else if (game.getHomeTeam().getScore() < game.getVisitors().getScore()){
               first = game.getVisitors();
               second = game.getHomeTeam();
           }
           if (game.getNote() == Note.OVERTIME_VICTORY || game.getNote() == Note.SHOOTOUT){
               loser++;
           }
           addScore(first.getTeam().getName(),winner);
           addScore(second.getTeam().getName(),loser);
       }
 
  


        try{
        String division = null;
        File file = new File("league.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line, data;
        while((line = br.readLine()) != null){
            data = line.trim();
            if (data.isEmpty()){
                continue;
            }
            if (line.startsWith("    ")){
                addDivisionTeam(division,data);
            } else if (line.startsWith("  ")){
                division = data;
            }
            
        }
       /* for (Map.Entry<String,List<String>> set : divisions.entrySet()){
          System.out.println(set.getKey());
          System.out.println(set.getValue()); 
        }
        */
      List<TeamScore> teams = new LinkedList<>();
      
      for (Map.Entry<String,List<String>> set : divisions.entrySet()){
          for (String s : set.getValue()){
              teams.add(new TeamScore(s,0,set.getKey()));
          }
      }
      
      for (Map.Entry<String,Integer> sc : score.entrySet()){
          for (TeamScore team : teams){
              if (team.team.equals(sc.getKey())){
                  team.score = sc.getValue();
              }
          }
      }
      
      for (TeamScore t : teams){
        System.out.println(t.team +" "+ t.score);  
      }
      
    }
    catch (FileNotFoundException e){
        } catch (IOException ex) {
            Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        public static void addDivisionTeam(String division, String data){
            if(!divisions.containsKey(division)){
                divisions.put(division,new LinkedList<>());
            }
            divisions.get(division).add(data);
        }
        
        public static void addScore(String team,Integer add){
            if (!score.containsKey(team)){
                score.put(team, add);
            } else {
                score.replace(team, score.get(team)+add);
            }
        }
        
        public static class TeamScore{
            String team;
            Integer score;
            String division;
           TeamScore(String team,Integer score,String division){
               this.team = team;
               this.score = score;
               this.division = division;
           }
        }
    }
