package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Jobert extends Entity{
    public static final String npcName = "Jobert";

    public NPC_Jobert(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        name = npcName;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;


        getImage();
        setDialogue();

    }

    public void getImage(){

        up1 = setup("/npc/jobert_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/jobert_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/jobert_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/jobert_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/jobert_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/jobert_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/jobert_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/jobert_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0][0] = "To the northwest of the village lies a cemetery.";
        dialogue[0][1] = "According to rumor, there is a chest located there.";

        dialogue[1][0] = "On the northeast side of the village,";
        dialogue[1][1] = "People say there is a merchant tent there.";
        dialogue[1][2] = "Maybe you can buy items from them?";
        
        dialogue[2][0] = "I'm quite good with directions.";
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
        dialogueSet++;
        
        if (introDone == true && dialogueSet > 2){
            dialogueSet = 2;
        }

    }
    
}