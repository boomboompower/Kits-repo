package us.universalpvp.kit.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created by avigh on 7/26/2016.
 */
public class KitGUI {

    /**
     *
     * @param player The player to open the GUI to
     * @param plugin The main class.
     */
    public void openGui(Player player, JavaPlugin plugin) {
        List<Kit> kits = KitAPI.getAPI().getRegisteredKits();

        Inventory gui = Bukkit.createInventory(null, 54,
                plugin.getConfig().getConfigurationSection("Gui").getString("name"));

        for (Kit k : kits) {
            ItemMeta meta = k.getGuiItem().getItemMeta();
            meta.setDisplayName(KitAPI.getAPI().color("The " + k.getName() + " kit!"));
            meta.setLore(k.getDescription());
            k.getGuiItem().setItemMeta(meta);

            gui.addItem(k.getGuiItem());
        }

        player.openInventory(gui);
    }

}

