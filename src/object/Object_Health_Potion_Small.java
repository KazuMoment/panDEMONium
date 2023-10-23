package object;

import entity.Entity;
import main.GamePanel;

public class Object_Health_Potion_Small extends Entity {

    GamePanel gp;
    int value = 5;

    public Object_Health_Potion_Small (GamePanel gp){
        super (gp);
        this.gp = gp;

        type = type_consumable;
        name = "Small Health Potion";
        down1 = setup("/objects/health_potion_small", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nA health potion.\nHeals by " + value + " HP.";
        price = 30;
        stackable = true;
    }

    public boolean use (Entity entity){
        gp.gameState = gp.dialogueState;

        if (gp.player.HP < gp.player.maxHP){
            gp.ui.currentDialogue = "You drink the " + name + "!\n"
            + "Your life has been healed by " + value + " HP!";
            entity.HP += value;
            gp.playSoundEffect(3);
            return true;
        }
        else{
            gp.ui.currentDialogue = "Your health is full!";
            gp.playSoundEffect(15);
            return false;
        }
    }
}
