package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Object_Gold;
import object.Object_Heart;
import object.Object_ManaCrystal;

public class Enemy_Zombie extends Entity{

    GamePanel gp;
    public static final String enemyName = "Zombie";

    public Enemy_Zombie(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = enemyName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHP = 12;
        HP = maxHP;
        attack = 4; 
        defense = 5;
        EXP = 2;

        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 46;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        down1 = setup("/enemy/zombie_left1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/zombie_left2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/zombie_right1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/zombie_right2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/zombie_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/zombie_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/zombie_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/zombie_right2", gp.tileSize, gp.tileSize);

    }

    public void setMovement(){

        if (onPath == true){

            // Check if player is still in range
            checkStopAggroRange(gp.player, 12, 100);

            // Search direction
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
        }
    

        else{

            checkStartAggroRange(gp.player, 5, 100);

            // Get a random direction
            getRandomDirection(120);
        }

    }

    public void damageReaction(){
        actionLockCounter = 0;
    }

    public void checkDrop(){
        // Cast die
        int i = new Random().nextInt(100)+1;

        // Set Enemy Drop
        if (i < 50){
            dropItem(new Object_Gold(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new Object_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new Object_ManaCrystal(gp));
        }

    }

    public void dropItem(){
        
    }
    
}