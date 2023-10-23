package object;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class Object_Fireball extends Projectile{

    GamePanel gp;

    public Object_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Fireball";
        solidArea = new Rectangle(8, 16 , 32, 32);
        solidAreaDefaultX = solidArea.x;
        speed = 9;
        maxHP = 70;
        HP = maxHP;
        knockbackPower = 3;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();

    }

    public void getImage(){
        up1  = setup("/projectile/fireball_up1", gp.tileSize, gp.tileSize);
        up2  = setup("/projectile/fireball_up2", gp.tileSize, gp.tileSize);
        down1  = setup("/projectile/fireball_down1", gp.tileSize, gp.tileSize);
        down2  = setup("/projectile/fireball_down2", gp.tileSize, gp.tileSize);
        left1  = setup("/projectile/fireball_left1", gp.tileSize, gp.tileSize);
        left2  = setup("/projectile/fireball_left2", gp.tileSize, gp.tileSize);
        right1  = setup("/projectile/fireball_right1", gp.tileSize, gp.tileSize);
        right2  = setup("/projectile/fireball_right2", gp.tileSize, gp.tileSize);
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
        Color color = new Color(240, 50, 0);
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
