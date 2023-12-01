package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Martha extends Entity{

    public static final String npcName = "Martha";

    public NPC_Martha(GamePanel gp){
        super(gp);

        direction = "left";
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

        up1 = setup("/npc/martha_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/martha_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/martha_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/martha_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/martha_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/martha_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/martha_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/martha_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0][0] = "Our village used to be flourishing.";
        dialogue[0][1] = "We had a special rose that gave life to crops!";
        dialogue[0][2] = "Well, until it was taken from us by a necromancer...";

        dialogue[1][0] = "When the necromancer took the rose.";
        dialogue[1][1] = "Everything started to wither...";
        dialogue[1][2] = "No crops would grow, and the trees started dying...";

        dialogue[2][0] = "After that, the necromancer went up north.";
        dialogue[2][1] = "He corrupted the forest up there.";
        dialogue[2][2] = "I just wish for the necromancer to leave, ";
        dialogue[2][3] = "and for us to get the rose back.";
        
        dialogue[3][0] = "I miss growing crops...";
  
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
            dialogueSet = 3;
        }

    }
    
}
