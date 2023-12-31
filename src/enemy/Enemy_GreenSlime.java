package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Object_Slimeball;
import object.Object_Gold;
import object.Object_Heart;
import object.Object_ManaCrystal;

public class Enemy_GreenSlime extends Entity{

    GamePanel gp;
    public static final String enemyName = "Green Slime";

    public Enemy_GreenSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = enemyName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHP = 6;
        HP = maxHP;
        attack = 1; 
        defense = 0;
        EXP = 2;
        projectile = new Object_Slimeball(gp);

        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 46;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        down1 = setup("/enemy/greenslime_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/greenslime_down2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/greenslime_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/greenslime_up2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/greenslime_up1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/greenslime_up2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/greenslime_down1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/greenslime_down2", gp.tileSize, gp.tileSize);

    }

    public void setMovement(){

        if (onPath == true){

            // Check if player is still in range
            checkStopAggroRange(gp.player, 12, 100);

            // Search direction
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
            
            // Checks if to shoot
            shootRate(200, 30);

        }
    

        else{

            // checkStartAggroRange(gp.player, 5, 100);

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
