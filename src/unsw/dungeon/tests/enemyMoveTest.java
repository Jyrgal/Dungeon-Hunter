package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Direction;
import unsw.dungeon.EntityList.*;

class enemyMoveTest {
	Dungeon dungeon = new Dungeon(6,6);
	Player player = new Player(dungeon, 2, 2);
	Enemies e1 = new Enemies(2,4);
	Enemies e2 = new Enemies(0,2);
	Enemies e3 = new Enemies(3,2);
	Wall w1 = new Wall(1,2);
	Sword s1 = new Sword(0,0);
	Potion p1 = new Potion(0,0);

	@Test
	void enemyFreeMove() {
		dungeon.setPlayer(player);
		dungeon.addEntity(e1);
		dungeon.makeGraph();
		
		player.moveUp();
		Direction direction = Direction.UP;
		dungeon.playerInteract(2, 1, direction);

		assertEquals(e1.getX(), 2, "enemy moved x incorrectly");
		assertEquals(e1.getY(), 3, "enemy moved incorrectly");
	}
	
	@Test 
	void enemyPlayerPotionInteract() {
		dungeon.setPlayer(player);
		dungeon.addEntity(e3);
		player.addItem(p1);
		player.moveRight();
		Direction direction = Direction.RIGHT;
		dungeon.playerInteract(3, 2, direction);
		
		assertEquals(player.checkPotion(), true, "player has no potion effect");
		assertEquals(player.getX(), 3, "player is in wrong x");
		assertEquals(player.getY(), 2, "player is in wrong y");
		assertEquals(dungeon.findSpecificEntity(e3), null, "enemy was not found");
		assertEquals(e3.enemyDead(), true, "enemy should now die/become null");
	}
	
	@Test 
	void enemyPlayerSwordInteract() {
		dungeon.setPlayer(player);
		dungeon.addEntity(e3);
		
		//pseudo sword
		player.addItem(s1);
		
		player.moveRight();
		Direction direction = Direction.RIGHT;
		dungeon.playerInteract(3, 2, direction);
		
		assertEquals(player.checkSword(), true, "player has no potion effect");
		assertEquals(dungeon.findSpecificEntity(e3), null, "enemy was not found");
		assertEquals(e3.enemyDead(), true, "enemy should now die/become null");
		
		//testing durability
		assertEquals(s1.getDurability(), 4, "sword was not properly used");
	}
}
