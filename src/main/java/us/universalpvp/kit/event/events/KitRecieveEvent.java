package us.universalpvp.kit.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.universalpvp.kit.api.Kit;

/**
 * Created by avigh on 7/24/2016.
 */
public class KitRecieveEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private Player receiver;
    private Kit kit;

    public KitRecieveEvent(Player receiver, Kit kit) {
        this.receiver = receiver;
        this.kit = kit;
    }

    public Kit getKit() {
        return kit;
    }

    public Player getPlayer() {
        return receiver;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}
