package object;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Object_Purple_Haze extends Projectile{

    GamePanel gp;
    public static final String objectName = "Purple Haze";

    public Object_Purple_Haze(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objectName;
        solidArea = new Rectangle(8 * 2, 16 * 2 , 32 * 2, 32 *2);
        solidAreaDefaultX = solidArea.x;
        speed = 9;
        maxHP = 70;
        HP = maxHP;
        knockbackPower = 3;
        attack = 8;
        useCost = 1;
        alive = false;
        getImage();

    }

    public void getImage(){
    	int i = 2; 
    	
    	up1  = setup("/projectile/purple_up1", gp.tileSize*i, gp.tileSize*i);
        up2  = setup("/projectile/purple_up2", gp.tileSize*i, gp.tileSize*i);
        down1  = setup("/projectile/purple_down1", gp.tileSize*i, gp.tileSize*i);
        down2  = setup("/projectile/purple_down2", gp.tileSize*i, gp.tileSize*i);
        left1  = setup("/projectile/purple_left1", gp.tileSize*i, gp.tileSize*i);
        left2  = setup("/projectile/purple_left2", gp.tileSize*i, gp.tileSize*i);
        right1  = setup("/projectile/purple_right1", gp.tileSize*i, gp.tileSize*i);
        right2  = setup("/projectile/purple_right2", gp.tileSize*i, gp.tileSize*i);
    }

    public boolean enoughCost(Entity user){
        boolean enoughCost = false;
        if (user.MP >= useCost){
            enoughCost = true;
        }
        return enoughCost;
    }

    public void payCost(Entity user){
        user.MP -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(92, 0, 128);
        return color;
    }
    public int getParticleSize(){
        int size = 10;
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
