package object;

import entity.Entity;
import main.GamePanel;

public class Object_Shield_Tinvaak extends Entity {
    
    public static final String objectName = "Tinvaak Shield";
    
    public Object_Shield_Tinvaak(GamePanel gp){
        super(gp);
        type = type_shield;
        name = objectName;
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA wooden shield from the town\nof Tinvaak. It has a weird art.";
        price = 30;
    }
    
}
