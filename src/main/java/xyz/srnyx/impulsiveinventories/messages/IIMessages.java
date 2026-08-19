package xyz.srnyx.impulsiveinventories.messages;

import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Include;
import eu.okaeri.configs.annotation.IncludePosition;
import eu.okaeri.validator.annotation.NotNull;
import xyz.srnyx.annoyingapi.file.okaeri.SubConfig;
import xyz.srnyx.annoyingapi.message.AnnoyingMessages;
import xyz.srnyx.annoyingapi.message.json.message.JsonChatMessage;
import xyz.srnyx.impulsiveinventories.ImpulsiveInventories;


@Include(value = AnnoyingMessages.class, position = IncludePosition.BEFORE)
public class IIMessages extends AnnoyingMessages {
    public IIMessages(@org.jetbrains.annotations.NotNull ImpulsiveInventories plugin) {
        super(plugin);
    }

    @Comment
    @NotNull public Command command = new Command(this);

    public static class Command extends SubConfig<IIMessages, IIMessages> {
        public Command(@org.jetbrains.annotations.NotNull IIMessages defaultsParent) {
            super(defaultsParent);
        }

        @NotNull public JsonChatMessage online = getRoot().defaultMessage("%prefix%%pe%There aren't any players online!@@%pe%%command%@@%command%");

        @Comment
        @Comment("Placeholders: %player%")
        @NotNull public JsonChatMessage randomize = getRoot().defaultMessage("%prefix%%s%%player%%p%'s inventory has been randomized@@%p%%command%@@%command%");

        @Comment
        @Comment("Placeholders: %player1%, %player2%")
        @NotNull public JsonChatMessage swap = getRoot().defaultMessage("%prefix%%s%%player1%%p% and %s%%player2%%p%'s inventories have been swapped@@%p%%command%@@%command%");

        @Comment
        @NotNull public JsonChatMessage reload = getRoot().defaultMessage("%prefix%%p%Messages have been reloaded@@%p%%command%@@%command%");
    }
}
