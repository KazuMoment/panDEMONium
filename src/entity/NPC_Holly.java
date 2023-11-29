package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Holly extends Entity {
	public static final String npcName = "Holly";
    
    public NPC_Holly(GamePanel gp){
        super(gp);

        name = npcName;
        direction = "down";
        speed = 0;
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;
        
        getImage();
        setDialogue();
    }
    public void getImage(){

        up1 = setup("/npc/HO1_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/HO1_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/HO1_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/HO1_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/HO1_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/HO1_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/HO1_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/HO1_down_2",gp.tileSize,gp.tileSize);
        
    }
    
    public void setDialogue(){
        dialogue[0][0] = "There are lots of hidden treasures!";
        dialogue[0][1] = "Make sure you find them all.";
        dialogue[0][2] = "It will give you lots of benefits!";
        
        dialogue[1][0] = "I wonder what treasures you'll find out there...?";
        
    }

    public void setMovement(){		

    
    }

    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        
        if (dialogue[dialogueSet][0] == null){
            dialogueSet = 0;
        }
    }

}
