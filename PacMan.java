package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PacMan extends Entity implements KeyListener {

    //private int req_dx, req_dy;


    private Image pacmanIm;
    private Image direction[] = new Image[4];

    private int score = 0;

    private int lives = 0;

    private boolean dead = false;

    public PacMan(int startPoint_X , int startPoint_Y, int sp){
        super(startPoint_X,startPoint_Y, sp);
        String path = new String("C:\\Users\\Petruska Bence\\Documents\\PacMan_HW\\src\\PacMan\\Images\\pacman.gif");
        pacmanIm = new ImageIcon(path).getImage();
        loadImages();
        lives = 3;
    }
    public void move(){

        Map map = level.getMap();


        int dir_x = (pos_X  + req_dx * map.getBLOCK_SIZE());
        int dir_y = (pos_Y  + req_dy * map.getBLOCK_SIZE());
        int x = 0;
        int y = 0;

        int BLOCK_MOVEMENT = map.getBLOCK_SIZE() - 3;

        if(req_dx <= 0 && req_dy <= 0) {
            x = (int) ((dir_x - req_dx * BLOCK_MOVEMENT) / map.getBLOCK_SIZE());
            y = (int) ((dir_y - req_dy * BLOCK_MOVEMENT) / map.getBLOCK_SIZE());
        }
        if(req_dx > 0 || req_dy > 0){
            x = (int) ((dir_x ) / map.getBLOCK_SIZE());
            y = (int) ((dir_y ) / map.getBLOCK_SIZE());
        }
        int down_Y = (int)(dir_y + 18)/map.getBLOCK_SIZE();
        int right_X = (int)(dir_x + 18)/map.getBLOCK_SIZE();

        boolean verticalFree = true;
        boolean horizontalFree = true;

        if(req_dx != 0) {
            verticalFree = level.getLevelData(down_Y, x) != 0;
        }
        if(req_dy != 0) {
            horizontalFree = level.getLevelData(y, right_X) != 0;
        }
        if (level.getLevelData(y,x) == 0 || !verticalFree || !horizontalFree){
            dpos_X = 0;
            dpos_Y = 0;
        } else {
            dpos_X = req_dx;
            dpos_Y = req_dy;
        }
        pos_X = pos_X + speed * dpos_X;
        pos_Y = pos_Y + speed * dpos_Y;


    }

    public void eat(){
        int x = 0;
        int y = 0;


        if(req_dx > 0 || req_dy > 0) {
            x = pos_X/ level.getMap().getBLOCK_SIZE();
            y = pos_Y/ level.getMap().getBLOCK_SIZE();
        }
        if(req_dx < 0){
            x = (pos_X+12)/ level.getMap().getBLOCK_SIZE();
            y = pos_Y/ level.getMap().getBLOCK_SIZE();
        }
        if(req_dy < 0){
            x = pos_X/ level.getMap().getBLOCK_SIZE();
            y = (pos_Y+12)/ level.getMap().getBLOCK_SIZE();
        }

        if (level.getLevelData(y,x) == 1)
        {
            level.setField(x,y,2);
            score++;
        }
    }

    public void die(){
        lives = lives-1;
        if(lives == 0){
            dead = true;
        }
    }

    public void loadImages(){
        String dir[] = {"up", "down", "left", "right"};
        for(int i = 0; i < dir.length; i++){
            String path = new String("C:\\Users\\Petruska Bence\\Documents\\PacMan_HW\\src\\PacMan\\Images\\"+dir[i]+".gif");
            direction[i] = new ImageIcon(path).getImage();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                req_dx = -1;
                req_dy = 0;
                pacmanIm = direction[2];
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                req_dx = 1;
                req_dy = 0;
                pacmanIm = direction[3];
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                req_dx = 0;
                req_dy = -1;
                pacmanIm = direction[0];
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                req_dx = 0;
                req_dy = 1;
                pacmanIm = direction[1];
            }
        }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getReq_dx() {
        return req_dx;
    }

    public void setReq_dx(int req_dx) {
        this.req_dx = req_dx;
    }

    public int getReq_dy() {
        return req_dy;
    }

    public void setReq_dy(int req_dy) {
        this.req_dy = req_dy;
    }

    public Image getPacmanIm() {
        return pacmanIm;
    }

    public void setPacmanIm(Image pacmanIm) {
        this.pacmanIm = pacmanIm;
    }

    public Image[] getDirection() {
        return direction;
    }

    public void setDirection(Image[] direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
