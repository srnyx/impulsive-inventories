package xyz.srnyx.impulsiveinventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.utility.BukkitUtility;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class ImpulsiveCommand extends AnnoyingCommand {
    @NotNull private static final Random RANDOM = new Random();

    @NotNull private final ImpulsiveInventories plugin;

    public ImpulsiveCommand(@NotNull ImpulsiveInventories plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ImpulsiveInventories getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getName() {
        return "impulsive";
    }

    @Override @NotNull
    public String getPermission() {
        return "impulsive.command";
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        if (sender.args.length == 1) {
            // reload
            if (sender.argEquals(0, "reload")) {
                plugin.reloadPlugin();
                plugin.getMessages().get().command.reload.newMessage().send(sender);
                return;
            }

            if (!sender.checkPlayer()) return;
            final Player player = sender.getPlayer();

            // randomize
            if (sender.argEquals(0, "randomize")) {
                InventoryUtility.randomize(player.getInventory());
                plugin.getMessages().get().command.randomize.newMessage()
                        .replace("%player%", player.getName())
                        .send(sender);
                return;
            }

            // swap
            if (sender.argEquals(0, "swap")) {
                final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                players.remove(player);
                final Player randomPlayer = players.stream()
                        .skip(RANDOM.nextInt(players.size()))
                        .findFirst()
                        .orElse(null);
                if (randomPlayer == null) {
                    plugin.getMessages().get().command.online.newMessage().send(sender);
                    return;
                }

                InventoryUtility.swap(player.getInventory(), randomPlayer.getInventory());
                plugin.getMessages().get().command.swap.newMessage()
                        .replace("%player1%", player.getName())
                        .replace("%player2%", randomPlayer.getName())
                        .send(sender);
                return;
            }
        }

        if (sender.args.length == 2) {
            // randomize <player>
            if (sender.argEquals(0, "randomize")) {
                final List<Player> players = sender.getSelector(1, Player.class)
                        .orElseSingle(Bukkit::getPlayer);
                if (players != null) for (final Player player : players) {
                    InventoryUtility.randomize(player.getInventory());
                    plugin.getMessages().get().command.randomize.newMessage()
                            .replace("%player%", player.getName())
                            .send(sender);
                }
                return;
            }

            // swap <player>
            if (sender.argEquals(0, "swap")) {
                final Player target = sender.getArgument(1, Bukkit::getPlayer);
                if (target == null) return;

                if (!sender.checkPlayer()) return;
                final Player player = sender.getPlayer();
                InventoryUtility.swap(target.getInventory(), player.getInventory());
                plugin.getMessages().get().command.swap.newMessage()
                        .replace("%player1%", target.getName())
                        .replace("%player2%", player.getName())
                        .send(sender);
                return;
            }
        }

        // swap <player1> <player2>
        if (sender.args.length == 3 && sender.argEquals(0, "swap")) {
            final Player target1 = sender.getArgument(1, Bukkit::getPlayer);
            if (target1 == null) return;
            final Player target2 = sender.getArgument(2, Bukkit::getPlayer);
            if (target2 == null) return;

            InventoryUtility.swap(target1.getInventory(), target2.getInventory());
            plugin.getMessages().get().command.swap.newMessage()
                    .replace("%player1%", target1.getName())
                    .replace("%player2%", target2.getName())
                    .send(sender);
            return;
        }

        sender.invalidArguments();
    }

    @Override @Nullable
    public Collection<String> onTabComplete(@NotNull AnnoyingSender sender) {
        if (sender.args.length == 1) return Arrays.asList("randomize", "swap", "reload");
        if (sender.argEquals(0, "randomize")) return sender.withSelectorKeys(BukkitUtility.getOnlinePlayerNames(), Player.class);
        if (sender.argEquals(0, "swap")) return BukkitUtility.getOnlinePlayerNames();
        return null;
    }
}
