package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

import object.Object_Health_Potion_Small;
import object.Object_Heart;
import object.Object_Mana_Potion_Small;
import object.Object_WaterBall;

public class Enemy_BlueSlime extends Entity{
    GamePanel gp;
    public static String enemyName = "Blue Slime";

    public Enemy_BlueSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = enemyName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHP = 12;
        HP = maxHP;
        attack = 3; 
        defense = 4;
        EXP = 3;
        projectile = new Object_WaterBall(gp);

        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 46;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        down1 = setup("/enemy/blueslime_right_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/blueslime_right_2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/blueslime_left_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/blueslime_left_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/blueslime_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/blueslime_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/blueslime_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/blueslime_right_2", gp.tileSize, gp.tileSize);

    }

    public void setMovement(){

        if (onPath == true){

            // Check if player is still in range
            checkStopAggroRange(gp.player, 12, 100);

            // Search direction
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
            
            // Checks if to shoot
            shootRate(100, 30);

        }
    

        else{

            checkStartAggroRange(gp.player, 5, 100);

            // Get a random direction
            getRandomDirection(120);
        }

    }

    public void damageReaction(){
        actionLockCounter = 0;
        following = true;
        onPath = true;
    }

    public void checkDrop(){
        // Cast die
        int i = new Random().nextInt(100)+1;

        // Set Enemy Drop
        if (i < 50){
            dropItem(new Object_Mana_Potion_Small(gp));
        }
        if (i >= 50 && i < 75){
            dropItem(new Object_Heart(gp));
        }
        if (i >= 75 && i < 100){
            dropItem(new Object_Health_Potion_Small(gp));
        }

    }

    public void dropItem(){
        
    }
    
}
