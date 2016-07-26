package us.universalpvp.kit.api;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import us.universalpvp.kit.KitMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by avigh on 7/24/2016.
 */
public final class KitAPI {

    private static KitAPI api;
    private static KitGUI kitGUI;

    public static KitAPI getAPI() {
        return api;
    }

    public static KitGUI getGUI() {
        return kitGUI;
    }


    public static void initialise(JavaPlugin plugin) {
        if (api != null || kitGUI != null) {
            throw new IllegalStateException();
        }
        api = new KitAPI(plugin);
        kitGUI = new KitGUI();
    }

    private final JavaPlugin plugin;

    private List<Kit> registeredKits = new ArrayList<>();

    private KitAPI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadKits() {
        KitMain pl = (KitMain) plugin;

        ConfigurationSection cs = pl.getKitsConfig().getConfigurationSection("kits");

        for (String cKey : cs.getKeys(false)) {
            String name = cs.getString(cKey + "kit-name", cKey),
                    permission = cs.getString(cKey + "permission", "");

            int attack = cs.getInt(cKey + ".extra-attack", 0),
                    defence = cs.getInt(cKey + ".extra-defence", 0);

            double health = cs.getDouble(cKey + ".max-health", 20.0);
            List<String> description = cs.getStringList(cKey + ".description");

            ItemStack guiItem = new ItemStack(Material.getMaterial(cs.getString(cKey + ".gui-item").toUpperCase()));
            List<ItemStack> items = new ArrayList<>();

            ConfigurationSection ics = cs.getConfigurationSection("items");
            for (String iKey : ics.getKeys(false)) {
                int quantity = ics.getInt(iKey + ".quantity");
                Material material = Material.getMaterial(ics.getString(iKey + ".material"));

                ItemStack item = new ItemStack(material, quantity);
                ics.getStringList(iKey + ".enchantments")
                        .forEach(s -> item.addEnchantment(Enchantment.getByName(s.split(":")[0]),
                                Integer.parseInt(s.split(":")[1])));

                items.add(item);
            }

            Kit kit = new Kit(name, attack, defence, health, guiItem,
                    items.toArray(new ItemStack[items.size()]), description, permission);
            registerKit(kit);
        }
    }

    public List<Kit> getRegisteredKits() {
        return registeredKits;
    }

    public void registerKit(Kit... kits) {
        Arrays.asList(kits).forEach(registeredKits::add);
    }
}