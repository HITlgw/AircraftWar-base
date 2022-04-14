package edu.hitsz.Data;

import java.util.ArrayList;

public interface Dao {
    ArrayList<Player> getAllData() ;
    void writeAllData(ArrayList<Player> L);
    void writeOne(String name, int score) ;
    void printAll();
    void deleteAll();
    void deleteOne(int rank);

}
