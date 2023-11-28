package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.Object_Seal;

public class NPC_Fischer extends Entity {

	public static final String npcName = "Fischer";

    public NPC_Fischer(GamePanel gp){
        super(gp);

        name = npcName;
        direction = "down";
        speed = 0;
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setup("/npc/mayor_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/mayor_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/mayor_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/mayor_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/mayor_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/mayor_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/mayor_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/mayor_down_2",gp.tileSize,gp.tileSize);
        
    }

    public void setDialogue(){
        dialogue[0][0]= "Hello, my secretary must've sent you here...";
        dialogue[0][1]= "As you know, there is a big problem currently happening\non the North-East direction.";
        dialogue[0][2]= "Unknown grass has been spreading rapidly.";
        dialogue[0][3]= "Also, there have been unknown noises around there.\nThe villagers are scared.";
        dialogue[0][4]= "The monk has been missing too, for quite some time now...";
        dialogue[0][5]= "Please help us...";
        dialogue[0][6]= "Give me proof of your exploration after. Good luck!";
        
        dialogue[1][0] = "I wish you goodluck!";
        
        dialogue[2][0] = "This seal is giving dark energy but\nit is emmitting holy energy too.";
        dialogue[2][1] = "I guess the missing monk was the cause...\nThank you.";
        dialogue[2][2] = "Because of you, our village is safe now.";
        
        dialogue[3][0] = "I wish you goodluck to your future travels!";
        
    }

    public void setMovement(){
        
    }

    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);
        
        if(doneQuest1 == false){
            if (introDone == true) {
                dialogueSet = 1;
            }
        }

        else {
            dialogueSet = 3;
        }

        int index = gp.player.searchItemInInventory(Object_Seal.objectName);
        if (index != 999){
            dialogueSet = 2;
            gp.player.inventory.remove(index);
            doneQuest1 = true;
        }
    }
}
