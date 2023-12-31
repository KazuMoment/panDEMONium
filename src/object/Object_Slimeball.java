package object;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Projectile;
import main.GamePanel;

public class Object_Slimeball extends Projectile{

    GamePanel gp;
    public static final String objectName = "Slimeball";

    public Object_Slimeball(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objectName;
        solidArea = new Rectangle(8, 16 , 20, 20);
        solidAreaDefaultX = solidArea.x;
        speed = 5;
        maxHP = 100;
        HP = maxHP;
        attack = 2;
        useCost = 1;
        alive = false;

        getImage();

    }

    public void getImage(){
        up1  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        up2  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        down1  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        down2  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        left1  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        left2  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        right1  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
        right2  = setup("/projectile/slimeball", gp.tileSize, gp.tileSize);
    }

    public Color getParticleColor(){
        Color color = new Color(121, 235, 123);
        return color;
    }
    public int getParticleSize(){
        int size = 6; // 6 pixels
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
