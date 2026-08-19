package xyz.srnyx.impulsiveinventories.messages;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.message.AnnoyingMessages;
import xyz.srnyx.annoyingapi.message.MessagesProvider;
import xyz.srnyx.impulsiveinventories.ImpulsiveInventories;


public class IIMessagesProvider extends MessagesProvider {
    @NotNull private final ImpulsiveInventories plugin;
    private IIMessages messages;

    public IIMessagesProvider(@NotNull ImpulsiveInventories plugin) {
        this.plugin = plugin;

        builder(b -> b.config(new IIMessages(plugin)));
        defaults
                .prefix("&5&lIMPULSIVE &8&l| &d")
                .p("&d")
                .s("&5");
    }

    @Override @NotNull
    public ImpulsiveInventories getAnnoyingPlugin() {
        return plugin;
    }

    @Override
    public void accept(@NotNull AnnoyingMessages annoyingMessages) {
        messages = (IIMessages) annoyingMessages;
    }

    @Override @NotNull
    public IIMessages get() {
        return messages;
    }
}
