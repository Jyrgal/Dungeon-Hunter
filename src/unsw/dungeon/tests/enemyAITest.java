package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Direction;
import unsw.dungeon.EntityList.Enemies;
import unsw.dungeon.EntityList.Player;
import unsw.dungeon.EntityList.Potion;
import unsw.dungeon.EntityList.Sword;
import unsw.dungeon.EntityList.Wall;

class enemyAITest {
	Dungeon dungeon = new Dungeon(6,6);
	Player player = new Player(dungeon, 2, 2);
	Enemies e1 = new Enemies(2,4);
	Enemies e2 = new Enemies(0,2);
	Enemies e3 = new Enemies(3,2);
	Wall w1 = new Wall(1,2);
	Sword s1 = new Sword(0,0);
	Potion p1 = new Potion(0,0);

	@Test
	void enemyWallMove() {
		dungeon.setPlayer(player);
		dungeon.addEntity(e2);
		dungeon.addEntity(w1);
		
		player.moveRight();	
		assertEquals(e2.getX(), 0, "enemy moved x incorrectly");
		assertEquals(e2.getY(), 1, "enemy moved y incorrectly");
		player.moveRight();	
		assertEquals(e2.getX(), 1, "enemy moved x incorrectly");
		assertEquals(e2.getY(), 1, "enemy moved y incorrectly");
		player.moveRight();
		assertEquals(e2.getX(), 2, "enemy moved x incorrectly");
		assertEquals(e2.getY(), 1, "enemy moved y incorrectly");
		player.moveUp();
		assertEquals(e2.getX(), 3, "enemy moved x incorrectly");
		assertEquals(e2.getY(), 1, "enemy moved y incorrectly");
	}
	
}
