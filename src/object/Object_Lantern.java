package object;

import entity.Entity;
import main.GamePanel;

public class Object_Lantern extends Entity{

    GamePanel gp;
    public static final String objectName = "Lantern";

    public Object_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = objectName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nIlluminates your\nsurroundings.";
        price = 200;
        lightRadius = 350;
    }
    
}
