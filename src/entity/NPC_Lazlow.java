package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Lazlow extends Entity{

    public static final String npcName = "Lazlow";

    public NPC_Lazlow(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 0;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;


        getImage();
        setDialogue();

    }

    public void getImage(){

        up1 = setup("/npc/kid1", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/kid2", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/kid1", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/kid2", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/kid1", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/kid2", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/kid1", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/kid2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0][0] = "Mommy said that dad was a hero.";
        dialogue[0][1] = "I don't want dad to be a hero...";
        dialogue[0][2] = "I just want him to be here...";
        dialogue[0][3] = "WAAHHHH! *cries*";
        
        dialogue[1][0] = "WAAHHHH! *cries*";

    }

    public void setMovement(){

    }

    public void speak(){
    	facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        
        if (introDone == true && dialogueSet > 1){
            dialogueSet = 1;
        }

    }
    
}
