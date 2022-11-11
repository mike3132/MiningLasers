package me.mike3132.mininglasers.ItemManager;

import me.mike3132.mininglasers.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class NetheriteFortune {

    private final Main plugin;
    private ItemStack nfLaser;

    public NetheriteFortune(Main plugin) {
        this.plugin = plugin;
        this.createLaser();
    }

    public ItemStack getLaser() {
        return this.nfLaser;
    }

    //This is where the item is created and where I set the name, lore, and enchantments
    private void createLaser() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        for (String realLore : this.plugin.getConfig().getStringList("Netherite_Fortune_Lore")) {
            lore.add(Main.chatColor("" + realLore));
        }
        for (String enchants : this.plugin.getConfig().getStringList("Netherite_Fortune_Enchants")) {
            String enchantName = enchants.split(":")[0];
            Integer enchantLvl = Integer.valueOf(enchants.split(":")[1]);
            NamespacedKey key = NamespacedKey.minecraft(enchantName.toLowerCase());
            Enchantment enchantment = Enchantment.getByKey(key);
            if (enchantment == null) {
                Bukkit.getLogger().log(Level.WARNING, "There was an error parsing the enchantment named " + enchantName + ".");
                continue;
            }
            meta.addEnchant(enchantment, enchantLvl, true);
        }

        meta.setDisplayName(Main.chatColor("" + this.plugin.getConfig().getString("Netherite_Fortune_Name")));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setCustomModelData(1001);
        item.setItemMeta(meta);
        nfLaser = item;
    }
}
