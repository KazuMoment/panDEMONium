package main;

import entity.Entity;

public class EventHandler {

    GamePanel gp;
    EventRectangle eventRectangle[][][];

    int previousEventX, previousEventY;
    boolean canTriggerEvent = true;
    int tempMap, tempColumn, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;

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
            if (hit(0, 27, 16, "right") == true){damagePit(gp.dialogueState);}
            else if (hit(0, 23, 12, "up") == true){healingPool(gp.dialogueState);}
            else if (hit(0, 10, 39, "up") == true){teleport(1, 12, 13);}
            else if (hit(1, 12, 13, "down") == true){teleport(0, 10, 39);}
            else if (hit(0, 12, 9, "any") == true){teleport(2, 9, 90);}
            else if (hit (1, 12, 9, "up") == true){speak(gp.npc[1][0]);}
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
        gp.ui.currentDialogue = "You fucked up.";
        gp.player.HP -= 1;
        canTriggerEvent = false;
    }

    public void healingPool (int gameState){
        if (gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drank the water.\nYour HP is recovered!";
            gp.player.HP = gp.player.maxHP;
            gp.player.MP = gp.player.maxMP;
            gp.aSetter.setEnemy();
        }
    }

    public void teleport(int map, int column, int row){
        gp.gameState = gp.transitionState;
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
