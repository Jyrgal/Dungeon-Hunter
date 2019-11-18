package unsw.dungeon.interactEntity;

import unsw.dungeon.*;
import unsw.dungeon.EntityList.*;

public class EnemyInteract implements Interact{
	@Override
	public void entityInteract(Dungeon dungeon, Entity enemy, Direction direction) {
		Player player = dungeon.getPlayer();
		if (((Enemies)enemy).enemyDead()) {
			return;
		}
		if (player.checkSword()) {
			System.out.println("shoudl die");
			((Enemies)enemy).notifyEntityObserver(dungeon, true);
			((Enemies)enemy).die(dungeon);
			Sword sword = (Sword)player.findSword();
			sword.use(player);
		}	else if (player.checkPotion()){
			System.out.println("shoudl die");
			((Enemies)enemy).notifyEntityObserver(dungeon, true);
			((Enemies)enemy).die(dungeon);			
		}	else {
			player.die();
		}
	}
	
	@Override
	public boolean checkMove(Dungeon dungeon, Entity entity, Direction direction) {
		return true;
	}
}
