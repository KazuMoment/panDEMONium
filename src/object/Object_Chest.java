package object;

import entity.Entity;
import main.GamePanel;

public class Object_Chest extends Entity {

    GamePanel gp;
    public static final String objectName = "Chest";

    public Object_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objectName;
        image = setup("/objects/chest_closed", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setLoot(Entity loot){
        this.loot = loot;

        setDialogue();
    }

    public void setDialogue(){

        dialogue[0][0] = "You opened the chest and found a " + loot.name + "!\n... But you cannot carry more!";
        dialogue[1][0] = "You opened the chest and found a " + loot.name + "!\nYou obtained " + loot.name + "!";
        dialogue[2][0] = "It's empty.";
    }

    public void interact(){

        if (opened == false){
            gp.playSoundEffect(4);


            if (gp.player.canObtainItem(loot) == false){
                startDialogue(this, 0);
            }
            else{
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
        }

        else{
            startDialogue(this, 2);
        }
    }
    
}
