package object;

import entity.Entity;
import main.GamePanel;

public class Object_Key extends Entity{

    GamePanel gp;
    public static final String objectName = "Key";

    public Object_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objectName;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA key for many doors.";
        price = 20;

        setDialogue();

    }


    public void setDialogue(){

        dialogue[0][0] = "You used " + name + " and opened the door!";

        dialogue[1][0] = "Wow! You used a key on the air!";
    }   


    public boolean use(Entity entity){

        int objIndex = getDetected(entity, gp.obj, "Door");
        if (objIndex != 999){
            startDialogue(this, 0);
            gp.playSoundEffect(4);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else{
            startDialogue(this, 1);
            gp.playSoundEffect(15);
            return false;
        }
    }

    
}
