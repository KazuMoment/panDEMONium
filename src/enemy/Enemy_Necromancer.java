package enemy;

import entity.Entity;
import main.GamePanel;
import object.Object_Heart;
import object.Object_PurpleHaze;

public class Enemy_Necromancer extends Entity{

    GamePanel gp;
    public static final String enemyName = "Renovamen";

    public Enemy_Necromancer(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_enemy;
        name = enemyName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHP = 15;
        HP = maxHP;
        attack = 4; 
        defense = 0;
        EXP = 20;
        projectile = new Object_PurpleHaze(gp);

        solidArea.x = 4;
        solidArea.y = 12;
        solidArea.width = 92;
        solidArea.height = 84;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }


    public void getImage(){
    	
    	int i = 2;

        down1 = setup("/enemy/necro_down1", gp.tileSize*i, gp.tileSize*i);
        down2 = setup("/enemy/necro_down2", gp.tileSize*i, gp.tileSize*i);
        up1 = setup("/enemy/necro_up1", gp.tileSize*i, gp.tileSize*i);
        up2 = setup("/enemy/necro_up2", gp.tileSize*i, gp.tileSize*i);
        left1 = setup("/enemy/necro_left1", gp.tileSize*i, gp.tileSize*i);
        left2 = setup("/enemy/necro_left2", gp.tileSize*i, gp.tileSize*i);
        right1 = setup("/enemy/necro_right1", gp.tileSize*i, gp.tileSize*i);
        right2 = setup("/enemy/necro_right2", gp.tileSize*i, gp.tileSize*i);

    }

    public void setMovement(){

        if (rage == false && HP < maxHP/2){
            rage = true;
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }

		if (getTileDistance(gp.player) < 10){	
			moveTowardPlayer(60);
		}

        if (getTileDistance(gp.player) > 3 && getTileDistance(gp.player) < 10){
            shootRate(200, 30);
        }

		else {
			// Get a random direction to move if not chasing
			getRandomDirection(120);
		}
		
		// Check if it attacks
		if (attacking == false){
            attackRate(40, gp.tileSize * 4, gp.tileSize);
		}

    }
    
    public void damageReaction(){

        actionLockCounter = 0;
        following = true;
        onPath = true;

    }

   
    public void checkDrop(){
        // Cast die
        dropItem(new Object_Heart(gp));

    }

    public void dropItem(){
        
    }
    
}
