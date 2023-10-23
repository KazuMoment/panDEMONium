package entity;

import main.GamePanel;

public class Projectile extends Entity {

    Entity user;
    
    public Projectile(GamePanel gp){
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.HP = this.maxHP; 
    }

    public void update(){

        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        if (collisionOn == true){
            generateParticle(user.projectile, user.projectile);
            alive = false; 
        }

        if (user == gp.player){
            int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
            if (enemyIndex != 999){
                gp.player.damageEnemy(enemyIndex, this, attack, knockbackPower);
                generateParticle(user.projectile, gp.enemy[gp.currentMap][enemyIndex]);
                alive = false;
            }
        }
        if (user != gp.player){
            boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
            if (gp.player.invulnerable == false && contactPlayer == true){
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false; 
            }
        }

        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
        HP--;
        if (HP <= 0){
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12){
            if (spriteNumber == 1){
                spriteNumber = 2;
            }
            else if (spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public boolean enoughCost(Entity user){
        boolean enoughMP = false;
        return enoughMP;
    }
    
    public void payCost(Entity user){
    }
    
}
