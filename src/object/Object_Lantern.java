package object;

import entity.Entity;
import main.GamePanel;

public class Object_Lantern extends Entity{

    public Object_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = "Lantern";
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern]\nIlluminates your\nsurroundings";
        price = 200;
        lightRadius = 250;
    }
    
}
