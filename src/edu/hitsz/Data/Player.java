package edu.hitsz.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Player implements Comparable, Serializable {
    private int Score;
    private String Name;
    private LocalDateTime localDateTime;

    public String getScore() {
        return String.valueOf(Score);
    }

    public String getName() {
        return Name;
    }

    public String getLocalDateTime() {
        return ""+localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+"  "+localDateTime.getHour()+":"+localDateTime.getMinute();
    }

    public Player(int score, String name, LocalDateTime localDateTime) {
        this.Score = score;
        this.Name = name;
        this.localDateTime=localDateTime;
    }


    @Override
    public int compareTo(Object o) {
        if (this.Score>((Player)o).Score) {
            return -1;
        }
        else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return Name+","+Score+","+
                localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+
                " "+localDateTime.getHour()+":"+localDateTime.getMinute();
    }
    public void printSelf(){
        System.out.printf("%s,%4d,%02d-%02d %02d:%02d",Name,Score,localDateTime.getMonthValue(),localDateTime.getDayOfMonth(),localDateTime.getHour(),localDateTime.getMinute());
    }

//    public static void main(String[] args) {
//        Player testplayer = new Player(111,"123123",LocalDateTime.now());
//        testplayer.printSelf();
//    }
}
