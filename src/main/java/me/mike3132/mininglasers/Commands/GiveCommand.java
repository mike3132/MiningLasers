package me.mike3132.mininglasers.Commands;

import me.mike3132.mininglasers.ItemManager.DiamondFortune;
import me.mike3132.mininglasers.ItemManager.DiamondSilk;
import me.mike3132.mininglasers.ItemManager.NetheriteFortune;
import me.mike3132.mininglasers.ItemManager.NetheriteSilk;
import me.mike3132.mininglasers.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class GiveCommand implements CommandExecutor {


    private final Main plugin;

    public GiveCommand(Main plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("Laser").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&4&lERROR: &c&lOnly players can use lasers"));
            return false;
        }
        Player player = (Player) sender;

        //Initial Command also checks to make sure player has the correct permission node
        if (cmd.getName().equalsIgnoreCase("Laser")) {
            if (player.hasPermission("Laser.Give")) {
                if (args.length == 0) {
                    player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &3Please use /laser give"));
                }
                //Checking to make sure second argument is give.
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("Give")) {
                        player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cPlease select a player name"));
                    }
                } else {
                    //Checking third argument to make sure it's a valid online player name.
                    if (args.length == 2) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&2" + player.getName() + " &2found &f> &aPlease choose your material &b&lDiamond &aor &8&lNetherite"));
                        } else {
                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&cPlayer Not found"));
                        }
                    }
                    //First switch statement that checks fourth argument to make sure it's either Diamond or Netherite.
                    if (args.length == 3) {
                        switch (args[2].toUpperCase()) {
                            case "DIAMOND":
                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&6Material &3[&b&lDIAMOND&3] &6Chosen &f> &eNow you can choose the enchantment &5*Silk* &eor &d*Fortune*"));
                                break;
                            case "NETHERITE":
                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&6Material &7[&8&lNETHERITE&7] &6Chosen &f> &eNow you can choose the enchantment &5*Silk* &eor &d*Fortune*"));
                                break;
                            default:
                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &c&l&ka&c" + player.getName() + "&c&l&ka&c That is not a valid material, Please chose &3[&b&lDIAMOND&3] &c or &7[&8&lNETHERITE&7]"));
                                break;

                        }
                    } else {
                        //Second switch statement that checks fifth argument to make sure it's either Silk or Fortune.
                        //This is also where I am checking to see if the player has at least 1 open slot.
                        //item is given to the player.
                        if (args.length == 4) {
                            switch (args[3].toUpperCase()) {
                                case "SILK":
                                    if (args[2].equalsIgnoreCase("Diamond")) {
                                        player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&9Enchantment &5Silk Touch &9Found. &6&lCreating your item now"));
                                        DiamondSilk diamondSilk = new DiamondSilk(this.plugin);
                                        ItemStack dsLaser = diamondSilk.getLaser();
                                        if (player.getInventory().firstEmpty() != -1) {
                                            player.getInventory().addItem(dsLaser);
                                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "You have been given a &3[&b&lDIAMOND&3] &5&l*Silk Touch* &4&lMining &c&lLaser"));
                                        } else {
                                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cYour inventory is full, Your laser has been dropped on the ground"));
                                            player.getLocation().getWorld().dropItem(player.getLocation().add(0, 1, 0), dsLaser);
                                        }
                                    } else {
                                        if (args[2].equalsIgnoreCase("Netherite")) {
                                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&9Enchantment &5Silk Touch &9Found. &6&lCreating your item now"));
                                            NetheriteSilk netheriteSilk = new NetheriteSilk(this.plugin);
                                            ItemStack nsLaser = netheriteSilk.getLaser();
                                            if (player.getInventory().firstEmpty() != -1) {
                                                player.getInventory().addItem(nsLaser);
                                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "You have been given a &7[&8&lNETHERITE&7] &5&l*Silk Touch* &4&lMining &c&lLaser"));
                                            } else {
                                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cYour inventory is full, Your laser has been dropped on the ground"));
                                                player.getLocation().getWorld().dropItem(player.getLocation().add(0, 1, 0), nsLaser);
                                            }
                                        }
                                    }
                                    break;
                                case "FORTUNE":
                                    if (args[2].equalsIgnoreCase("Diamond")) {
                                        player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&9Enchantment &dFortune &9Found. &6&lCreating your item now"));
                                        DiamondFortune diamondFortune = new DiamondFortune(this.plugin);
                                        ItemStack dfLaser = diamondFortune.getLaser();
                                        if (player.getInventory().firstEmpty() != -1) {
                                            player.getInventory().addItem(dfLaser);
                                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "You have been given a &3[&b&lDIAMOND&3] &d&l*Fortune* &4&lMining &c&lLaser"));
                                        } else {
                                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cYour inventory is full, Your laser has been dropped on the ground"));
                                            player.getLocation().getWorld().dropItem(player.getLocation().add(0, 1, 0), dfLaser);
                                        }
                                    } else {
                                        if (args[2].equalsIgnoreCase("Netherite")) {
                                            player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&9Enchantment &dFortune &9Found. &6&lCreating your item now"));
                                            NetheriteFortune netheriteFortune = new NetheriteFortune(this.plugin);
                                            ItemStack nfLaser = netheriteFortune.getLaser();
                                            if (player.getInventory().firstEmpty() != -1) {
                                                player.getInventory().addItem(nfLaser);
                                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "You have been given a &7[&8&lNETHERITE&7] &d&l*Fortune* &4&lMining &c&lLaser"));
                                            } else {
                                                player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &cYour inventory is full, Your laser has been dropped on the ground"));
                                                player.getLocation().getWorld().dropItem(player.getLocation().add(0, 1, 0), nfLaser);
                                            }
                                        }
                                    }
                                    break;
                                default:
                                    player.sendMessage(Main.chatColor("" + this.plugin.getConfig().getString("Prefix") + "&4&lError &c&l&ka&c" + player.getName() + "&c&l&ka&c That is not a valid enchantment, Please chose &5*Silk* &eor &d*Fortune*"));
                                    break;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }
}
