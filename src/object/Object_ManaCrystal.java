package object;

import entity.Entity;
import main.GamePanel;

public class Object_ManaCrystal extends Entity{
    GamePanel gp;

    public Object_ManaCrystal(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickuponly;
        value = 1;
        name = "Mana Crystal";
        down1 = setup("/objects/mana_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/mana_empty", gp.tileSize, gp.tileSize);
    }
    public boolean use (Entity entity){
        gp.playSoundEffect(3);
        gp.ui.addMessage("Regenerated " + value + " MP!");
        entity.MP += value;
        return true;
    }

}
