package me.mike3132.mininglasers;

import me.mike3132.mininglasers.Commands.GiveCommand;
import me.mike3132.mininglasers.Commands.ReloadCommand;
import me.mike3132.mininglasers.EventHandler.DiamondFortuneEvent;
import me.mike3132.mininglasers.EventHandler.DiamondSilkEvent;
import me.mike3132.mininglasers.EventHandler.NetheriteFortuneEvent;
import me.mike3132.mininglasers.EventHandler.NetheriteSilkEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }

    @Override
    public void onEnable() {
        //Plugin startup logic
        getServer().getConsoleSender().sendMessage(chatColor("" + this.getConfig().getString("Prefix") + "&2&lENABLED"));

        //Command Initializers
        registerGive();
        registerReload();

        //Event Listener
        Bukkit.getPluginManager().registerEvents(new DiamondFortuneEvent(this),this);
        Bukkit.getPluginManager().registerEvents(new DiamondSilkEvent(this),this);
        Bukkit.getPluginManager().registerEvents(new NetheriteFortuneEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new NetheriteSilkEvent(this), this);

        //Config Loaders
        saveDefaultConfig();
        getConfig();
    }

    //Command Registers
    public void registerGive() {
        new GiveCommand(this);
    }
    public void registerReload() {
        new ReloadCommand(this);
    }

    @Override
    public void onDisable() {
        //Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(chatColor("" + this.getConfig().getString("Prefix") + "4&lDISABLED"));
    }
}
