package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Object_Gold;
import object.Object_Heart;
import object.Object_Lavaball;
import object.Object_ManaCrystal;

public class Enemy_RockSlime extends Entity{

    GamePanel gp;

    public Enemy_RockSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = "Rock Slime";
        defaultSpeed = (int)1.5;
        speed = defaultSpeed;
        maxHP = 10;
        HP = maxHP;
        attack = 2; 
        defense = 2;
        EXP = 4;
        projectile = new Object_Lavaball(gp);

        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 46;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        down1 = setup("/enemy/rockslime_right_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/rockslime_right_2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/rockslime_right_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/rockslime_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/rockslime_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/rockslime_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/rockslime_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/rockslime_right_2", gp.tileSize, gp.tileSize);

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
