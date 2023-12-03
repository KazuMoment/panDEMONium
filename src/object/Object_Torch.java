package object;

import entity.Entity;
import main.GamePanel;

public class Object_Torch extends Entity{

    GamePanel gp;
    public static final String objectName = "Torch";

    public Object_Torch(GamePanel gp){
        super(gp);

        type = type_light;
        name = objectName;
        down1 = setup("/objects/torch_placed", gp.tileSize, gp.tileSize);
        description = "[" + name + "]" + "\nIlluminates your\nsurroundings a bit.";
        price = 20;
        lightRadius = 100;
    }
    
}
