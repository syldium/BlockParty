package de.leonkoth.blockparty.util;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.version.VersionedMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

import static de.leonkoth.blockparty.locale.BlockPartyLocale.*;

/**
 * Created by Leon on 18.09.2018.
 * Project BlockParty-2.0
 * © 2016 - Leon Koth
 */
public enum ItemType {

    LEAVEARENA(ITEM_LEAVE_ARENA.toString(), VersionedMaterial.DOOR.get(0), null), //TODO: add to config (slot & item)
    VOTEFORASONG(ITEM_VOTE_FOR_A_SONG.toString(), VersionedMaterial.FIRE_CHARGE.get(0), null),
    SELECTITEM(ITEM_SELECT.toString(), Material.STICK, null),
    SONG("", null, ITEM_SONG_LORE.toString());

    private String name;
    private Material type;
    private String lore;

    /**
     * Default navigation items
     *
     * @param name of the item
     * @param type of the item
     * @param lore of the item
     */
    private ItemType(String name, Material type, String lore) {
        this.name = name;
        this.type = type;
        this.lore = lore;
    }

    /**
     * To get the item
     *
     * @return the item initialized
     */
    public ItemStack getItem() {
        ItemStack i;
        i = new ItemStack(this.type);
        ItemMeta m = i.getItemMeta();
        if (this.lore != null) {
            ArrayList l = new ArrayList();
            l.add(this.lore);
            m.setLore(l);
        }
        m.setDisplayName(this.name);
        i.setItemMeta(m);
        return i;
    }

    public ItemStack getSongItem(String name) {
        this.name = name;

        Random r = new Random();
        int t = r.nextInt(10) + 1;
        this.type = VersionedMaterial.MUSIC_DISC.get(t);
        return this.getItem();
    }

    /**
     * To get the Material
     *
     * @return the item Material
     */
    public Material getType() {
        return this.type;
    }

    /**
     * To get the item name.
     * Important for identification which shop navigation item was clicked
     *
     * @return the item name
     */
    public String getName() {
        return this.name;
    }
}

