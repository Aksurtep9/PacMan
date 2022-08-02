package PacMan;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {

    String name;
    int score;


    public Player(String n, int s) {
        name = n;
        score = s;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}


