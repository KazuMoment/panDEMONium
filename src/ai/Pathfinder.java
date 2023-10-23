package ai;

import java.util.ArrayList;

import main.GamePanel;

public class Pathfinder {
    
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false; 
    int step = 0;

    public Pathfinder(GamePanel gp){
        this.gp = gp;
        instantiateNode();
    }

    public void instantiateNode(){
        node = new Node[gp.maxWorldColumn][gp.maxWorldRow];
        
        int column = 0;
        int row = 0;

        while (column < gp.maxWorldColumn && row < gp.maxWorldRow){
            node[column][row] = new Node(column, row);

            column++;

            if (column == gp.maxWorldColumn){
                column = 0;
                row++;
            }
        }

    }

    public void resetNodes(){

        int column = 0;
        int row = 0;

        while (column < gp.maxWorldColumn && row < gp.maxWorldRow){
            // Reset Open, Checked, and Solid State
            node[column][row].open = false;
            node[column][row].checked = false;
            node[column][row].solid = false;

            column++;
            if (column == gp.maxWorldColumn){
                column = 0;
                row++;
            }
        }

        // Reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startColumn, int startRow, int goalColumn, int goalRow){
        
        resetNodes();

        // Set Start and Goal Node
        startNode = node[startColumn][startRow];
        currentNode = startNode;
        goalNode = node[goalColumn][goalRow];
        openList.add(currentNode);

        int column = 0;
        int row = 0;
        while (column < gp.maxWorldColumn && row < gp.maxWorldRow){

            // Set Solid State
            // Check Tiles
            int tileNumber = gp.tileM.mapTileNumber[gp.currentMap][column][row];
            if (gp.tileM.tile[tileNumber].collision == true){
                node[column][row].solid = true;
            }
            // Check Interactive Tiles
            for (int i = 0; i < gp.iTile[1].length; i++){
                if (gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible == true){
                    int itColumn = gp.iTile[gp.currentMap][i].worldX/gp.tileSize;
                    int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tileSize;
                    node[itColumn][itRow].solid = true;
                }
            }
            // Set Cost
            getCost(node[column][row]);

            column++;
            if (column == gp.maxWorldColumn){
                column = 0;
                row++;
            }
        }

    }

    public void getCost(Node node){
        // Get G Cost (Distance from Start Node)
        int xDistance = Math.abs(node.column - startNode.column);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // Get H Cost (Distance from Goal Node)
        xDistance = Math.abs(node.column - goalNode.column);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // Get F Cost (G + H Cost)
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search(){
        while (goalReached == false && step < 500){
            int column = currentNode.column;
            int row = currentNode.row;

            // Check Current Node;
            currentNode.checked = true;
            openList.remove(currentNode);

            // Open the Up Node
            if (row - 1 >= 0){
                openNode(node[column][row - 1]);
            }
            // Open the Left Node
            if (column - 1 >= 0){
                openNode(node[column - 1][row]);
            }
            // Open the Down Node
            if (row + 1 < gp.maxWorldRow){
                openNode(node[column][row + 1]);
            }  
            // Open the Right Node
            if (column + 1 < gp.maxWorldColumn){
                openNode(node[column + 1][row]);
            }

            // Find the Best Node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++){

                // Check if this node's F Cost is better
                if (openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // If F Cost is equal, check G Cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                } 

            }
            // If there is no node in the open list, end loop
            if (openList.size() == 0){
                break;
            }

            // After the loop, openList [bestNodeIndex] is the next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode){
                goalReached = true;
                trackPath();
            }
            step++;
        }
        return goalReached;
    }   

    public void openNode(Node node){

        if (node.open == false && node.checked == false && node.solid == false){

            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }   

    }

    public void trackPath(){
        Node current = goalNode;
        while (current != startNode){
            pathList.add(0, current);
            current = current.parent;
        }
    }

}
