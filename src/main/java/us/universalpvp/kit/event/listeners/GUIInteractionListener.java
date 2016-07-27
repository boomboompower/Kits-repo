package us.universalpvp.kit.event.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.universalpvp.kit.KitMain;
import us.universalpvp.kit.api.KitAPI;
import us.universalpvp.kit.event.events.KitReceiveEvent;

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

        if (e.getClickedInventory().getName().equalsIgnoreCase(plugin.getConfig().
                getConfigurationSection("Gui").getString("name"))) {

            KitAPI.getAPI().getRegisteredKits().stream().filter(k -> k.getGuiItem().isSimilar(e.getCurrentItem())).forEach(k -> {
                if (k.isPermissible() && p.hasPermission("kit." + k.getName().toLowerCase())) {
                    p.getInventory().addItem(k.getItems());
                    p.getInventory().setArmorContents(k.getArmor());

                } else {
                    p.getInventory().addItem(k.getItems());
                    p.getInventory().setArmorContents(k.getArmor());
                }

                Bukkit.getPluginManager().callEvent(new KitReceiveEvent(p, k));
                p.sendMessage(plugin.getConfig().getString("Kit-received"));
            });
        }
    }
}
