package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Kane extends Entity{

    public NPC_Kane(GamePanel gp){
        super(gp);

        direction = "left";
        speed = 2;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        getImage();
        setDialogue();

    }

    public void getImage(){

        up1 = setup("/npc/kane_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/kane_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/kane_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/kane_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/kane_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/kane_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/kane_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/kane_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0] = "Hello, stranger. You seem troubled.";
        dialogue[1] = "May I ask what you are doing here?";
        dialogue[2] = "What? You don't know where you are?";
        dialogue[3] = "Well, this is Nurvia. The land of tranquility.";
        dialogue[4] = "That is, until the Demon King took control \nof this land.";
        dialogue[5] = "Be careful, stranger. One does not cross\nthe Tenari Rivers so casually.";

    }

    public void setMovement(){

        if (onPath == true){

            int goalColumn = 12;
            int goalRow = 9;

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
            onPath = true;
        }

    }
    
}
