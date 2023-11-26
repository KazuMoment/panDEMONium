package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Piyaye extends Entity {

    public NPC_Piyaye(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 2;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("/npc/fisherman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/fisherman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/fisherman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/fisherman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/fisherman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/fisherman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/fisherman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/fisherman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0][0] = "Oh. Hey. I'm Piyaye.";
        dialogue[0][1] = "I'm having trouble right now.";
        dialogue[0][2] = "Some orc stole my paddles, and \nI can't leave this island without it!";
        dialogue[0][3] = "He's like really big, and strong-looking!";
        dialogue[0][4] = "Tell you what. Defeat that orc and give me my paddle back,\nand I'll take you to Tinvaak Village.";

        dialogue[1][0] = "Got the paddle back, I hope? The orc over there has it!";

        dialogue[2][0] = "Oh. By the way, you have a shield, right?";
        dialogue[2][1] = "Press SPACE and you'll block! It'll\nreduce damage!";
        dialogue[2][2] = "Press SPACE at the moment an enemy is about to hit you,\nand you'll be able to parry!";
        dialogue[2][3] = "When you hear a big banging sound, attack them!";
        dialogue[2][4] = "It'll do a big critical hit!";

        dialogue[3][0] = "You got the paddle? Perfect! Let's \ngo to the boat right away!";


    }

    public void setMovement(){

        if (onPath == true){

            int goalColumn = 33;
            int goalRow = 38;

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
        facePlayer();
        startDialogue(this, dialogueSet);

        if (introDone == true){
            dialogueSet = 1;
        }
        
    }
    
}
