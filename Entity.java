package PacMan;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class Entity  {

    protected int pos_X,pos_Y;
    protected int dpos_X,dpos_Y;
    protected int req_dx, req_dy;

    protected int start_pos_X, start_pos_Y;

    protected int speed = 4;


    protected Level level = null;

    public Entity(int startPoint_X , int startPoint_Y, int s){
        pos_X = startPoint_X;
        pos_Y = startPoint_Y;
        start_pos_X = startPoint_X;
        start_pos_Y = startPoint_Y;
        speed = s;
    }


    public void setLevel(Level l){
        level = l;
    }

    public Level getLevel() {
        return level;
    }

    public int getPos_X() {
        return pos_X;
    }

    public void setPos_X(int pos_X) {
        this.pos_X = pos_X;
    }

    public int getPos_Y() {
        return pos_Y;
    }

    public void setPos_Y(int pos_Y) {
        this.pos_Y = pos_Y;
    }

    public int getDpos_X() {
        return dpos_X;
    }

    public void setDpos_X(int dpos_X) {
        this.dpos_X = dpos_X;
    }

    public int getDpos_Y() {
        return dpos_Y;
    }

    public void setDpos_Y(int dpos_Y) {
        this.dpos_Y = dpos_Y;
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

    public int getStart_pos_X() {
        return start_pos_X;
    }

    public void setStart_pos_X(int start_pos_X) {
        this.start_pos_X = start_pos_X;
    }

    public int getStart_pos_Y() {
        return start_pos_Y;
    }

    public void setStart_pos_Y(int start_pos_Y) {
        this.start_pos_Y = start_pos_Y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }




}
