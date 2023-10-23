package object;

import entity.Entity;
import main.GamePanel;

public class Object_Tent extends Entity{

    GamePanel gp;

    public Object_Tent(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent]\nYou can use this to sleep\nuntil dawn.";
        price = 300;
        stackable = true;
    }

    public boolean use (Entity entity){

        gp.gameState = gp.sleepState;
        gp.playSoundEffect(16);
        gp.player.HP = gp.player.maxHP;
        gp.player.MP = gp.player.maxMP;
        gp.aSetter.setEnemy();
        gp.player.getSleepingImage(down1);

        return true;
    }
    
}
