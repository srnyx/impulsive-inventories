package xyz.srnyx.impulsiveinventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class InventoryUtility {
    public static void randomize(@NotNull Inventory inventory) {
        final ItemStack[] contents = inventory.getContents();
        final List<ItemStack> items = Arrays.asList(contents);
        Collections.shuffle(items);
        inventory.setContents(items.toArray(contents));
    }

    public static void swap(@NotNull Inventory first, @NotNull Inventory second) {
        final ItemStack[] contents = first.getContents();
        first.setContents(second.getContents());
        second.setContents(contents);
    }
}
