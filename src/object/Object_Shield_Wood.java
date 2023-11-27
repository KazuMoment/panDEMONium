package object;

import entity.Entity;
import main.GamePanel;

public class Object_Shield_Wood extends Entity{

    public static final String objectName = "Wooden Shield";
    
    public Object_Shield_Wood(GamePanel gp){
        super(gp);
        type = type_shield;
        name = objectName;
        down1 = setup("/objects/wood shield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nA wooden shield.";
        price = 14;
    }

    
}
