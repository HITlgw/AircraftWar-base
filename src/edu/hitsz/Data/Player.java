package edu.hitsz.Data;

import java.io.Serializable;

public class Player implements Comparable, Serializable {
    private int Score;
    private String Name;


    public Player(int score, String name) {
        this.Score = score;
        this.Name = name;
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
        return Name+"\t"+Score;
    }
}
