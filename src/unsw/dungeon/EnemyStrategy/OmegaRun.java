package unsw.dungeon.EnemyStrategy;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.*;
import unsw.dungeon.EntityList.Enemies;
import unsw.dungeon.EntityList.Player;

public class OmegaRun extends PathFind implements enemyState{

	
    /**
     * Strategy design pattern to implement enemy movement
     * @param dungeon, the current dungeon 
     * @param enemy, the enemy to be moved
     * basic algorithm to help enemy move toward player through increments
     */
	@Override
	public void move(Dungeon dungeon, Enemies enemy) {
		Player player = dungeon.getPlayer();
		int playerX = player.getX();
		int playerY = player.getY();
		int enemyX = enemy.getX();
		int enemyY = enemy.getY();
//
//		if (enemyY < playerY) {
//			boolean moveable = dungeon.checkTile(enemyX, enemyY - 1);
//			if (moveable) {
//				enemy.moveUp(dungeon);
//				return;
//			}
//		}	else if (enemyY > playerY) {
//				boolean moveable= dungeon.checkTile(enemyX, enemyY + 1);
//				if (moveable) {
//					enemy.moveDown(dungeon);
//					return;
//				}
//		}	else if (enemyX < playerX) {
//				boolean moveable = dungeon.checkTile(enemyX - 1, enemyY);
//				if (moveable) {
//					enemy.moveRight(dungeon);
//					return;
//				}
//		}	else if (enemyX > playerX) {
//				boolean moveable = dungeon.checkTile(enemyX + 1, enemyY);
//				if (moveable) {
//					enemy.moveLeft(dungeon);
//					return;
//				}
//		}
//
//		boolean moveable = dungeon.checkTile(enemyX, enemyY - 1);
//		if (moveable) {
//			enemy.moveUp(dungeon);
//			return;
//		}
//		moveable = dungeon.checkTile(enemyX, enemyY + 1);
//		if (moveable) {
//			enemy.moveDown(dungeon);
//			return;
//		}
//		moveable = dungeon.checkTile(enemyX - 1, enemyY);
//		if (moveable) {
//			enemy.moveRight(dungeon);
//			return;
//		}	moveable = dungeon.checkTile(enemyX + 1, enemyY);
//		if (moveable) {
//			enemy.moveLeft(dungeon);
//			return;
//		}
		ArrayList<String> directions = new ArrayList<>();
		directions.add("up");
		directions.add("down");
		directions.add("right");
		directions.add("left");
    	Random rand = new Random();
        String direction = directions.get(rand.nextInt(directions.size()));
        
        boolean moved = false;
        while (moved == false) {
        	rand = new Random();
            direction = directions.get(rand.nextInt(directions.size()));
            System.out.println(direction);
	        switch (direction) {
	        case "up":
	        	if (dungeon.checkTile(enemyX, enemyY - 1)) {
	        		enemy.moveUp(dungeon);
	        		moved = true;
	        	}
	        	break;
	        case "down":
	        	if (dungeon.checkTile(enemyX, enemyY + 1)) {
	        		enemy.moveDown(dungeon);
	        		moved = true;
	        	}
	        	break;
	        case "right":
	        	if (dungeon.checkTile(enemyX + 1, enemyY)) {
	        		enemy.moveRight(dungeon);
	        		moved = true;
	        	}
	        	break;
	        case "left":
	        	if (dungeon.checkTile(enemyX - 1, enemyY)) {
	        		enemy.moveLeft(dungeon);
	        		moved = true;
	        	}
	        	break;  
	        default:
	        	break;
	        }
        }
//		dungeon.makeGraph();
//		int enemyPosition = enemyX + (enemyY * dungeon.getWidth());
//		int runX = dungeon.getWidth() - playerX - 1;
//		int runY = dungeon.getHeight() - playerY - 1;
//		int runPosition = runX + (runY * dungeon.getWidth());
//		dijkstra(dungeon.getMatrix(), enemyPosition, runPosition);
//		int nextMove;
//		if (finalPath.size() > 2) {
//			System.out.println("success dij");
//			nextMove = finalPath.get(1);
//		} 	else {
//			System.out.println("fail dij");
//			System.out.println(enemyPosition);
//			nextMove = enemyPosition;
//		}
//		Point2D newCoordinate = dungeon.convertInteger(nextMove);
//		enemy.teleport((int)newCoordinate.getX(), (int)newCoordinate.getY());	
	}
}
