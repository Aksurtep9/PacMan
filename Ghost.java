package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class Ghost extends Entity{

    private Image ghostImage;

    private boolean blocked = false;


    public Ghost(int startPoint_X , int startPoint_Y, int sp){
        super(startPoint_X,startPoint_Y, sp);

        String path = new String("C:\\Users\\Petruska Bence\\Documents\\PacMan_HW\\src\\PacMan\\Images\\ghost.gif");
        ghostImage = new ImageIcon(path).getImage();
        req_dx = 1;
        req_dy = 0;
    }

    public void move(){
        //super.move();

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
            blocked = true;
        } else {
            dpos_X = req_dx;
            dpos_Y = req_dy;
        }
        pos_X = pos_X + (speed * dpos_X);
        pos_Y = pos_Y + (speed * dpos_Y);

        directionChoose();

    }

    public void directionChoose() {


        Map map = level.getMap();
        if(pos_X%map.getBLOCK_SIZE()==0 && pos_Y%map.getBLOCK_SIZE()==0) {
            //System.out.println(turnTimer);
            int x = pos_X / map.getBLOCK_SIZE();
            int y = pos_Y / map.getBLOCK_SIZE();
            int upBlock = level.getLevelData(y-1,x);
            int downBlock = level.getLevelData(y + 1,x);
            int rightBlock = level.getLevelData(y,x+1);
            int leftBlock = level.getLevelData(y,x-1);


            //Ha egy fuggoleges folyosón megy a pacman, akkor maradjon rajta.
            if (dpos_Y != 0 && rightBlock == 0 && leftBlock == 0) {
                return;
            }
            //Ha egy vizszintes folyoson megy a pacman, akkor maradjon rajta.
            if (dpos_X != 0 && upBlock == 0 && downBlock == 0) {
                return;
            }


                int temp_req_dx = req_dx;
                int temp_req_dy = req_dy;

                //while(temp_req_dx == req_dx && temp_req_dy == req_dy){
                    int dir = (int) (Math.random() * 3) % 3;
                    switch (dir) {
                        case 0:
                            //Ha egyenesen menne tovább,nem kell semmit semmit se csinálni
                            break;
                        case 1:
                            req_dx = temp_req_dy;
                            req_dy = temp_req_dx;
                            break;
                        case 2:
                            req_dx = -1*temp_req_dy;
                            req_dy = -1*temp_req_dx;
                            break;
                        default:
                            break;
                    }
                //}

            //Ha áll egyhelyben, akkor próbálja meg megfordulni.
            if (blocked) {


                req_dx = -1 * req_dx;
                req_dy = -1 * req_dy;

                blocked = false;
                return;
            }
        }
    }



    public Image getGhostImage() {
        return ghostImage;
    }

    public void setGhostImage(Image ghostImage) {
        this.ghostImage = ghostImage;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
