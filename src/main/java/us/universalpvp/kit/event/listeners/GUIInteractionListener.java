package us.universalpvp.kit.event.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.universalpvp.kit.KitMain;

/**
 * Created by avigh on 7/24/2016.
 */
public class GUIInteractionListener implements Listener {
    private final KitMain plugin;

    public GUIInteractionListener(KitMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGuiInteract(final InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory().getName().equalsIgnoreCase(plugin.getKitsConfig().getString("gui-name"))) {

        }

    }




}