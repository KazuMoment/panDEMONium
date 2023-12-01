package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.Object_Gold;
import object.Object_Heart;
import object.Object_ManaCrystal;


public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    public Font munro;
    BufferedImage heart_full, heart_half, heart_empty, mana_empty, mana_full, gold;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();  
    public boolean gameFinished = false;
    public String currentDialogue = " ";
    public int commandNumber = 0;
    public int titleScreenState = 0; // 0 is the first screen
    public int playerSlotColumn = 0;
    public int playerSlotRow = 0;
    public int npcSlotColumn = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = ""; 
    boolean recordCounter = true;

    String sessionID;
    int currentSessionID = 1;
    String level;
    String maxHP;
    String hp;
    String maxMana;
    String mana;
    String strength;
    String dexterity;
    String player_gold;
    String weapon;
    String shield;
    String playtime;
    int delayCounter = 120;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/munro.ttf");
            munro = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create HUD Object
        Entity heart = new Object_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
        Entity mana = new Object_ManaCrystal(gp);
        mana_full = mana.image;
        mana_empty = mana.image2;
        Entity goldCoin = new Object_Gold(gp);
        gold = goldCoin.down1;
    }

    public void addMessage (String text){

        message.add(text);
        messageCounter.add(0);
        
    }

    public void draw (Graphics2D g2){
        
        this.g2 = g2;
        g2.setFont(munro);
        g2.setColor(Color.white);

        // Title State
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }


        // Play State
        if (gp.gameState == gp.playState){
            drawPlayerHP();
            drawEnemyHP();
            drawMessage();
        }

        // Pause State
        if (gp.gameState == gp.pauseState){
            drawPlayerHP();
            drawPauseScreen();
        }

        // Dialogue State
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

        // Character State
        if (gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        
        // Options State
        if (gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }

        // Game Over State
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        // Transition State
        if (gp.gameState == gp.transitionState){
            drawTransition();
        }
        // Shop State
        if (gp.gameState == gp.shopState){
            drawShopScreen();
        }
        // Sleep State
        if (gp.gameState == gp.sleepState){
            drawSleepScreen();
        }

        // Save State
        if (gp.gameState == gp.saveState){
            drawSaveScreen();
        }


    }

    public void drawPlayerHP(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        int iconSize = 32;
        int manaStartX = (gp.tileSize/2) - 5;
        int manaStartY = 0;
    
        // Draw Max HP
        while (i < gp.player.maxHP/2){
            g2.drawImage(heart_empty, x, y, iconSize, iconSize, null);
            i++;
            x += iconSize;
            manaStartY = y + 32;

            if (i % 8 == 0){
                x = gp.tileSize/2;
                y += iconSize;
            }
        }

        // Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // Draw Current HP
        while (i < gp.player.HP){
            g2.drawImage(heart_half, x, y, iconSize, iconSize, null);
            i++;
            if (i < gp.player.HP){
                g2.drawImage(heart_full, x, y, iconSize, iconSize, null);
            }
            i++;
            x += iconSize;

            if (i % 16 == 0){
                x = gp.tileSize/2;
                y += iconSize;
            }
        }

        // Draw Max Mana
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while (i < gp.player.maxMP){
            g2.drawImage(mana_empty, x, y, iconSize, iconSize, null);
            i++;
            x += 25;

            if (i % 10 == 0){
                x = manaStartX;
                y += iconSize;
            }
        }

        // Draw Current Mana
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while (i < gp.player.MP){
            g2.drawImage(mana_full, x, y, iconSize, iconSize, null);
            i++;
            x += 25;

            if (i % 10 == 0){
                x = manaStartX;
                y += iconSize;
            }
        }
    }

    public void drawEnemyHP(){

        for (int i = 0; i < gp.enemy[1].length; i++){

            Entity enemy = gp.enemy[gp.currentMap][i];

            if (enemy != null && enemy.inCamera() == true){

                if (enemy.hpBarOn == true && enemy.boss == false){

                    double oneScale = (double)gp.tileSize/enemy.maxHP;
                    double hpBarValue = oneScale * enemy.HP;

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(enemy.getScreenX() - 1, enemy.getScreenY() - 16, gp.tileSize + 2, 7);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(enemy.getScreenX(), enemy.getScreenY() - 15, (int)hpBarValue, 5);

                    enemy.hpBarCounter++;

                    if (enemy.hpBarCounter > 600){
                        enemy.hpBarCounter = 0;
                        enemy.hpBarOn = false;
                    }
                }

                else if (enemy.boss == true && gp.bossBattleOn == true){

                    double oneScale = (double)gp.tileSize * 8/enemy.maxHP;
                    double hpBarValue = oneScale * enemy.HP;

                    int x  = gp.screenWidth/2 - gp.tileSize * 4;
                    int y = gp.tileSize * 10;

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(x - 1, y - 1, gp.tileSize * 8 + 2, 22);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(x, y, (int)hpBarValue, 20);

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
                    g2.setColor(Color.white);
                    g2.drawString(enemy.name, x + 4, y - 10);

                }

            }
        }
    }


    public void drawMessage(){

        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));

        for (int i = 0; i < message.size(); i++){

            if (message.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // Message counter
                messageCounter.set(i, counter); // Set counter to array
                messageY += 50;

                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }

        }

    }
    
    public void drawTitleScreen(){
    
        if (titleScreenState == 0){
            
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Title Name 
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "panDEMONium";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // Shadow
            g2.setColor(new Color (66, 66, 66));
            g2.drawString(text, x + 5, y + 5);

            // Title Font Color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // Demon King Image
            x = gp.screenWidth/2 -(gp.tileSize * 2)/2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);


            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNumber == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNumber == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "RECORDS";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNumber == 2){
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNumber == 3){
                g2.drawString(">", x - gp.tileSize, y);
            }

            
        }
        else if (titleScreenState == 1){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(80F));

            String text = "Records";
            int  x = getXforCenteredText(text);
            int y = gp.tileSize * 2;
            g2.drawString(text, x, y);
            drawRecordsScreen();
            

        }
 
    }
    public void drawRecordsScreen(){
        delayCounter++;
        if(recordCounter && delayCounter >120){
            //RecordScreen Placeholder
            sessionID = gp.toSQL.getValue(1, currentSessionID);
            level = gp.toSQL.getValue(2, currentSessionID);
            maxHP = gp.toSQL.getValue(3, currentSessionID);
            hp = gp.toSQL.getValue(4, currentSessionID);
            maxMana = gp.toSQL.getValue(5, currentSessionID);
            mana = gp.toSQL.getValue(6, currentSessionID);
            strength = gp.toSQL.getValue(7, currentSessionID);
            dexterity = gp.toSQL.getValue(8, currentSessionID);
            player_gold = gp.toSQL.getValue(9, currentSessionID);
            weapon = gp.toSQL.getValue(10, currentSessionID);
            shield = gp.toSQL.getValue(11, currentSessionID);
            playtime = gp.toSQL.getValue(12, currentSessionID);
            recordCounter = false;
            delayCounter = 0;
        }

        // Create a frame using drawSubWindow()
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize * 3;
        final int frameWidth = gp.tileSize * 15;
        final int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 50;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        // Parameter names
        g2.drawString("SessionID", textX, textY);
        textY += lineHeight;
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("HP", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        textX += 300;
        textY = frameY + gp.tileSize;;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        
        g2.drawString("Gold", textX, textY); // Images are drawn next so spacing is compensated
        textY += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;
        g2.drawString("Playtime", textX, textY);
        textY += lineHeight * 3;
        textX -= 332;
        g2.drawString("Next: >", textX, textY);
        textY += lineHeight;
        g2.drawString("Prev: <", textX, textY);
        textY += lineHeight;
        g2.drawString("Back to Title Screen: ESC", textX, textY);

        // Display Parameter Values
        int tailX = (frameX + frameWidth) - 450;
        
        // Reset textY first before starting
        textY = frameY + gp.tileSize;
        String value;

        value = sessionID;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = level;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = maxHP + "/" + hp;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = maxMana + "/" + mana;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = strength;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        tailX = (frameX + frameWidth) - 50;
        textY = frameY + gp.tileSize;

        value = dexterity;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = player_gold;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = weapon;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = shield;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = playtime;
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen(){

        // Window
        int x = gp.tileSize * 3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc.dialogue[npc.dialogueSet][npc.dialogueIndex] != null){

            // currentDialogue = npc.dialogue[npc.dialogueSet][npc.dialogueIndex];

            char characters[] = npc.dialogue[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            if (charIndex < characters.length){
                // gp.playSoundEffect(19);
                String s  = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true){

                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState){
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else{ // If no text is in array
            npc.introDone = true;
            npc.dialogueIndex = 0;

            if (gp.gameState == gp.dialogueState){
                gp.gameState = gp.playState;
            }
            if (gp.gameState == gp.cutsceneState){
                gp.csManager.scenePhase++;
            }

        }

        for (String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawCharacterScreen(){

        // Create a frame using drawSubWindow()
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        // Text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        // Parameter names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("HP", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold", textX, textY); // Images are drawn next so spacing is compensated
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        // Display Parameter Values
        int tailX = (frameX + frameWidth) - 30;
        
        // Reset textY first before starting
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.HP + "/" + gp.player.maxHP);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.MP + "/" + gp.player.maxMP);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.defense);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.EXP);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelEXP);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.gold);
        textX = getXforAlignRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        if(gp.player.currentWeapon != null && gp.player.currentShield != null){
            g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
            textY += gp.tileSize;

            g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);
            textY += gp.tileSize;
        }

    }


    public void drawInventory(Entity entity, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;   
        int slotColumn = 0;
        int slotRow = 0;

        if (entity == gp.player){
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotColumn = playerSlotColumn;
            slotRow = playerSlotRow;
        }
        else{
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotColumn = npcSlotColumn;
            slotRow = npcSlotRow;
        }

        // Frame 
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        // Draw Items
        for (int i = 0; i < entity.inventory.size(); i++){

            // Equip Cursor
            if (entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i) == entity.currentShield ||
                    entity.inventory.get(i) == entity.currentLight){
                g2.setColor(new Color(67, 147, 173));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // Display amount of items
            if (entity == gp.player && entity.inventory.get(i).amount > 1){
                g2.setFont(g2.getFont().deriveFont(32F));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignRight(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                // Shadow
                g2.setColor(new Color (60, 60, 60));
                g2.drawString(s, amountX, amountY);
                // Number
                g2.setColor(Color.white);
                g2.drawString(s, amountX - 3, amountY - 3);

            }


            slotX += gp.tileSize;

            if (i == 4 || i == 9 || i == 14){
                slotX = slotXStart;
                slotY += slotSize;
            }

        }
    

        // Cursor
        if (cursor == true){
            int cursorX = slotXStart + (gp.tileSize * slotColumn);
            int cursorY = slotYStart + (gp.tileSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // Draw Cursor
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // Description Frame
            int descFrameX = frameX;
            int descFrameY = frameY + frameHeight;
            int descFrameWidth = frameWidth;
            int descFrameHeight = gp.tileSize * 3;
            

            // Draw Description text
            int textX = descFrameX + 20;
            int textY = descFrameY + 40;
            g2.setFont(g2.getFont().deriveFont(20f));

            int itemIndex = getItemIndexOnSlot(slotColumn, slotRow);

            if (itemIndex < entity.inventory.size()){

                drawSubWindow(descFrameX, descFrameY, descFrameWidth, descFrameHeight); // Moved from frame statement to hide when not selecting 

                for (String line : entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }   
                
            }
        }

    }

    public void drawGameOverScreen(){

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90f));

        text = "GAME OVER";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNumber == 0){
            g2.drawString(">", x - 40, y);
        }

        // Back to the Title Screen
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
         if (commandNumber == 1){
            g2.drawString(">", x - 40, y);
        }

    }

    public void drawOptionsScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullscreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }
        gp.keyH.enterPressed = false; // After options have been done, toggle enter 
    }

    public void options_top(int frameX, int frameY){

        int textX;
        int textY;


        // Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // Full Screen On/Off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNumber == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                if (gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                }
                else if (gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        // Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNumber == 1){
            g2.drawString(">", textX - 25, textY);
        }

        // Sound Effect
        textY += gp.tileSize;
        g2.drawString("Sound Effect", textX, textY);
        if (commandNumber == 2){
            g2.drawString(">", textX - 25, textY);
        }

        // Controls
        textY += gp.tileSize;
        g2.drawString("Controls", textX, textY);
        if (commandNumber == 3){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 2;
                commandNumber = 0;
            }
        }

        // End Game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNumber == 4){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 3;
                commandNumber = 0;
            }
        }

        // Back
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNumber == 5){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                gp.gameState = gp.playState;
                commandNumber = 0;
            }
        }

        // Full Screen Check Box
        textX = frameX + (int) (gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 24 ;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }

        // Music Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); // 120/5 = 24 pixels
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // Sound Effect Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.soundEffect.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void options_fullscreenNotification(int frameX, int frameY){

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        for (String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Back
        textY += frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNumber == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 0;
            }
        }

    }

    public void options_control(int frameX, int frameY){

        int textX;
        int textY;

        // Title
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Interact", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("E", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("T", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNumber == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                commandNumber = 3;
            }
        }

    }

    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Quit the game and\nreturn to title screen?";

        for (String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // Yes
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNumber == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                gp.stopMusic();
                gp.gameState = gp.titleState;
                gp.resetGame(true);
                titleScreenState = 0;
            }
        }

        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNumber == 1){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true){
                subState = 0;
                commandNumber = 4;
            }
        }

    }

    public void drawTransition(){

        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempColumn;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }
    }

    public void drawShopScreen(){

        switch(subState){
            case 0: shop_select(); break;
            case 1: shop_buy(); break;
            case 2: shop_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void shop_select(){
        drawDialogueScreen();

        // Draw Window
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 3;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        // Draw Dialogue Options
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNumber == 0){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true){
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNumber == 1){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNumber == 2){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true){
                commandNumber = 0;
                npc.startDialogue(npc, 2);
            }
        }
        y += gp.tileSize;



    }

    public void shop_buy(){

        // Draw Player Inventory
        drawInventory(gp.player, false);
        // Draw NPC Inventory
        drawInventory(npc, true);

        // Draw Hint Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // Draw Player Gold Window
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Gold: " + gp.player.gold, x + 24, y + 60);

        // Draw Price Window
        int itemIndex = getItemIndexOnSlot(npcSlotColumn, npcSlotRow);
        if (itemIndex < npc.inventory.size()){

            x = (int)(gp.tileSize * 5.5);
            y = (int)(gp.tileSize * 5.5);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(gold, x + 10, y + 8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignRight(text, gp.tileSize * 8 - 20);
            g2.drawString(text, x, y + 34);

            // Buy Item
            if (gp.keyH.enterPressed == true){
                if (npc.inventory.get(itemIndex).price > gp.player.gold){
                    subState = 0;
                    npc.startDialogue(npc, 3);
                }
                else {
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        gp.player.gold -= npc.inventory.get(itemIndex).price;
                    }
                    else {
                        subState = 0;
                        npc.startDialogue(npc, 4);
                    }  
                }
            }
        }


    }

    public void shop_sell(){

        // Draw Player Inventory
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;

        // Draw Hint Window
        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        // Draw Player Gold Window
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Gold: " + gp.player.gold, x + 24, y + 60);

        // Draw Price Window
        int itemIndex = getItemIndexOnSlot(playerSlotColumn, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()){
            
            x = (int)(gp.tileSize * 15.5);
            y = (int)(gp.tileSize * 5.5);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(gold, x + 10, y + 8, 32, 32, null);

            int price = (int) (gp.player.inventory.get(itemIndex).price - (gp.player.inventory.get(itemIndex).price * .20));
            String text = "" + price;
            x = getXforAlignRight(text, gp.tileSize * 18 - 20);
            g2.drawString(text, x, y + 34);

            // Sell Item
            if (gp.keyH.enterPressed == true){
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                    gp.player.inventory.get(itemIndex) == gp.player.currentShield ||
                    gp.player.inventory.get(itemIndex) == gp.player.currentLight){
                        commandNumber = 0;
                        subState = 0;
                        npc.startDialogue(npc, 5);
                    }
                else {
                    if (gp.player.inventory.get(itemIndex).amount > 1){
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.gold += price;
                }
            }   
        }
    }

    public void drawSleepScreen(){
        
        counter++;

        if (counter < 120){
            gp.eManager.lighting.filterAlpha += 0.01f;
            if (gp.eManager.lighting.filterAlpha > 1f){
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120){
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if (gp.eManager.lighting.filterAlpha <= 0f){
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }

    public void drawSaveScreen(){

        drawDialogueScreen();

        // Draw Window
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 3;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        // Draw Dialogue Options
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Yes", x, y);
        if (commandNumber == 0){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true){
                gp.player.HP = gp.player.maxHP;
                gp.player.MP = gp.player.maxMP;
                gp.saveLoad.save();
                npc.startDialogue(npc, 2);
                gp.gameState = gp.sleepState;
                gp.aSetter.setEnemy();
                gp.playSoundEffect(16);
            }
        }
        y += gp.tileSize;
        g2.drawString("No", x, y);
        if (commandNumber == 1){
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true){
                commandNumber = 0;
                npc.startDialogue(npc, 2);
            }
        }
        gp.keyH.enterPressed = false;
    }

    public int getItemIndexOnSlot(int slotColumn, int slotRow){
        int itemIndex = slotColumn + (slotRow * 5);
        return itemIndex;
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c =  new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText (String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public int getXforAlignRight (String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    
}
