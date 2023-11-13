package xyz.srnyx.impulsiveinventories;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;


public class ImpulsiveInventories extends AnnoyingPlugin {
    public ImpulsiveInventories() {
        options
                .pluginOptions(pluginOptions -> pluginOptions.updatePlatforms(
                        PluginPlatform.modrinth("impulsive-inventories"),
                        PluginPlatform.hangar(this, "srnyx"),
                        PluginPlatform.spigot("113437")))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18322))
                .registrationOptions.toRegister(this, ImpulsiveCommand.class);
    }
}
