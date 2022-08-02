package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level extends JPanel implements ActionListener {

    private Map map;

    private PlayersData scores;

    private int ghost_number = 4;

    private boolean inGame = false;
    private Dimension dimension;
    private int maxScore = 0;

    private Ghost ghosts[] = new Ghost[ghost_number];
    private PacMan pacman;

    private Timer timer;

    private int levelData[][] = new int[20][20];

    private String player = "";


    public Level(Map m) {
        map = m;

        initLeveldata();

        dimension = new Dimension(480, 480);
        timer = new Timer(40, this);
        timer.start();
        pacman = new PacMan(3*map.getBLOCK_SIZE(),7*map.getBLOCK_SIZE(),4);
        pacman.setLevel(this);
        for(int i = 0; i < ghost_number; i++) {
            ghosts[i] = new Ghost((1+i) * map.getBLOCK_SIZE(), 1 * map.getBLOCK_SIZE(), 4);
            ghosts[i].setLevel(this);
        }
        addKeyListener(pacman);
        scores = new PlayersData();
        this.setFocusable(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void drawMap(Graphics2D g) {
        for (int y = 0; y < map.getBLOCK_N(); y++) {
            for (int x = 0; x < map.getBLOCK_N(); x++) {
                if (levelData[y][x] == 0) {
                    g.setColor(new Color(0,0,255));
                    g.fillRect(x*map.getBLOCK_SIZE(), y*map.getBLOCK_SIZE(), map.getBLOCK_SIZE(), map.getBLOCK_SIZE());
                }
                if (levelData[y][x] == 1) {
                    g.setColor(new Color(255, 255, 255));
                    g.fillOval(x*map.getBLOCK_SIZE() + 10, y*map.getBLOCK_SIZE() + 10, 7, 7);
                }
            }
        }
    }

    public void drawPacMan(Graphics2D g) {
        g.drawImage(pacman.getPacmanIm(), pacman.getPos_X(), pacman.getPos_Y() , this);
    }

    public void drawGhost(Graphics2D g) {
        for(int i = 0; i < ghost_number; i++) {
            g.drawImage(ghosts[i].getGhostImage(), ghosts[i].getPos_X(), ghosts[i].getPos_Y(), this);
        }
    }

    public void drawScoreandLife(Graphics2D g){
        g.setColor(new Color(255,255,73));
        g.setFont(new Font("Arial", 1, 22));
        String sal = "Score: " + pacman.getScore() + " Life: " + pacman.getLives();
        g.drawString(sal, 10,map.getBLOCK_SIZE()*map.getBLOCK_N()-4);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, dimension.width, dimension.height);

        drawMap(g2d);
        drawPacMan(g2d);
        drawGhost(g2d);

        //drawTest(g2d);
        drawScoreandLife(g2d);

        playGame(g2d);

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    public void startGame(){
        pacman.setPos_X(pacman.getStart_pos_X());
        pacman.setPos_Y(pacman.getStart_pos_Y());
        for(int i = 0; i < ghost_number; i++){
            ghosts[i].setPos_X(ghosts[i].getStart_pos_X());
            ghosts[i].setPos_Y(ghosts[i].getStart_pos_Y());
        }
    }

    public void playGame(Graphics2D g){
        if(inGame == true) {
            movePacMan();
            moveGhosts();
        }
    }

    public void restartGame(){
        pacman.setLives(3);
        pacman.setDead(false);
        inGame = true;
        pacman.setScore(0);
        initLeveldata();
        startGame();
    }


    public void endGame(){
        inGame = false;

        if(!player.isEmpty()) {
            scores.add(player, pacman.getScore());
            scores.save();
        }

        int reply = JOptionPane.showConfirmDialog(this,
                "You lost the game! Would you like to restart?", "GAME OVER" , JOptionPane.YES_NO_OPTION
        );

        if(reply == JOptionPane.YES_NO_OPTION){
            restartGame();
        }
        else{
            System.exit(0);
        }
    }

    public void winGame(){
        inGame = false;

        if(!player.isEmpty()) {
            scores.add(player, pacman.getScore());
            scores.save();
        }
        int reply = JOptionPane.showConfirmDialog(this,
                "CONGRATS, YOU HAVE WON! Would you like to play again??", "WINNER" , JOptionPane.YES_NO_OPTION
        );

        if(reply == JOptionPane.YES_NO_OPTION){
            restartGame();
        }
        else{
            System.exit(0);
        }

    }

    public void movePacMan(){
        pacman.move();
        pacman.eat();
        killPacman();
        if(pacman.getScore() == maxScore){
            winGame();
        }
    }

    public void moveGhosts(){
        for(int i = 0; i < ghost_number; i++){
            ghosts[i].move();
        }
    }

    public void killPacman(){
        for(int i = 0; i< ghost_number; i++) {
            int ghost_x = ghosts[i].getPos_X() / map.getBLOCK_SIZE();
            int ghost_y = ghosts[i].getPos_Y() / map.getBLOCK_SIZE();
            int pacman_x = pacman.getPos_X() / map.getBLOCK_SIZE();
            int pacman_y = pacman.getPos_Y() / map.getBLOCK_SIZE();
            if (ghost_x == pacman_x && ghost_y == pacman_y) {
                pacman.die();
                if(pacman.isDead() == true) {
                    endGame();
                }
                if(pacman.isDead() == false){
                    startGame();
                }
            }
        }
    }




    public void setField(int x, int y, int value){
        levelData[y][x] = value;
    }



    public Map getMap() {
        return map;
    }

    public int getLevelData(int y, int x) {
        return levelData[y][x];
    }

    public void initLeveldata(){
        maxScore = 0;
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                levelData[y][x] = map.getMapData(y,x);
                if(map.getMapData(y,x) == 1){
                    maxScore++;
                }
            }
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public PlayersData getScores() {
        return scores;
    }

    public void setScores(PlayersData scores) {
        this.scores = scores;
    }


    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public void setGhosts(Ghost[] ghosts) {
        this.ghosts = ghosts;
    }

    public PacMan getPacman() {
        return pacman;
    }

    public void setPacman(PacMan pacman) {
        this.pacman = pacman;
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getGhost_number() {
        return ghost_number;
    }
}

