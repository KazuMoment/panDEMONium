package object;

import entity.Entity;
import main.GamePanel;

public class Object_Mana_Potion_Small extends Entity{

    GamePanel gp;
    public static final String objectName = "Small Mana Potion";

    int value = 2;

    public Object_Mana_Potion_Small (GamePanel gp){
        super (gp);
        this.gp = gp;

        type = type_consumable;
        name = objectName;
        down1 = setup("/objects/mana_potion_small", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA mana potion.\nHeals by " + value + " MP.";
        price = 40;
        stackable = true;
        
        setDialogue();
    }

    public void setDialogue(){
        
        dialogue[0][0] = "You drink the " + name + "!\n"
            + "Your mana has been restored by " + value + " MP!";

        dialogue[1][0] = "Your mana is full!";

    }

    public boolean use (Entity entity){

        if (gp.player.MP < gp.player.maxMP){
            startDialogue(this, 0);
            entity.MP += value;
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
