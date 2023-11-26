package object;

import entity.Entity;
import main.GamePanel;

public class Object_Health_Potion_Small extends Entity {

    GamePanel gp;
    public static final String objectName = "Small Health Potion";
    int value = 3;

    public Object_Health_Potion_Small (GamePanel gp){
        super (gp);
        this.gp = gp;

        type = type_consumable;
        name = objectName;
        down1 = setup("/objects/health_potion_small", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA health potion.\nHeals by " + value + " HP.";
        price = 30;
        stackable = true;
        
        setDialogue();
    }

    public void setDialogue(){
        
        dialogue[0][0] = "You drink the " + name + "!\n"
            + "Your life has been healed by " + value + " HP!";

        dialogue[1][0] = "Your health is full!";

    }

    public boolean use (Entity entity){

        if (gp.player.HP < gp.player.maxHP){
            startDialogue(this, 0);
            entity.HP += value;
            gp.playSoundEffect(3);
            return true;
        }
        else{
            startDialogue(this, 1);
            gp.playSoundEffect(15);
            return false;
        }
    }
}
