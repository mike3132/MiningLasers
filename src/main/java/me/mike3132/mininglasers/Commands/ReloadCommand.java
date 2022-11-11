package me.mike3132.mininglasers.Commands;

import me.mike3132.mininglasers.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("Lasers").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("Lasers")) {
            if (sender.hasPermission("Laser.Reload")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("Reload")) {
                        this.plugin.reloadConfig();
                        sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&2Config reloaded in " + String.valueOf(System.currentTimeMillis() - 1) + "ms"));
                    } else {
                        if (sender.hasPermission("Laser.Version")) {
                            if (args[0].equalsIgnoreCase("Version")) {
                                sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&d&lBy &5&lMike3132"));
                                sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&6&lVersion &e&l2.0"));
                                sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&2&lConfig Version &a&l2.0"));
                                sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lNeed help? &c&lhttps://discord.gg/ePQGvQuzG3"));
                            } else {
                                sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cPlease use /lasers reload or /lasers version"));
                            }
                        }
                    }
                } else {
                    sender.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cPlease use /lasers reload or /lasers version"));
                }
            }
        }
        return false;
    }
}
