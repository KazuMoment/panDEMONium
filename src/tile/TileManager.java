package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumber[] [] []; 
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager (GamePanel gp){

        this.gp = gp;

        // Read Tile Data File
        InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Getting tile names and collisions info from file
        String line;
        
        try {
            while ((line = br.readLine()) != null){
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize tile array based on filenames size
        tile = new Tile[fileNames.size()];
        getTileImage();

        // Get max world row and column
        is = getClass().getResourceAsStream("/maps/forest_tutorial.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try{
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");

            gp.maxWorldColumn = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNumber = new int [gp.maxMap] [gp.maxWorldColumn] [gp.maxWorldRow];

            br.close();

        }catch(IOException e){
            System.out.println("Exception!");
        }
        
        loadMap("/maps/forest_tutorial.txt", 0); 
        loadMap("/maps/dungeon_1.txt", 1);
        loadMap("/maps/merchant_house.txt", 2);
        loadMap("/maps/village_1.txt", 3);
        loadMap("/maps/vilalge_2.txt", 4);
        
    }

    public void getTileImage(){

        for (int i = 0; i < fileNames.size(); i++){
            String fileName;
            boolean collision;

            // Get file name
            fileName = fileNames.get(i);
            
            // Get Collision Status
            if (collisionStatus.get(i).equals("true")){
                collision = true;
            }
            else {
                collision = false;
            }

            setup(i, fileName, collision);

        }

    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    public void loadMap(String filePath, int map){

        try{

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int column = 0;
            int row = 0;

            while (column < gp.maxWorldColumn && row  < gp.maxWorldRow){

                String line = br.readLine();

                while(column < gp.maxWorldColumn){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[column]);

                    mapTileNumber[map][column][row] = num;
                    column++;
                }
                if (column == gp.maxWorldColumn){

                    column = 0;
                    row++;

                }

            }
            br.close();

        }catch(Exception e){

        }

    }

    public void draw(Graphics2D g2){
        
        int worldColumn = 0; 
        int worldRow = 0;

        while (worldColumn < gp.maxWorldColumn && worldRow < gp.maxWorldRow){

            int tileNumber = mapTileNumber[gp.currentMap][worldColumn] [worldRow];

            int worldX = worldColumn * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNumber].image, screenX, screenY, null);

            }
            worldColumn++;

            if (worldColumn == gp.maxWorldColumn){
                worldColumn = 0;
                worldRow++;
            }

        }

    }

}
