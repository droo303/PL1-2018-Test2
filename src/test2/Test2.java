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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        League league = League.loadData();
       // league.printResult();


       for (Game game : league.getGames()){
           
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
        for (Map.Entry<String,List<String>> set : divisions.entrySet()){
          System.out.println(set.getKey());
          System.out.println(set.getValue()); 
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
        
        public static void TeamGame(){
            String team;
            Integer score;
            String division;
        }
    }
