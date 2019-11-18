package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Direction;
import unsw.dungeon.EntityList.*;

class potionInteractTest {
	// p1 and d1 tests the player move boundaries independent of obstacles.

	Dungeon dungeon = new Dungeon(6,6);
	Player player = new Player(dungeon, 2, 2);
	Potion p1 = new Potion (2,1);
	Enemies e1 = new Enemies(2,4);

	@Test
	void collectPotion() {
		dungeon.addEntity(p1);
		dungeon.setPlayer(player);

		Direction direction = Direction.UP;
		assertEquals(dungeon.move(2, 1, direction), true, "potion should be moveable");

		player.moveUp();
		dungeon.playerInteract(2, 1, direction);
		
		assertEquals(player.checkInventory(p1), true, "potion not picked up");
		assertEquals(dungeon.findSpecificEntity(p1), null, "potion still in dungeon");
	}

	@Test
	void potionDuration() {
		dungeon.addEntity(p1);
		dungeon.setPlayer(player);

		Direction direction = Direction.UP;
		assertEquals(dungeon.move(2, 1, direction), true, "potion should be moveable");

		player.moveUp();
		dungeon.playerInteract(2, 1, direction);
		
		assertEquals(p1.potionDuration(), 5, "potion should have original duration");
		player.moveLeft();
		direction = Direction.LEFT;
		dungeon.playerInteract(1, 1, direction);
		assertEquals(p1.potionDuration(), 4, "potion duration shouldve decreased by 1");
	}
}
