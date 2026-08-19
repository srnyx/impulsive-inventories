package xyz.srnyx.impulsiveinventories;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.impulsiveinventories.messages.IIMessagesProvider;
import xyz.srnyx.impulsiveinventories.stats.FastStats;


public class ImpulsiveInventories extends AnnoyingPlugin {
    public ImpulsiveInventories() {
        options.statsOptions(statsOptions -> statsOptions
                .bStats(bStatsOptions -> bStatsOptions.id(18322))
                .fastStats(fastStatsOptions -> fastStatsOptions.loader(FastStats.class)));
    }

    @Override @NotNull
    public IIMessagesProvider getMessages() {
        return (IIMessagesProvider) super.getMessages();
    }
}
