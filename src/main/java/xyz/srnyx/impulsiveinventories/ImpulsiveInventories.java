package xyz.srnyx.impulsiveinventories;

import org.bukkit.ChatColor;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;


public class ImpulsiveInventories extends AnnoyingPlugin {
    public ImpulsiveInventories() {
        super();

        // Options
        options.colorLight = ChatColor.LIGHT_PURPLE;
        options.colorDark = ChatColor.DARK_PURPLE;
        options.commands.add(new ImpulsiveCommand(this));
    }
}
