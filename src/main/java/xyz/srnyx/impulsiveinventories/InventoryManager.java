package xyz.srnyx.impulsiveinventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Class to manage {@link Inventory}s
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
    @Contract(pure = true)
    public InventoryManager(@NotNull Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Randomizes the {@link #inventory}
     */
    public void randomize() {
        final List<ItemStack> items = Arrays.asList(inventory.getContents());
        Collections.shuffle(items);
        inventory.setContents(items.toArray(new ItemStack[0]));
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
