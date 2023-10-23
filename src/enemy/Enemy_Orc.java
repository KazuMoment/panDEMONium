package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.Object_Gold;
import object.Object_Heart;
import object.Object_ManaCrystal;

public class Enemy_Orc extends Entity{

    GamePanel gp;

    public Enemy_Orc(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHP = 10;
        HP = maxHP;
        attack = 5; 
        defense = 2;
        EXP = 10;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage(){

        down1 = setup("/enemy/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/orc_down_2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/orc_up_2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/orc_right_2", gp.tileSize, gp.tileSize);

    }

    public void getAttackImage(){

        attackUp1 = setup("/enemy/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/enemy/orc_attack_up_2", gp.tileSize, gp.tileSize* 2);
        attackDown1 = setup("/enemy/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/enemy/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/enemy/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/enemy/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/enemy/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/enemy/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);

    }

    public void setMovement(){

        if (onPath == true){

            // Check if player is still in range
            checkStopAggroRange(gp.player, 12, 100);

            // Search direction
            searchPath(getGoalColumn(gp.player), getGoalRow(gp.player));
            
        }
    

        else{

            // Check if to start aggro
            checkStartAggroRange(gp.player, 8, 100);

            // Get a random direction
            getRandomDirection();
        }

        // Check if it will attack
        if (attacking == false){
            attackRate(30, gp.tileSize * 4, gp.tileSize);
        }

    }

    public void checkDrop(){
        //Cast die
        int i = new Random().nextInt(100)+1;

        //Set Enemy Drop
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





