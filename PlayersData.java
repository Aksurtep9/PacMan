package PacMan;

import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class PlayersData extends AbstractTableModel {

    static ArrayList<Player> players ;

    public PlayersData(){
        try {
            players = new ArrayList<>();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("players.dat"));
            players = (ArrayList<Player>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("players.dat"));
            oos.writeObject(players);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void add(String name, int score){
        Player player = new Player(name, score);
        players.add(player);
        players.sort(playerComparator);
    }

    public void remove(int index){
        players.remove(index);
    }

    public static Comparator<Player> playerComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            int a = p2.getScore()-p1.getScore();
            return a;
        }
    };

    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = players.get(rowIndex);
        switch (columnIndex){
            case 0:
                return player.getName();
            default:
                return player.getScore();
        }
    }

    @Override
    public String getColumnName(int i)
    {
        switch(i)
        {
            case 0: return "Player";
            default: return "Score";
        }
    }


}
