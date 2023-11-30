package enemy;

import entity.Entity;
import main.GamePanel;
import object.Object_Heart;

public class Enemy_Skeleton extends Entity{

    GamePanel gp;

    public static final String enemyName = "Skeleton";

    public Enemy_Skeleton(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = enemyName;
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxHP = 10;
        HP = maxHP;
        attack = 3; 
        defense = 2;
        EXP = 1;
        knockbackPower = 2;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){

        down1 = setup("/enemy/skeleton_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/skeleton_down_2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/skeleton_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/skeleton_up_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/skeleton_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/skeleton_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/skeleton_up_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/skeleton_up_2", gp.tileSize, gp.tileSize);

    }

    public void setMovement(){

        if (onPath == true){

            // Check if player is still in range
            checkStopAggroRange(gp.player, 6, 100);

            // Search direction
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
            
        }

        else{

            // Check if to start aggro
            checkStartAggroRange(gp.player, 8, 100);

            // Get a random direction
            getRandomDirection(120);
        }

        // Check if it will attack
        if (attacking == false){
            attackRate(20, gp.tileSize * 4, gp.tileSize);
        }

    }

    public void checkDrop(){
        dropItem(new Object_Heart(gp));
    }

}