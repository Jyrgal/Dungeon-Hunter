package unsw.dungeon.EnemyStrategy;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;

public class PepegaCharge extends PathFind implements enemyState{

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
		
		if (playerX == enemyX && playerY == enemyY) {
			Direction direction = Direction.UP;
			enemy.interactEntity(dungeon, direction);
			return;
		}
		
//		if (enemyY < playerY) {
//			boolean moveable = dungeon.checkTile(enemyX, enemyY + 1);
//			if (moveable) {
//				enemy.moveDown(dungeon);
//				return;
//			}
//		}	else if (enemyY > playerY) {
//				boolean moveable= dungeon.checkTile(enemyX, enemyY - 1);
//				if (moveable) {
//					enemy.moveUp(dungeon);
//					return;
//				}	
//		}	else if (enemyX < playerX) {
//				boolean moveable = dungeon.checkTile(enemyX + 1, enemyY);
//				if (moveable) {
//					enemy.moveRight(dungeon);
//					return;
//				}	
//		}	else if (enemyX > playerX) {
//				boolean moveable = dungeon.checkTile(enemyX - 1, enemyY);
//				if (moveable) {
//					enemy.moveLeft(dungeon);
//					return;
//				}
//		}	
//		
//		boolean moveable = dungeon.checkTile(enemyX, enemyY + 1);
//		if (moveable) {
//			enemy.moveUp(dungeon);
//			return;
//		}
//		moveable = dungeon.checkTile(enemyX, enemyY - 1);
//		if (moveable) {
//			enemy.moveDown(dungeon);
//			return;
//		}
//		moveable = dungeon.checkTile(enemyX + 1, enemyY);
//		if (moveable) {
//			enemy.moveRight(dungeon);
//			return;
//		}	
//		moveable = dungeon.checkTile(enemyX - 1, enemyY);
//		if (moveable) {
//			enemy.moveLeft(dungeon);
//			return;
//		}
		//dungeon.makeGraph();
		int enemyPosition = enemyX + (enemyY * dungeon.getWidth());
		int playerPosition = playerX + (playerY * dungeon.getWidth());
		this.portalPoints = dungeon.getPortals();

		System.out.println(Integer.MAX_VALUE);
		V = dungeon.getHeight() * dungeon.getWidth();
		int distance = calculateDistance(dungeon.getMatrix(), enemyPosition, playerPosition, dungeon);
		if (distance == Integer.MAX_VALUE) {
			System.out.println("CANT FIND PATH");
			return;
		}
		
		
		for (Integer path: finalPath) {
			Point2D point = dungeon.convertInteger(path);
			System.out.println("final path is" + (int)point.getX() + ":" + (int)point.getY());
		}
		dijkstra(dungeon.getMatrix(), enemyPosition, playerPosition, dungeon);
		int nextMove = finalPath.get(1);
		Point2D newCoordinate = dungeon.convertInteger(nextMove);
		enemy.teleport((int)newCoordinate.getX(), (int)newCoordinate.getY());
	}
}