package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Pacman_Game extends JFrame {


    private JFrame Game;
    private JFrame Scoreboard;
    private JPanel Menu;
    private Level level = null;


    private Image icon;

    public Pacman_Game(Level l){


        level = l;

        this.setTitle("PacMan");

        try{
            icon = new ImageIcon("C:\\Users\\Petruska Bence\\Documents\\PacMan_HW\\src\\PacMan\\Images\\icon.jpg").getImage();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        setIconImage(icon);

        Menu = new JPanel();

        JTextField name = new JTextField(20);
        JButton save = new JButton();
        JButton play = new JButton();
        JButton scoreboard = new JButton();

        //Menu.setVisible(true);

        name.setText("PLAYER");
        name.setEditable(true);


        save.setText("Save!");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!name.getText().equals("PLAYER")) {
                    level.setPlayer(name.getText());
                }
            }
        });

        play.setText("Start Game");
        //play.setSize(500,500);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Game = new Level(new Map());
                Game = new JFrame();
                Game.add(level);
                Game.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                Game.setTitle("Pacman Game");
                Game.setSize(level.getDimension().width+10, level.getDimension().height+36);
                level.setInGame(true);
                Game.setVisible(true);


            }

        });

        scoreboard.setText("Scoreboard");
        //scoreboard.setSize(200,200);
        scoreboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scoreboard = new JFrame();
                JTable table = new JTable(level.getScores());
                Scoreboard.add(new JScrollPane(table), BorderLayout.CENTER);
                Scoreboard.setSize(new Dimension(300,200));
                Scoreboard.setVisible(true);
                Scoreboard.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

        JPanel namePanel = new JPanel();
        namePanel.setSize(200,200);
        namePanel.add(name, BorderLayout.WEST);
        namePanel.add(save, BorderLayout.EAST);
        Menu.add(namePanel, BorderLayout.CENTER);

        JPanel startPanel = new JPanel();
        startPanel.add(play, BorderLayout.CENTER);
        startPanel.setSize(100,100);
        Menu.add(startPanel,BorderLayout.CENTER);

        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.add(scoreboard, BorderLayout.CENTER);
        scoreboardPanel.setSize(100,100);
        Menu.add(scoreboardPanel, BorderLayout.SOUTH);

        this.add(Menu);

        this.setVisible(true);

        this.setSize(400, 400);
        this.setBackground(Color.BLACK);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        Pacman_Game g = new Pacman_Game(new Level(new Map()));
    }
}
