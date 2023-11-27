package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Piyaye extends Entity {

    public static final String npcName = "Piyaye";

    public NPC_Piyaye(GamePanel gp){
        super(gp);

        direction = "down";
        defaultSpeed = 2;
        speed = defaultSpeed;
        name = npcName;

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
        dialogue[1][1] = "I hid a wooden shield back there! \nEquip it and talk to me when you get it!";

        dialogue[2][0] = "Oh. By the way, you have a shield, right?";
        dialogue[2][1] = "Press SPACE and you'll block! It'll\nreduce damage!";
        dialogue[2][2] = "Press SPACE at the moment an enemy is about to hit you,\nand you'll be able to parry!";
        dialogue[2][3] = "When you hear a big banging sound, attack them!";
        dialogue[2][4] = "It'll do a big critical hit!";

        dialogue[3][0] = "Alright. The boat's all fixed up. \nJust enter the boat when you're ready.";


    }

    public void setMovement(){

        if (onPath == true){

            int goalColumn = 33;
            int goalRow = 36;

            searchPath(goalColumn, goalRow);
            if (goalReached == true){
                standby = true;
            }
        }

        else{
            if (standby == true && doneQuest1 == false){
                direction = "down";
                speed = 0;
            }
            else if (standby == true && doneQuest1 == true){
                direction = "left";
                speed = 0;
            }
        }
    }

    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);

        if (introDone == true && 
            gp.player.currentShield == null && 
            doneQuest1 == false) {
            dialogueSet = 1;
        }
        else if (gp.player.currentShield != null 
            && gp.player.currentShield.name == "Wooden Shield" 
            && doneQuest1 == false){
            dialogueSet = 2;
        }

        else if (introDone == true && doneQuest1 == true){
            dialogueSet = 3;
        }


    }
    
}
