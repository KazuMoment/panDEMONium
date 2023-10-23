package object;

import entity.Entity;
import main.GamePanel;

public class Object_Boots extends Entity{

        public Object_Boots(GamePanel gp){
        super(gp);
        
        name = "Boots";
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
    }

    
}
