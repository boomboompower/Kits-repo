package us.universalpvp.kit.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import us.universalpvp.kit.KitMain;

import java.util.List;

/**
 * Created by avigh on 7/26/2016.
 */
public class KitGUI {

    public void openGui(Player player, KitMain plugin) {
        List<Kit> kits = KitAPI.getAPI().getRegisteredKits();

        Inventory gui = Bukkit.createInventory(null, plugin.getKitsConfig().getInt("gui-size"),
                plugin.getKitsConfig().getString("gui-name"));

        for (Kit k : kits) {
            ItemMeta meta = k.getGuiItem().getItemMeta();
            meta.setDisplayName(k.getName());
            meta.setLore(k.getDescription());
            k.getGuiItem().setItemMeta(meta);

            gui.addItem(k.getGuiItem());
        }

        player.openInventory(gui);
    }

}
