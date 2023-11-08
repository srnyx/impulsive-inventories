package xyz.srnyx.impulsiveinventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;
import xyz.srnyx.annoyingapi.utility.BukkitUtility;

import java.util.Arrays;
import java.util.Collection;
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
        final String[] args = sender.args;

        if (args.length == 1) {
            // reload
            if (sender.argEquals(0, "reload")) {
                plugin.reloadPlugin();
                new AnnoyingMessage(plugin, "command.reload").send(sender);
                return;
            }

            if (!sender.checkPlayer()) return;
            final Player player = sender.getPlayer();
            final InventoryManager manager = new InventoryManager(player.getInventory());

            // randomize
            if (sender.argEquals(0, "randomize")) {
                manager.randomize();
                new AnnoyingMessage(plugin, "command.randomize")
                        .replace("%player%", player.getName())
                        .send(player);
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
                    new AnnoyingMessage(plugin, "command.online").send(sender);
                    return;
                }

                manager.swap(randomPlayer.getInventory());
                new AnnoyingMessage(plugin, "command.swap")
                        .replace("%player1%", player.getName())
                        .replace("%player2%", randomPlayer.getName())
                        .send(sender);
                return;
            }
        }

        if (args.length == 2) {
            final Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.invalidArgument(args[1]);
                return;
            }
            final InventoryManager manager = new InventoryManager(target.getInventory());

            // randomize <player>
            if (sender.argEquals(0, "randomize")) {
                manager.randomize();
                new AnnoyingMessage(plugin, "command.randomize")
                        .replace("%player%", target.getName())
                        .send(sender);
                return;
            }

            // swap <player>
            if (sender.argEquals(0, "swap")) {
                if (!sender.checkPlayer()) return;
                final Player player = sender.getPlayer();
                manager.swap(player.getInventory());
                new AnnoyingMessage(plugin, "command.swap")
                        .replace("%player1%", target.getName())
                        .replace("%player2%", player.getName())
                        .send(sender);
                return;
            }
        }

        // swap <player1> <player2>
        if (args.length == 3 && sender.argEquals(0, "swap")) {
            final Player target1 = Bukkit.getPlayer(args[1]);
            final Player target2 = Bukkit.getPlayer(args[2]);
            if (target1 == null || target2 == null) {
                sender.invalidArguments();
                return;
            }

            new InventoryManager(target1.getInventory()).swap(target2.getInventory());
            new AnnoyingMessage(plugin, "command.swap")
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
        if (sender.argEquals(0, "randomize", "swap")) return BukkitUtility.getOnlinePlayerNames();
        return null;
    }
}
