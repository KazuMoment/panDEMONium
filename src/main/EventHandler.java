package main;

import entity.Entity;

public class EventHandler {

    GamePanel gp;
    EventRectangle eventRectangle[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTriggerEvent = true;
    int tempMap, tempColumn, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventMaster = new Entity(gp);

        eventRectangle = new EventRectangle[gp.maxMap][gp.maxWorldColumn][gp.maxWorldRow];

        int map = 0;
        int column = 0;
        int row = 0;

        while (map < gp.maxMap && column < gp.maxWorldColumn && row < gp.maxWorldRow){
            eventRectangle[map][column][row] = new EventRectangle();
            eventRectangle[map][column][row].x = 23;
            eventRectangle[map][column][row].y = 23;
            eventRectangle[map][column][row].width = 2;
            eventRectangle[map][column][row].height = 2;
            eventRectangle[map][column][row].eventRectangleDefaultX = eventRectangle[map][column][row].x;
            eventRectangle[map][column][row].eventRectangleDefaultY =  eventRectangle[map][column][row].y;
            column++;
            if (column == gp.maxWorldColumn){
                column = 0;
                row++;

                if (row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }

            }
        }

        setDialogue();
    }

    public void setDialogue(){

        eventMaster.dialogue[0][0] = "You fucked up.";
        eventMaster.dialogue[1][0] = "You drank the water.\nYour HP and MP have been recovered!\nProgress is saved.";

    }

    public void checkEvent(){
        // Check if player is more than one tile away after event
        int xDistance = Math.abs(gp.player.worldX - previousEventX); // Absolute value, takes positive
        int yDistance = Math.abs (gp.player.worldY - previousEventY);
        int distance = Math.max (xDistance, yDistance); // Picks the greater number of two integers, in this case either horizontal or vertical
        if (distance > gp.tileSize){
            canTriggerEvent = true;
        }

        if (canTriggerEvent == true){
            if (hit(0, 11, 32, "up") == true){teleport(1, 43, 41, gp.dungeon, gp.tinvaak_dungeon);} // dungeon 
            else if (hit(1, 45, 42, "right") == true){teleport(0, 11, 32, gp.outdoor, gp.tutorial_forest);} // back to forest 
            else if (hit(0, 40, 12, "up") == true){teleport(2, 25, 30, gp.outdoor, gp.merchant_tent);} // merchant tent 
            else if (hit(2, 25, 30, "down") == true){teleport(0, 40, 12, gp.outdoor, gp.tutorial_forest);} // back to forest 
            else if (hit (2, 25, 25, "up") == true){speak(gp.npc[2][0]);} // talk to merchant interacting with table
        }
    

    }

    public boolean hit (int map, int column, int row, String requiredDirection){

        boolean hit = false;

        if (map == gp.currentMap){

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRectangle[map][column][row].x = column * gp.tileSize + eventRectangle[map][column][row].x;
            eventRectangle[map][column][row].y = row * gp.tileSize + eventRectangle[map][column][row].y;

            if (gp.player.solidArea.intersects(eventRectangle[map][column][row]) && eventRectangle[map][column][row].eventDone == false){
                if (gp.player.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRectangle[map][column][row].x = eventRectangle[map][column][row].eventRectangleDefaultX;
            eventRectangle[map][column][row].y = eventRectangle[map][column][row].eventRectangleDefaultY;

        }

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.HP -= 1;
        canTriggerEvent = false;
    }

    public void healingPool (int gameState){
        if (gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.HP = gp.player.maxHP;
            gp.player.MP = gp.player.maxMP;
            gp.aSetter.setEnemy();
            gp.saveLoad.save();
        }
    }

    public void teleport(int map, int column, int row, int area, int level){
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        gp.nextLevel = level;
        tempMap = map;
        tempColumn = column;
        tempRow = row;
        
        canTriggerEvent = false;
        gp.playSoundEffect(14);

    }

    public void speak (Entity entity){
        if (gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            entity.speak();
        }
    }

}
