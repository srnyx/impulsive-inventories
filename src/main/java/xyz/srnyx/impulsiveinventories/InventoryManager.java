package xyz.srnyx.impulsiveinventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Class to manage {@link Inventory inventories}
 */
public class InventoryManager {
    /**
     * The {@link Inventory} to manage
     */
    @NotNull private final Inventory inventory;

    /**
     * Class to manage {@link Inventory}s
     *
     * @param   inventory   the {@link Inventory} to manage
     */
    public InventoryManager(@NotNull Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Randomizes the {@link #inventory}
     */
    public void randomize() {
        final ItemStack[] contents = inventory.getContents();
        final List<ItemStack> items = Arrays.asList(contents);
        Collections.shuffle(items);
        inventory.setContents(items.toArray(contents));
    }

    /**
     * Swaps the {@link #inventory} with {@code other}
     *
     * @param   other   the other {@link Inventory}
     */
    public void swap(@NotNull Inventory other) {
        final ItemStack[] contents = inventory.getContents();
        inventory.setContents(other.getContents());
        other.setContents(contents);
    }
}
