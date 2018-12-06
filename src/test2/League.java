/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author beh01
 */
public class League {

    private final List<Game> games = new ArrayList<>();
    private final Set<Team> teams = new TreeSet<>();

    private League() {

    }

    private Team lookForTema(String teamName) {
        for (Team t : teams) {
            if (t.getName().equals(teamName)) {
                return t;
            }
        }
        Team result = new Team(teamName);
        teams.add(result);
        return result;
    }

    public List<Game> getGames() {
        return games;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public List<Streak> computeStreaks() {
        for (Game game : games) {
            Team winner = null;
            Team loser = null;
            if (game.getHomeTeam().getScore() > game.getVisitors().getScore()) {
                winner = game.getHomeTeam().getTeam();
                loser = game.getVisitors().getTeam();
            } else if (game.getHomeTeam().getScore() < game.getVisitors().getScore()) {
                winner = game.getVisitors().getTeam();
                loser = game.getHomeTeam().getTeam();
            } else {
                throw new RuntimeException("Error in data");
            }
            winner.AddToStreak(ResultType.W, loser);
            if (game.getNote() != null) {
                loser.AddToStreak(ResultType.OT, winner);
            }else {
                loser.AddToStreak(ResultType.L, winner);
            }
        }
        List<Streak> result = new ArrayList<>();
        for(Team team : teams) {
            result.addAll(team.getStreaks());
        }
        return result;
    }

    private static Result readResult(League league, Scanner s) {
        String team = "";
        String x = s.next();
        boolean first = true;
        while (!x.matches("[0-9]+")) {
            if (first) {
                first = false;
            } else {
                team += " ";
            }
            team += x;
            x = s.next();
        }
        return new Result(league.lookForTema(team), Integer.parseInt(x));
    }

    public static League loadData() {
        League league = new League();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");
        try {
            Scanner input = new Scanner(new File("schedule.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Scanner s = new Scanner(line);
                LocalDate date = LocalDate.parse(s.next(), dateFormat);
                Result home = readResult(league, s);
                Result visit = readResult(league, s);
                String nextString = s.next();
                Note note = null;
                if (nextString.equals("OT")) {
                    note = Note.OVERTIME_VICTORY;
                    nextString = s.next();
                }
                if (nextString.equals("SO")) {
                    note = Note.SHOOTOUT;
                    nextString = s.next();
                }
                nextString = nextString.replace(",", "");
                int numberOfvisitors = Integer.parseInt(nextString);
                LocalTime l = LocalTime.parse(s.next(), timeFormat);
                league.getGames().add(new Game(date, home, visit, numberOfvisitors, l, note));
            }
            input = new Scanner(new File("league.txt"));
            for (int i = 0; i < 2; i++) {
                Conference conference = Conference.valueOf(input.nextLine().trim());
                for (int j = 0; j < 2; j++) {
                    Division division = Division.valueOf(input.nextLine().trim());
                    String team = (input.hasNextLine()) ? input.nextLine().trim() : "";
                    while (!team.equals("")) {
                        Team t = league.lookForTema(team);
                        t.setConference(conference);
                        t.setDivision(division);
                        team = (input.hasNextLine()) ? input.nextLine().trim() : "";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return league;
    }
}
