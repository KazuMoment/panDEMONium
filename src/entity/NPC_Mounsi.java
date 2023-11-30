package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.Object_Boat;
import object.Object_Sacred_Rose;

public class NPC_Mounsi extends Entity{

    public static final String npcName = "Mounsi";

    public NPC_Mounsi(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 0;
        name = npcName;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        dialogueSet = -1;


        getImage();
        setDialogue();

    }

    public void getImage(){

        up1 = setup("/npc/old_mayor", gp.tileSize, gp.tileSize);
        up2 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
        down1 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
        down2 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
        left1 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
        left2 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
        right1 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
        right2 = setup ("/npc/old_mayor", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogue[0][0] = "I'm the mayor of this village...";
        dialogue[0][1] = "This is shameful, but i have a request for you hero..";
        dialogue[0][2] = "The necromancer set up base to the forest up north..";
        dialogue[0][3] = "Can you slay him for us? And take back our rose?";
        dialogue[0][4] = "There's someone who can guide you to the forest.";
        
        dialogue[1][0] = "Help us...";

        dialogue[2][0] = "This rose...";
        dialogue[2][1] = "I feel its rejuvenating presence.";
        dialogue[2][2] = "I feel that our village will prosper in life once more.";
        dialogue[2][3] = "Thank you, adventurer.";
        dialogue[2][4] = "Our boat will be accessible now. Without the two generals to \nmaintain peace, you can challenge the Demon Lord!";

    }

    public void setMovement(){

    }

    public void speak(){
    	facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        
        if (introDone == false) {
        	for (int mapNum = 0; mapNum < gp.maxMap; mapNum++){
        		for (int i = 0; i < gp.npc[1].length; i++){
        			if (gp.npc[mapNum][i] != null && 
        				gp.npc[mapNum][i].name == (NPC_Bogart.npcName)){
        				gp.npc[mapNum][i].doneQuest1 = true;
                        gp.npc[mapNum][i].sleep = false;
        			}
        		}	
        	}
        }
        
        if (introDone == true && dialogueSet > 1){
            dialogueSet = 1;
        }

        int index = gp.player.searchItemInInventory(Object_Sacred_Rose.objectName);
        if (index != 999){
            dialogueSet = 2;
            gp.player.inventory.remove(index);
            doneQuest1 = true;
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for (int i = 0; i < gp.obj[1].length; i++){
                    if (gp.obj[mapNum][i] != null && 
                        gp.obj[mapNum][i].name == Object_Boat.objectName &&
                        gp.obj[mapNum][i].collision == true){
                            gp.obj[mapNum][i].collision = false;
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                            break;
                    }
                }
            }
        }       

    }
    
}
