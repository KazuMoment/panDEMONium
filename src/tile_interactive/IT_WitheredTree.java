package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_WitheredTree extends InteractiveTile{

    GamePanel gp;

    public IT_WitheredTree(GamePanel gp, int column, int row) {
        super(gp, column, row);
        this.gp = gp;

        this.worldX = gp.tileSize * column;
        this.worldY = gp.tileSize * row;
        
        down1 = setup("/tile_interactive/witheredtree", gp.tileSize, gp.tileSize);
        destructible = true;
        HP = 3;
    }

    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe){
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSoundEffect(){
        gp.playSoundEffect(12);
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = new IT_WitheredTrunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor(){
        Color color = new Color(57, 48, 46);
        return color;
    }
    public int getParticleSize(){
        int size = 6; //6 pixels
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxHP(){
        int maxHP = 20;
        return maxHP;
    }
    
}
