package unsw.dungeon.EnemyStrategy;
import unsw.dungeon.*;
import unsw.dungeon.EntityList.Enemies;
import unsw.dungeon.EntityList.Player;

public class KekDeath implements enemyState{

    /**
     * Strategy design pattern to implement enemy death
     * @param dungeon, the current dungeon 
     * @param enemy, the enemy to be removed or killed
     * remove from player observers
     */
	@Override
	public void move(Dungeon dungeon, Enemies enemy) {
		Player player = dungeon.getPlayer();
		player.removePlayerObserver(enemy);
	}
}
