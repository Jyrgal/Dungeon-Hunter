package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Direction;
import unsw.dungeon.EntityList.*;

class portalInteractTest {
	// p1 and d1 tests the player move boundaries independent of obstacles.

	Dungeon dungeon = new Dungeon(6,6);
	Player player = new Player(dungeon, 2, 2);
	Portal p1 = new Portal(2,1,0);
    Portal p2 = new Portal(4,1,0);
    Portal p3 = new Portal(1,2,1);
    Portal p4 = new Portal(1,4,1);
    Enemies e1 = new Enemies(0,2);

	@Test
	void playerTeleport() {
		dungeon.addEntity(p1);
        dungeon.addEntity(p2);
		dungeon.setPlayer(player);
		
		player.moveUp();
		Direction moveUP = Direction.UP;
		dungeon.playerInteract(2, 1, moveUP);
		assertEquals(player.getX(), 4, "teleport didnt work x");
		assertEquals(player.getY(), 1, "teleport didnt work y");
	}

	@Test
	void enemyTeleport() {
		dungeon.setPlayer(player);
		dungeon.addEntity(p3);
        dungeon.addEntity(p4);
        dungeon.addEntity(e1);
        dungeon.makeGraph();

		player.moveRight();
		Direction moveRIGHT = Direction.RIGHT;
		dungeon.playerInteract(3, 2, moveRIGHT);
		assertEquals(player.getX(), 3, "player didnt move properly");
		assertEquals(e1.getX(), 1, "enemy didnt teleport x");
        assertEquals(e1.getY(), 4, "enemy didnt teleport y");
	}
}
