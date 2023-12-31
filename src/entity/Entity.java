package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, 
    attackLeft1, attackLeft2, attackRight1, attackRight2, 
    guardUp, guardDown, guardLeft, guardRight;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public BufferedImage image, image2, image3;
    public String dialogue[][] = new String[20][20];   
    public Entity attacker;
    public Entity linekdEntity;
    public boolean temp = false;
   
    // State
    public int worldX, worldY;
    public String direction = "down";
    public boolean collisionOn = false;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public int spriteNumber = 1;
    public boolean invulnerable = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean following = false;
    public boolean knockback = false;
    public String knockbackDirection;
    public boolean guarding = false;   
    public boolean transparent = false;
    public boolean parried = false;
    public Entity loot;
    public Entity reward;
    public boolean opened = false;
    public boolean introDone = false;
    public boolean pickedQuestObject = false;
    public boolean doneQuest1 = false;
    public boolean doneQuest2 = false;
    public boolean receivedReward = false;
    public boolean goalReached = false;
    boolean unmovable = true;
    public boolean rage = false;
    public boolean sleep = false;
    public boolean standby = false;
    public boolean drawing = true;

    
    // Counter 
    public int spriteCounter = 0;
    public int invulnerableCounter = 0;
    public int actionLockCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shootCounter = 0;
    public int summonCounter = 0;
    int knockbackCounter = 0;
    public int guardCounter = 0;
    public int parriedCounter = 0;

    // Character Attributes
    public String name;
    public int speed;
    public int defaultSpeed;
    public int maxHP;
    public int HP;
    public int maxMP;
    public int MP;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int EXP;
    public int nextLevelEXP;
    public int gold;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile; 
    public Entity currentLight;
    public int obstacleNumber;
    public int obstacleIndex;
    public boolean boss;

    // Item Attributes
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int attackValue;
    public int defenseValue;
    public String description = " ";
    public int useCost; 
    public int value;
    public int price;
    public int knockbackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    // Type
    public int type; //0 = player, 1 = npc, 2 = enemy
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_enemy = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield =  5;
    public final int type_consumable = 6;
    public final int type_pickuponly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;


    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public int getScreenX(){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    
    public int getScreenY(){
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }

    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getColumn(){
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY + solidArea.y)/gp.tileSize;
    }

    public int getCenterX(){
        int centerX = worldX + left1.getWidth()/2;
        return centerX;
    }

    public int getCenterY(){
        int centerY = worldY + up1.getHeight();
        return centerY;
    }

    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }

    public int getYdistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }

    public int getTileDistance(Entity target){
        int tileDistance = ((getXdistance(target) + getYdistance(target))/gp.tileSize);
        return tileDistance;
    }

    public int getGoalColumn(Entity target){
        int goalColumn = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalColumn;
    }
    public int getGoalRow(Entity target){
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    public void resetCounter(){

        spriteCounter = 0;
        invulnerableCounter = 0;
        actionLockCounter = 0;  
        dyingCounter = 0;
        hpBarCounter = 0;
        shootCounter = 0;
        knockbackCounter = 0;
        guardCounter = 0;
        parriedCounter = 0;

    }

    public void setLoot(Entity loot){}

    public void setReward(Entity reward){}


    public void setMovement(){}

    public void move(String direction){

        if (unmovable == false){
            this.direction = direction;

            checkCollision();
        
            if (collisionOn == false){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;   
                }
            }
        }
    }

    public void damageReaction(){}
    
    public void speak(){}

    public void facePlayer(){
        switch(gp.player.direction){
            case "up": direction = "down"; break;  
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right":direction = "left"; break;
        }
    }

    public void startDialogue(Entity entity, int setNumber){

        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNumber;
    }

    public void interact(){}
    
    public void checkDrop(){}

    public void dropItem(Entity droppedItem){
        for (int i = 0; i < gp.obj[1].length; i++){
            if (gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; //Current enemy coordinates
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0; //6 pixels
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxHP(){
        int maxHP = 0;
        return maxHP;
    }

    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxHP = generator.getParticleMaxHP();

        Particle p1 = new Particle(gp, target, color, size, speed, maxHP, -2, -1);
        gp.particleList.add(p1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxHP, 2, -1);
        gp.particleList.add(p2);
        Particle p3 = new Particle(gp, target, color, size, speed, maxHP, -2, 1);
        gp.particleList.add(p3);
        Particle p4 = new Particle(gp, target, color, size, speed, maxHP, 2, 1);
        gp.particleList.add(p4);

    }

    public boolean use (Entity entity){return false;}

    public void checkCollision(){

        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.enemy);
        gp.collisionChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == type_enemy && contactPlayer == true){
            damagePlayer(attack);
        } 
    }

    public void update(){

        if (sleep == false){

            if (knockback == true){

                checkCollision();

                if (collisionOn == true){
                    knockbackCounter = 0;
                    knockback = false;
                    speed = defaultSpeed;
                }
                else if (collisionOn == false){
                    switch(knockbackDirection){
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
                knockbackCounter++;
                if (knockbackCounter == 10){
                    knockbackCounter = 0;
                    knockback = false;
                    speed = defaultSpeed;
                } 
                }
            else if (attacking == true){
                attacking();
            }

            else{
                setMovement();
                checkCollision();

                if (collisionOn == false){
                    switch(direction){
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }

                if (speed > 0){
                    spriteCounter++;
                    if (spriteCounter > 24){
                        if (spriteNumber == 1){
                            spriteNumber = 2;
                        }
                        else if (spriteNumber == 2){
                            spriteNumber = 1;
                        }
                        spriteCounter = 0;
                    }
                }
            }

            if (invulnerable == true){
                invulnerableCounter++;
                if (invulnerableCounter > 30){
                    invulnerable = false;
                    invulnerableCounter = 0;
                }
            }

            if (shootCounter < 30){
                shootCounter++;
            }

            if (summonCounter < 60){
                summonCounter++;
            }

            if (parried == true){
                parriedCounter++;
                if (parriedCounter > 120){
                    parried = false;
                    parriedCounter = 0;
                }
            }
        }
    }

    public void moveTowardPlayer(int interval){
        actionLockCounter++;
        if (actionLockCounter > interval){
            if (getXdistance(gp.player) > getYdistance(gp.player)){
                if (gp.player.getCenterX() < getCenterX()){
                    direction = "left";
                }
                else {
                    direction = "right";
                }
            }
            else if (getXdistance(gp.player) < getYdistance(gp.player)){
                if (gp.player.getCenterY() < getCenterY()){
                    direction = "up";
                }
                else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }

    }

    public String getOppositeDirection(String direction){

        String oppositeDirection = "";

        switch(direction){
            case "up": oppositeDirection = "down"; break;
            case "down": oppositeDirection = "up"; break;
            case "left": oppositeDirection = "right"; break;
            case "right": oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }

    public void attacking(){

        spriteCounter++;

        if (spriteCounter <= motion1_duration){
            spriteNumber = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration){
            spriteNumber = 2;

            // Save Current world x, y, solid area
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust these variables for attack check
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // Attack Area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_enemy){
                if (gp.collisionChecker.checkPlayer(this) == true){
                    damagePlayer(attack);
                }

            }

            else{ // Player
                // Check enemy collision with updated world x, y, solid area
                int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
                gp.player.damageEnemy(enemyIndex, this, attack, currentWeapon.knockbackPower);

                int iTileIndex = gp.collisionChecker.checkEntity(this, gp.iTile);
                gp.player.interactTile(iTileIndex);

                int projectileIndex = gp.collisionChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }


            

            // Restore values after checking
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > motion2_duration){
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack){
        if (gp.player.invulnerable == false){
            int damage; 
            if (attack >= gp.player.defense) {
                    damage = (attack * 2) - gp.player.defense;
                } 
                else {
                    damage = attack * (attack / gp.player.defense);
                }

            // Get opposite direction of attacker
            String canGuardDirection = getOppositeDirection(direction);

            if (gp.player.guarding == true && gp.player.direction == canGuardDirection){

                // Parry
                if (gp.player.guardCounter < 10){
                    damage = 0;
                    gp.playSoundEffect(18);
                    setKnockback(this, gp.player, knockbackPower);
                    parried = true;
                    spriteCounter -= 120;
                }

                // Block
                else{ 
                    damage /= 3;
                    gp.playSoundEffect(17);
                }
            }

            else{
                gp.playSoundEffect(7);
                if (damage < 1){
                    damage = 1;
                }
            }

            if (damage != 0){
                gp.player.transparent = true;
                setKnockback(gp.player, this, knockbackPower);
            }

            gp.player.HP -= damage;
            gp.player.invulnerable = true;
            
        } 
    }

    public boolean inCamera(){
        boolean inCamera = false;

        if (worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                inCamera =true;
            }
            return inCamera;
    }

    public void setKnockback(Entity target, Entity attacker, int knockbackPower){
        this.attacker = attacker;
        target.knockbackDirection = attacker.direction;
        target.speed += knockbackPower;
        target.knockback = true;
    }

    public void attackRate(int rate, int vertical, int horizontal){

        boolean targetInRange = false;
        int xDistance = getXdistance(gp.player);
        int yDistance = getYdistance(gp.player);

        switch(direction){
            case "up":
                if(gp.player.getCenterY() < getCenterY() && yDistance < vertical && xDistance < horizontal){
                    targetInRange = true; 
                }
                break;
            case "down":
                if(gp.player.getCenterY() > getCenterY() && yDistance < vertical && xDistance < horizontal){
                    targetInRange = true; 
                }
                break;
            case "left":
                if(gp.player.getCenterX() < getCenterX() && xDistance < vertical && yDistance < horizontal){
                    targetInRange = true; 
                }
                break;
            case "right":
                if(gp.player.getCenterX() > getCenterX() && xDistance < vertical && yDistance < horizontal){
                    targetInRange = true; 
                }
                break;
        }

        if (targetInRange == true){
            // Check if it will attack
            int i = new Random().nextInt(rate);
            if (i == 0){
                attacking = true;
                spriteNumber = 1;
                spriteCounter = 0;
                shootCounter = 0;
            }
        }

    }

    public void shootRate(int rate, int shootInterval){

        int i = new Random().nextInt(rate);
            if (i == 0 && projectile.alive == false && shootCounter == shootInterval){
                projectile.set(worldX, worldY, direction, true, this);
                
                // Check Vacancy
                for (int ii = 0; ii < gp.projectile[1].length; ii++){
                    if (gp.projectile[gp.currentMap][ii] == null){
                        gp.projectile[gp.currentMap][ii] = projectile;
                        break;
                    }
                }
                shootCounter = 0;
            }
    }

   

    public void checkStartAggroRange(Entity target, int distance, int rate){
        if (getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                following = true;
                onPath = true;
            }
        }
    }

    public void checkStopAggroRange(Entity target, int distance, int rate){
        if (getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if (i == 0){
                following = false;
                onPath = false;
            }
        }
    }

    public void getRandomDirection(int interval){
        
        actionLockCounter++;

        if (actionLockCounter > interval){
            Random random = new Random();
            int i = random.nextInt(100)+1; // random number from 1 to 100
            if (i <= 25){direction = "up";}
            if (i > 25 && i <= 50){direction = "down";}
            if (i > 50 && i <= 75){direction = "left";}
            if (i > 75 && i <= 100){direction = "right";}
            actionLockCounter = 0;
        }
    }

    public void draw (Graphics2D g2){

        BufferedImage image = null;

        if (inCamera() == true){
                
            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch(direction){
                case "up":
                    if (attacking == false){
                        if (spriteNumber == 1){image = up1;} 
                        if (spriteNumber == 2){image = up2;} 
                    }
                    if (attacking == true){
                        tempScreenY = getScreenY() - up1.getHeight();
                        if (spriteNumber == 1){image = attackUp1;} 
                        if (spriteNumber == 2){image = attackUp2;} 
                    } 
                    break;

                case "down":
                    if (attacking == false){
                        if (spriteNumber == 1){image = down1;} 
                        if (spriteNumber == 2){image = down2;} 
                    }
                    if (attacking == true){
                        if (spriteNumber == 1){image = attackDown1;} 
                        if (spriteNumber == 2){image = attackDown2;} 
                    }
                    break;

                case "left":
                    if (attacking == false){
                        if (spriteNumber == 1){image = left1;} 
                        if (spriteNumber == 2){image = left2;} 
                    }
                    if (attacking == true){
                        tempScreenX = getScreenX() - left1.getWidth();
                        if (spriteNumber == 1){image = attackLeft1;} 
                        if (spriteNumber == 2){image = attackLeft2;} 
                    }
                    break; 

                case "right":
                    if (attacking == false){
                        if (spriteNumber == 1){image = right1;} 
                        if (spriteNumber == 2){image = right2;} 
                    }
                    if (attacking == true){
                        if (spriteNumber == 1){image = attackRight1;} 
                        if (spriteNumber == 2){image = attackRight2;} 
                    }
                    break; 
            }

            if (invulnerable == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }

            if (dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);
                
            changeAlpha(g2, 1f);
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i){changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i * 2){changeAlpha(g2, 1f);}
        if (dyingCounter > i * 2 && dyingCounter <= i * 3){changeAlpha(g2, 0f);}
        if (dyingCounter > i * 3  && dyingCounter <= i * 4){changeAlpha(g2, 1f);}
        if (dyingCounter > i * 4  && dyingCounter <= i * 5){changeAlpha(g2, 0f);}
        if (dyingCounter > i * 5 && dyingCounter <= i * 6){changeAlpha(g2, 1f);}
        if (dyingCounter > i * 6 && dyingCounter <= i * 7){changeAlpha(g2, 0f);}
        if (dyingCounter > i * 7 && dyingCounter <= i * 8){changeAlpha(g2, 1f);}
        if(dyingCounter > i * 8){
            alive = false;
        }
    }

    public void changeAlpha (Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;

    }

    public void searchPath(int goalColumn, int goalRow){
    
        int startColumn = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startColumn, startRow, goalColumn, goalRow);

        if (gp.pFinder.search() == true){

            // Next World X and Y
            int nextX = gp.pFinder.pathList.get(0).column * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's Solid Area Position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                // Left or Right
                if (enLeftX > nextX){
                    direction = "left";
                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            } 
            else if(enTopY > nextY && enLeftX > nextX){
                // Up or Left
                direction = "up";
                checkCollision();
                if (collisionOn == true){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX){   
                // Up or Right
                direction = "up";
                checkCollision();
                if (collisionOn == true){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX){
                // Down or Left
                direction = "down";
                checkCollision();
                if (collisionOn == true){
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX){
                // Down or Right
                direction = "down";
                checkCollision();
                if (collisionOn == true){
                    direction = "right";
                }
            }

            // If reaches goal, stop search
            if (following == false){
                int nextColumn = gp.pFinder.pathList.get(0).column;
                int nextRow = gp.pFinder.pathList.get(0).row;
                if (nextColumn == goalColumn && nextRow == goalRow){
                    onPath = false;
                    goalReached = true;
                }
            }
        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName){

        int index = 999;

        // Check surrounding objects
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction) {
            case "up": nextWorldY = user.getTopY() - user.speed; break;  
            case "down": nextWorldY = user.getBottomY() + user.speed; break; 
            case "left": nextWorldX = user.getLeftX() - user.speed; break;   
            case "right": nextWorldX = user.getRightX() + user.speed; break;   
        }

        int column = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length; i++){
            if (target[gp.currentMap][i] != null){
                if (target[gp.currentMap][i].getColumn() == column &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)){
                    index = i;
                    break;
                }
            }
        }
        return index;
        
    }


}
