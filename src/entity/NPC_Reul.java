package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Reul extends Entity{

    public NPC_Reul(GamePanel gp){
        super(gp);

        direction = "left";
        speed = 1;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        getImage();
        setDialogue();

    }

    public void getImage(){

        up1 = setup("/npc/Reul_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/Reul_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/Reul_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/Reul_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/Reul_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/Reul_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/Reul_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/Reul_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0] = "Hello, lad.";
  

    }

    public void setMovement(){

        if (onPath == true){

            int goalColumn = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalColumn, goalRow);

        }

        else{
             actionLockCounter++;

            if (actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1; // random number from 1 to 100
                if (i <= 25){
                    direction = "up";
                }
                if (i > 25 && i <= 50){
                    direction = "down";
                }
                if (i > 50 && i <= 75){
                    direction = "left";
                }
                if (i > 75 && i <= 100){
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    public void speak(){
        super.speak();
        if (dialogue[dialogueIndex] == null){
            following = true;
            onPath = true;
        }

    }
    
}
