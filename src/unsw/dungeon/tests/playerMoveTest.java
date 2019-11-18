package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Direction;
import unsw.dungeon.EntityList.*;

class playerMoveTest {
	// p1 and d1 tests the player move boundaries independent of obstacles.

	Dungeon dungeon = new Dungeon(6,6);
	Player player = new Player(dungeon, 2, 2);
	Boulder b1 = new Boulder(1,2);
	Boulder b2 = new Boulder(3,2);
	Switch f1 = new Switch (4,2,dungeon);
	Wall w1 = new Wall(0,2);
	Wall w2 = new Wall(2,0);
	Potion p1 = new Potion (2,1);
	Sword s1 = new Sword(2,3);
	Door d1 = new Door(0,3,0);
	Key k1 = new Key(1,3,0);
	Door d2 = new Door(3,3,1);
	Enemies e1 = new Enemies(2,4);


	@Test
	void boulderWall() {
		dungeon.addEntity(b1);
		dungeon.addEntity(b2);
		dungeon.addEntity(w1);
		dungeon.addEntity(w2);
		dungeon.addEntity(s1);
		dungeon.addEntity(f1);
		dungeon.addEntity(p1);
		dungeon.setPlayer(player);

		ArrayList<Entity> entities = dungeon.findEntity(1, 2);
		
		//asserting boulder to the left with a wall behind boulder
		assertEquals(entities.size(), 1, "Incorrect number of entities found! Only 1 boulder");
		assertEquals(dungeon.checkTile(1,2), false, "Incorrect tile check! Boulder cannot be moved");
	}
	
	@Test
	void boulderSwitch() {
		dungeon.addEntity(b2);
		dungeon.addEntity(f1);
		dungeon.setPlayer(player);
		
		ArrayList<Entity> entities = dungeon.findEntity(3, 2);
		assertEquals(entities.get(0) instanceof Boulder, true, "THe boulder wasn't found!");
		assertEquals(entities.size(), 1, "Incorrect number of entities found! Only 1 boulder");
		assertEquals(dungeon.checkTile(4, 2), true, "should be switch");
		
		player.moveRight();
		Direction moveRight = Direction.RIGHT;
		dungeon.playerInteract(3, 2, moveRight);
		
		assertEquals(b2.getX(), 4, "non matching coords for switch & boulder");
		assertEquals(b2.getY() == f1.getY(), true, "non matching coords for switch & boulder");
		assertEquals(f1.isActivated(), true, "floorswitch should be activated");
		
		player.moveRight();
		dungeon.playerInteract(4, 2, moveRight);
		assertEquals(f1.isActivated(), false, "floorswitch should be activated");
	}
	
	@Test
	void collectSword() {
		dungeon.addEntity(s1);
		dungeon.setPlayer(player);
		
		Direction moveDOWN = Direction.DOWN;
		assertEquals(dungeon.move(2, 3, moveDOWN), true, "sword should be moveable");
		
		player.moveDown();
		dungeon.playerInteract(2, 3, moveDOWN);
		assertEquals(player.checkInventory(s1), true, "potion not picked up");
		assertEquals(dungeon.findSpecificEntity(s1), null, "potion still in dungeon");
	}
	
	@Test
	void unlockDoor() {
		dungeon.addEntity(d1);
		dungeon.addEntity(k1);
		dungeon.setPlayer(player);
		
		player.moveDown();
		Direction moveDOWN = Direction.DOWN;
		Direction moveLEFT = Direction.LEFT;
		dungeon.playerInteract(1, 3, moveDOWN);
		assertEquals(dungeon.move(1, 3, moveLEFT), true, "key should be moveable");
		assertEquals(player.checkInventory(k1), true, "key not picked up");
	
		player.moveLeft();
		dungeon.playerInteract(0, 3, moveLEFT);
		assertEquals(dungeon.move(0, 3, moveLEFT), true, "door should be unlocked");
		assertEquals(d1.checkLocked(), false, "door should be unlocked");
	}
	
	@Test
	void lockDoor() {
		dungeon.addEntity(d2);
		dungeon.setPlayer(player);
		Direction moveRight = Direction.RIGHT;
		assertEquals(dungeon.move(3, 3, moveRight), false, "locked door");
	}
	
	@Test
	void wrongDoor() {
		dungeon.addEntity(d2);
		dungeon.setPlayer(player);
		player.addItem(k1);
		Direction moveRight = Direction.RIGHT;
		assertEquals(dungeon.move(3, 3, moveRight), false, "wrong key door");
	}
	
}
