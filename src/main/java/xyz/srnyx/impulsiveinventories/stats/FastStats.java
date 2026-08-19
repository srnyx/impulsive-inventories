package xyz.srnyx.impulsiveinventories.stats;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.stats.loader.FastStatsLoader;
import xyz.srnyx.impulsiveinventories.ImpulsiveInventories;


public class FastStats extends FastStatsLoader {
    @NotNull private final ImpulsiveInventories plugin;

    public FastStats(@NotNull ImpulsiveInventories plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ImpulsiveInventories getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getId() {
        return "d20286500f8638d9d2f9ba379139eafe";
    }
}
