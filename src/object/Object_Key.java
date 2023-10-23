package object;

import entity.Entity;
import main.GamePanel;

public class Object_Key extends Entity{

    GamePanel gp;

    public Object_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA key for many doors.";
        price = 20;

    }

    public boolean use(Entity entity){

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");
        if (objIndex != 999){
            gp.ui.currentDialogue = "You used " + name + " and opened the door!";
            gp.playSoundEffect(4);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else{
            gp.ui.currentDialogue = "Wow! You used a key on the air!";
            gp.playSoundEffect(15);
            return false;
        }
    }

    
}
