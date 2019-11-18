package unsw.dungeon.observerEntity;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.EntityList.Player;

public interface entityObserver {
	public void updateEntityObserver(Dungeon dungeon, Entity entity, boolean complete);
}
