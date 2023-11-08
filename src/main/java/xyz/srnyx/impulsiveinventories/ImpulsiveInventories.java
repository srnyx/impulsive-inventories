package xyz.srnyx.impulsiveinventories;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;


public class ImpulsiveInventories extends AnnoyingPlugin {
    public ImpulsiveInventories() {
        options
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18322))
                .registrationOptions.toRegister(this, ImpulsiveCommand.class);
    }
}
