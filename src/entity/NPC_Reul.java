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

        dialogue[0][0] = "You gave me a fright, lad.";
        dialogue[0][1] = "You just suddenly appeared out of thin air!";
        dialogue[0][2] = "Huh? You don't know where you are?";
        dialogue[0][3] = "This is the land of Nurvia! The land of peace and tranquility.";
        dialogue[0][4] = "But we have a problem.";
        dialogue[0][5] = "The Demon King has taken over the land!";
        dialogue[0][6] = "We are hopeless in his tyranny!";
        dialogue[0][7] = "Listen closely, lad.";
        dialogue[0][8] = "I have summoned you here for a purpose.";
        dialogue[0][9] = "It will be through only your power to defeat the Demon King!";
        dialogue[0][10] = "But do not worry. I will guide you every step of the way.";
        dialogue[0][11] = "Please do follow me.";

        dialogue[1][0] = "I will be staying here for a while.";
        dialogue[1][1] = "There are many slimes around.";
        dialogue[1][2] = "Can you take care of them for me?";

        dialogue[2][0] = "Huh? You don't know how to fight?";
        dialogue[2][1] = "Just press E!";
        dialogue[2][2] = "Your sword will just come out.";

  

    }

    public void setMovement(){

        if (onPath == true){

            int goalColumn = 24;
            int goalRow = 43;

            searchPath(goalColumn, goalRow);

        }

        else{

            if (introDone == true){
                onPath = true;
            }

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
        facePlayer();
        startDialogue(this, dialogueSet);

        if (introDone == true){
            dialogueSet = 1;
        }


    }
    
}
