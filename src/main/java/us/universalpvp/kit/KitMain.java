package us.universalpvp.kit;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import us.universalpvp.kit.api.KitAPI;
import us.universalpvp.kit.commands.KitCommands;
import us.universalpvp.kit.event.listeners.GUIInteractionListener;

import java.io.File;

/**
 * Created by avigh on 7/23/2016.
 */
public class KitMain extends JavaPlugin {

    private static FileConfiguration kits;

    @Override
    public void onEnable() {
        createKitFile();

        KitAPI.initialise(this);
        KitAPI.getAPI().loadKits();

        getServer().getPluginManager().registerEvents(new GUIInteractionListener(this), this);
        getCommand("kits").setExecutor(new KitCommands(this));

        getConfig().addDefault("No-Permission", ChatColor.RED + "You do not have permission to perform this command!");
        getConfig().addDefault("Kit-Received", "");
        getConfig().addDefault("Enable-Title", false);
        getConfig().addDefault("Title", "");
        getConfig().addDefault("Subtitle", "");

        saveDefaultConfig();
    }

    public FileConfiguration getKitsConfig() {
        return kits;
    }


    private void createKitFile() {

        File kitsFile = new File(getDataFolder(), "kits.yml");
        kits = YamlConfiguration.loadConfiguration(kitsFile);

        if (!kitsFile.exists()) {
            kitsFile.getParentFile().mkdirs();
            saveResource("kits.yml", false);
        }

    }

    public void saveKitsConfig() {
        saveResource("kits.yml", false);
    }

}
