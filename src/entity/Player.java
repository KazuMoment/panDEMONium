package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.Object_Fireball;
import object.Object_Health_Potion_Small;
import object.Object_Torch;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int snapFirstSpriteCounter = 0;
    public boolean lightUpdated = false;

    // Quests
    public boolean slimeQuest = false;
    public boolean hasBoots = false;
    
    
    public Player (GamePanel gp, KeyHandler keyH){
        
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //Solid Area 
        solidArea = new Rectangle(8, 16 , 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
    }

    public void setDefaultValues() {
        
        worldX = gp.tileSize * 30;
        worldY = gp.tileSize * 26;

        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        // Player Status
        level = 1;
        maxHP = 6;
        HP = maxHP;
        maxMP = 3;
        MP = maxMP;
        strength = 1; // Attack
        dexterity = 1; // Defense
        EXP = 0;
        nextLevelEXP = 5;
        gold = 500;
        currentLight = null;
        projectile = new Object_Fireball(gp);
        attack = getAttack();
        defense = getDefense();

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
    }

    public void setDefaultPosition(){

        switch(gp.currentMap){
            case 0: worldX = gp.tileSize * 30; worldY = gp.tileSize * 26; break;
            case 1: worldX = gp.tileSize * 41; worldY = gp.tileSize * 40; break;
            case 2: 
            case 3: worldX = gp.tileSize * 17; worldY = gp.tileSize * 32; break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8: worldX = gp.tileSize * 31; worldY = gp.tileSize * 32; break;
            case 9: worldX = gp.tileSize * 31; worldY = gp.tileSize * 38; break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14: worldX = gp.tileSize * 21; worldY = gp.tileSize * 35; break;
            default: 
        }
        direction = "down";

    }

    public void setDialogue(){

        dialogue[0][0] = "You leveled up! Your level now is " + level + "!\n" + "You feel stronger!";

    }

    public void restoreStatus(){

        HP = maxHP;
        MP = maxMP;
        invulnerable = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockback = false;
        lightUpdated = true;
        gp.bossBattleOn = false;
    }

    public void setItems(){
        inventory.clear();
        inventory.add(new Object_Health_Potion_Small(gp));
        inventory.add(new Object_Torch(gp));
    }

    public int getAttack(){
        if (currentWeapon != null){
            attackArea = currentWeapon.attackArea;
            motion1_duration = currentWeapon.motion1_duration;
            motion2_duration = currentWeapon.motion2_duration;
            return attack = strength * currentWeapon.attackValue; // Attack scales from Strength
        }
        else{
            attack = 0;
            return attack;
        }        
    }

    public int getDefense(){
        if (currentShield != null){
            return defense = dexterity * currentShield.defenseValue; // Defense scales from Dexterity
        }
        else{
            defense = 0;
            return defense;
        }
    }

    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot(){
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i) == currentShield){
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }

    public void getImage(){

        up1 = setup("/player/player_girl_up1", gp.tileSize, gp.tileSize);
        up2 = setup ("/player/player_girl_up2", gp.tileSize, gp.tileSize);
        down1 = setup ("/player/player_girl_down1", gp.tileSize, gp.tileSize);
        down2 = setup ("/player/player_girl_down2", gp.tileSize, gp.tileSize);
        left1 = setup ("/player/player_girl_left1", gp.tileSize, gp.tileSize);
        left2 = setup ("/player/player_girl_left2", gp.tileSize, gp.tileSize);
        right1 = setup ("/player/player_girl_right1", gp.tileSize, gp.tileSize);
        right2 = setup ("/player/player_girl_right2", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image){
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    public void getAttackImage(){

        if (currentWeapon != null){
            if (currentWeapon.name == "Tinvaak Sword"){
                attackUp1 = setup("/player/player_girl_attack_sword_up1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/player/player_girl_attack_sword_up2", gp.tileSize, gp.tileSize* 2);
                attackDown1 = setup("/player/player_girl_attack_sword_down1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/player_girl_attack_sword_down2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/player_girl_attack_sword_left1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/player/player_girl_attack_sword_left2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/player/player_girl_attack_sword_right1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/player/player_girl_attack_sword_right2", gp.tileSize * 2, gp.tileSize);
            }

            if (currentWeapon.name == "Axe"){
                attackUp1 = setup("/player/player_girl_attack_axe_up1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/player/player_girl_attack_axe_up2", gp.tileSize, gp.tileSize* 2);
                attackDown1 = setup("/player/player_girl_attack_axe_down1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/player_girl_attack_axe_down2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/player_girl_attack_axe_left1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/player/player_girl_attack_axe_left2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/player/player_girl_attack_axe_right1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/player/player_girl_attack_axe_right2", gp.tileSize * 2, gp.tileSize);
            }

            if (currentWeapon.name == "Vorlorn Sword"){
                attackUp1 = setup("/player/player_attack_vorlorn_up_1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/player/player_attack_vorlorn_up_2", gp.tileSize, gp.tileSize* 2);
                attackDown1 = setup("/player/player_attack_vorlorn_down_1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/player_attack_vorlorn_down_2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/player_attack_vorlorn_left_1", gp.tileSize * 2, gp.tileSize);
                attackLeft2 = setup("/player/player_attack_vorlorn_left_2", gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/player/player_attack_vorlorn_right_1", gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/player/player_attack_vorlorn_right_2", gp.tileSize * 2, gp.tileSize);
            }

        }   
    }

    public void getGuardImage(){
        
        if (currentShield != null){
            if (currentShield.name == "Tinvaak Shield"){
                guardUp = setup("/player/player_shield_tinvaak_up_1", gp.tileSize, gp.tileSize);
                guardDown = setup("/player/player_shield_tinvaak_down_1", gp.tileSize, gp.tileSize);
                guardLeft = setup("/player/player_shield_tinvaak_left_1", gp.tileSize, gp.tileSize);
                guardRight = setup("/player/player_shield_tinvaak_right_1", gp.tileSize, gp.tileSize);
            }
            if (currentShield.name == "Victoria Shield"){
                guardUp = setup("/player/player_shield_victoria_up_1", gp.tileSize, gp.tileSize);
                guardDown = setup("/player/player_shield_victoria_down_1", gp.tileSize, gp.tileSize);
                guardLeft = setup("/player/player_shield_victoria_left_1", gp.tileSize, gp.tileSize);
                guardRight = setup("/player/player_shield_victoria_right_1", gp.tileSize, gp.tileSize);
            }

            if (currentShield.name == "Wooden Shield"){
                guardUp = setup("/player/player_shield_wood_up_1", gp.tileSize, gp.tileSize);
                guardDown = setup("/player/player_shield_wood_down_1", gp.tileSize, gp.tileSize);
                guardLeft = setup("/player/player_shield_wood_left_1", gp.tileSize, gp.tileSize);
                guardRight = setup("/player/player_shield_wood_right_1", gp.tileSize, gp.tileSize);
            }

        }
    }
    

    public void update(){

        if (knockback == true){

            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            gp.collisionChecker.checkObject(this, true);
            gp.collisionChecker.checkEntity(this, gp.npc);
            gp.collisionChecker.checkEntity(this, gp.enemy);
            gp.collisionChecker.checkEntity(this, gp.iTile);

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

        else if (keyH.spacePressed == true && currentShield != null){
            guarding = true;
            guardCounter++;
        }

        else if ((keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) ||
            (keyH.ePressed == true && currentWeapon != null)){

            if (keyH.upPressed == true){
                direction  = "up";
            }
            else if (keyH.downPressed == true){
                direction = "down";
            }
            else if (keyH.leftPressed == true){
                direction = "left";
            }
            else if (keyH.rightPressed == true){
                direction = "right";
            }

            if (keyH.shiftPressed == true){
                if (hasBoots == true){
                    speed = 6;
                }
            }
            else if (keyH.shiftPressed == false){
                speed = defaultSpeed;
            }

            if (keyH.ePressed == true && currentWeapon !=  null){
                attacking = true;
                gp.playSoundEffect(8);
            }
            
            // Check Tile Collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // Check Object Collision
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // Check NPC Collision
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check Enemy Collision
            int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
            contactEnemy(enemyIndex);

            // Check Interactive Tile Collision
            gp.collisionChecker.checkEntity(this, gp.iTile);


            // Check Event Collision
            gp.eHandler.checkEvent();

            // If Collision is false, Player can move
            if (collisionOn == false && keyH.enterPressed == false){
                switch(direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

        
            gp.keyH.enterPressed = false; // Used here to toggle enter to false after checking entity collision for dialogue box
            guarding = false;
            guardCounter = 0;

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
        else{
            snapFirstSpriteCounter++;
            if (snapFirstSpriteCounter == 15){
                spriteNumber = 1; 
                snapFirstSpriteCounter = 0;
            }
            guarding = false;
            guardCounter = 0;
        }

        if (gp.keyH.shootPressed == true && projectile.alive == false && shootCounter == 30 
            && projectile.enoughCost(this) == true){
            // Set Defautlt Coordinates, Direction, and user
            projectile.set(worldX, worldY, direction, true, this);

            // Subtract Cost
            projectile.payCost(this);

            // Check Vacancy, then add
            for (int i = 0; i < gp.projectile[1].length; i++){
                if (gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shootCounter = 0;

            gp.playSoundEffect(11);
        }


        if (invulnerable == true){
            invulnerableCounter++;
            if (invulnerableCounter > 60){
                invulnerable = false;
                transparent = false;
                invulnerableCounter = 0;
            }
        }
        if (shootCounter < 30){
            shootCounter++;
        }
        if (HP > maxHP){
            HP = maxHP;
        }
        if (MP > maxMP){
            MP = maxMP;
        }
        if (keyH.godModeOn == false){
            if (HP <= 0){
                gp.gameState = gp.gameOverState;
                gp.stopMusic();
                gp.playSoundEffect(13);
            }
        }
        

    }


    public void pickUpObject(int i){

        if (i != 999){

            // Pickup Only Items
            if (gp.obj[gp.currentMap][i].type == type_pickuponly){

                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;

            }

            // Obstacle
            else if (gp.obj[gp.currentMap][i].type == type_obstacle){
                if (keyH.enterPressed == true){
                    gp.obj[gp.currentMap][i].interact();
                }
            }

            // Inventory Items
            else {

                String text;

                if (canObtainItem(gp.obj[gp.currentMap][i]) == true){
                    gp.playSoundEffect(2);
                    text = "Picked up " + gp.obj[gp.currentMap][i].name + "!";
                }
                else{
                    text = "You cannot carry any more items!";
                }   
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            
            }
        }
    }

    public void interactNPC(int i){
         if (i != 999){
            if (gp.keyH.enterPressed == true){
                gp.npc[gp.currentMap][i].speak();
            }
            gp.npc[gp.currentMap][i].move(direction);
        }
    }

    public void contactEnemy(int i){
        if (i != 999){
            if (invulnerable == false && gp.enemy[gp.currentMap][i].dying == false){
                gp.playSoundEffect(7);
                int damage; 
                if (gp.enemy[gp.currentMap][i].attack >= defense) {
                    damage = (gp.enemy[gp.currentMap][i].attack * 2) - defense;
                } 
                else {
                    damage = gp.enemy[gp.currentMap][i].attack * (gp.enemy[gp.currentMap][i].attack / defense);
                }
                
                if (damage < 1){
                    damage = 1;
                }
                HP -= damage;
                invulnerable = true;
                transparent = true;
            }
        }
    }

    public void damageEnemy(int i, Entity attacker, int attack, int knockbackPower){
        if (i != 999){
            if (gp.enemy[gp.currentMap][i].invulnerable == false){
                gp.playSoundEffect(6);
                if (knockbackPower > 0){
                    setKnockback(gp.enemy[gp.currentMap][i], attacker, knockbackPower);
                }

                if (gp.enemy[gp.currentMap][i].parried == true){
                    attack *= 3;
                }

                int damage;
                if (attack >= gp.enemy[gp.currentMap][i].defense) {
                    damage = (attack * 2) - gp.enemy[gp.currentMap][i].defense;
                } 
                else {
                    damage = attack * (attack / gp.enemy[gp.currentMap][i].defense);
                }
                if (damage < 1){
                    damage = 1;
                }
                gp.enemy[gp.currentMap][i].HP -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.enemy[gp.currentMap][i].invulnerable = true;
                gp.enemy[gp.currentMap][i].damageReaction();

                if (gp.enemy[gp.currentMap][i].HP <= 0){
                    gp.enemy[gp.currentMap][i].dying = true;
                    gp.ui.addMessage(gp.enemy[gp.currentMap][i].EXP + " EXP gained!");
                    EXP += gp.enemy[gp.currentMap][i].EXP;
                    checkLevelUp();
                }
            }
        }
    }

    public void interactTile(int i){

        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
            && gp.iTile[gp.currentMap][i].invulnerable == false){
            gp.iTile[gp.currentMap][i].playSoundEffect();
            gp.iTile[gp.currentMap][i].HP--;
            gp.iTile[gp.currentMap][i].invulnerable = true;

            // Generate Particle
            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].HP == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }

    }

    public void damageProjectile(int i){
        if (i != 999){
            Entity projectile  = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    

    public void checkLevelUp(){
        while (EXP >= nextLevelEXP){
            level++;
            EXP -= nextLevelEXP;
            nextLevelEXP *= 1.5;
            maxHP += 2;
            maxMP += 1;
            HP = maxHP;
            MP = maxMP;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.playSoundEffect(9);
            setDialogue();
            startDialogue(this, 0);
        }
    }

    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotColumn, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()){

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }

            if (selectedItem.type == type_shield ){
                currentShield = selectedItem;
                defense = getDefense();
                getGuardImage();
            }

            if (selectedItem.type == type_consumable ){
                if (selectedItem.use(this) == true){
                    if (selectedItem.amount > 1){
                        selectedItem.amount--;
                    }
                    else{
                        inventory.remove(itemIndex);
                    }
                }   
            }

            if (selectedItem.type == type_light ){
                if (currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }

        }

    }

    public int searchItemInInventory(String itemName){

        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item){

        boolean canObtain = false;

        Entity newItem = gp.eGenerator.getObject(item.name);

        // Check if item is stackable
        if (newItem.stackable == true){

            int index = searchItemInInventory(newItem.name);

            if (index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{ // New item so check vacancy
                if (inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }

        else { // Not stackable
            if (inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
        }
        return canObtain;
    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case "up":
                if (attacking == false){
                    if (spriteNumber == 1){image = up1;} 
                    if (spriteNumber == 2){image = up2;} 
                }
                if (attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1){image = attackUp1;} 
                    if (spriteNumber == 2){image = attackUp2;} 
                } 
                if (guarding == true){
                    image = guardUp;
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
                if (guarding == true){
                    image = guardDown;
                }
                break;

            case "left":
                if (attacking == false){
                    if (spriteNumber == 1){image = left1;} 
                    if (spriteNumber == 2){image = left2;} 
                }
                if (attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1){image = attackLeft1;} 
                    if (spriteNumber == 2){image = attackLeft2;} 
                }
                if (guarding == true){
                    image = guardLeft;
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
                if (guarding == true){
                    image = guardRight;
                }
                break; 
        }

        if (transparent == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        if (drawing == true){
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }   

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
