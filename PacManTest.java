package PacMan;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacManTest {

    PacMan pacman;
    Ghost ghost;
    Level level;

    @Before
    public void setup(){
        pacman = new PacMan(20,5,4);
        ghost = new Ghost(10,5,4);
        level = new Level(new Map());
    }

    @Test
    public void p_moveTest(){
        pacman.setDpos_X(1);
        pacman.setPos_X(pacman.getPos_X() + pacman.getDpos_X()*pacman.getSpeed());
        Assert.assertEquals("Nem mozgott", 24,pacman.getPos_X());
    }

    @Test
    public void p_startPosTest(){
        Assert.assertEquals("Rossz a start position", pacman.getPos_X(), pacman.getStart_pos_X());
    }

    @Test
    public void eatTest(){
        pacman.setReq_dx(1);
        pacman.setReq_dy(0);

        pacman.setLevel(new Level(new Map()));

        pacman.setPos_X(80);
        pacman.setPos_Y(40);

        pacman.eat();

        int x = pacman.pos_X/pacman.getLevel().getMap().getBLOCK_SIZE();
        int y = pacman.pos_Y/pacman.getLevel().getMap().getBLOCK_SIZE();

        Assert.assertEquals("Pont nem novekedett", 1,pacman.getScore());
        Assert.assertEquals("Level nem valtozott", 2, pacman.getLevel().getLevelData(y, x));
    }

    @Test
    public void dieTest(){

        int oldLives = pacman.getLives();
        pacman.die();

        Assert.assertEquals("Nem vesztett életet", oldLives-1, pacman.getLives());

        pacman.setLives(1);
        pacman.die();

        Assert.assertEquals("Nem halt meg", true, pacman.isDead());
    }

    @Test
    public void levelDataTest(){

        Assert.assertEquals("Nem masolodott at jol a MAP", level.getLevelData(1,1), level.getMap().getMapData(1,1));
    }

    @Test
    public void ghostsTest(){
        Ghost[] old = level.getGhosts();

        Assert.assertSame("Nem jok a szellemek", old, level.getGhosts());
    }

    @Test
    public void ghostsPosTest(){

        Ghost[] ghosts = level.getGhosts();
        for(int i = 0; i < level.getGhost_number(); i++){
            Assert.assertEquals("Rossz a szellem X pozicio",ghosts[i].getPos_X(), (1+i)*level.getMap().getBLOCK_SIZE());
            Assert.assertEquals("Rossz a szellem Y pozicio",ghosts[i].getPos_Y(), (1)*level.getMap().getBLOCK_SIZE());
        }
    }

    @Test
    public void killPacManTest(){
        level.getPacman().setPos_X(24);
        level.getPacman().setPos_Y(24);

        level.killPacman();

        Assert.assertEquals("Nem allitodott at az X pozicio", level.getPacman().getStart_pos_X(), level.getPacman().getPos_X());
        Assert.assertEquals("Nem allitodott at az Y pozicio",level.getPacman().getStart_pos_Y() , level.getPacman().getPos_Y());
        Assert.assertEquals("Nem vesztett eletet", 2, level.getPacman().getLives());
    }

    @Test
    public void winGameTest(){
        level.winGame();
        Assert.assertEquals("Nem maradt ingame", true, level.isInGame());
    }

    @Test
    public void playerTest(){
        Player player = new Player("Teszt", 10);
        Assert.assertEquals("Rossz a Player konstruktor", player.getName(), "Teszt");
        Assert.assertEquals("Rossz a Player konstruktor", player.getScore(), 10);
    }

}
