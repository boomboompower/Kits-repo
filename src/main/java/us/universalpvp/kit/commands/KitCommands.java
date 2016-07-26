package us.universalpvp.kit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.universalpvp.kit.KitMain;
import us.universalpvp.kit.api.KitAPI;

/**
 * Created by avigh on 7/26/2016.
 */
public class KitCommands implements CommandExecutor {

    private final KitMain plugin;

    public KitCommands(KitMain plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("kits")) {
                if (p.hasPermission("kit.kits"))
                    KitAPI.getGUI().openGui(p, plugin);
                else
                    p.sendMessage(plugin.getConfig().getString("no-permission"));
            }
        } else
            sender.sendMessage("Only players can use kits!");
        return false;
    }
}
