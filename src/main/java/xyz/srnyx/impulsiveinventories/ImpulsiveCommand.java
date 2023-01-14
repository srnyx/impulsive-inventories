package xyz.srnyx.impulsiveinventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import xyz.srnyx.annoyingapi.AnnoyingMessage;
import xyz.srnyx.annoyingapi.AnnoyingUtility;
import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;


public class ImpulsiveCommand implements AnnoyingCommand {
    @NotNull private final ImpulsiveInventories plugin;
    /**
     * {@link Random} to generate random numbers
     */
    @NotNull private final Random random = new Random();

    @Contract(pure = true)
    public ImpulsiveCommand(@NotNull ImpulsiveInventories plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ImpulsiveInventories getPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getName() {
        return "impulsiveinventories";
    }

    @Override @NotNull
    public String getPermission() {
        return "impulsiveinventories.command";
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        final String[] args = sender.getArgs();
        final boolean isPlayer = sender.getCmdSender() instanceof Player;

        if (args.length == 1) {
            if (sender.argEquals(0, "reload")) {
                plugin.reloadPlugin();
                new AnnoyingMessage(plugin, "command.reload").send(sender);
                return;
            }

            if (isPlayer) {
                final Player player = sender.getPlayer();
                final InventoryManager manager = new InventoryManager(player.getInventory());

                if (sender.argEquals(0, "randomize")) {
                    manager.randomize();
                    new AnnoyingMessage(plugin, "command.randomize")
                            .replace("%player%", player.getName())
                            .send(player);
                    return;
                }

                if (sender.argEquals(0, "swap")) {
                    final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                    final int length = players.size();
                    if (length == 0) {
                        new AnnoyingMessage(plugin, "command.online").send(sender);
                        return;
                    }

                    final Player randomPlayer = (Player) players.toArray()[random.nextInt(length)];
                    manager.swap(randomPlayer.getInventory());
                    new AnnoyingMessage(plugin, "command.swap")
                            .replace("%player1%", player.getName())
                            .replace("%player2%", randomPlayer.getName())
                            .send(sender);
                    return;
                }
            }
        }

        if (args.length == 2) {
            final Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                new AnnoyingMessage(plugin, "error.invalid-argument")
                        .replace("%argument%", args[1])
                        .send(sender);
                return;
            }
            final InventoryManager manager = new InventoryManager(target.getInventory());

            if (sender.argEquals(0, "randomize")) {
                manager.randomize();
                new AnnoyingMessage(plugin, "command.randomize")
                        .replace("%player%", target.getName())
                        .send(sender);
                return;
            }

            if (sender.argEquals(0, "swap") && isPlayer) {
                final Player player = sender.getPlayer();
                manager.swap(player.getInventory());
                new AnnoyingMessage(plugin, "command.swap")
                        .replace("%player1%", target.getName())
                        .replace("%player2%", player.getName())
                        .send(sender);
                return;
            }
        }

        if (args.length == 3 && sender.argEquals(0, "swap")) {
            final Player target1 = Bukkit.getPlayer(args[1]);
            final Player target2 = Bukkit.getPlayer(args[2]);
            if (target1 == null || target2 == null) {
                new AnnoyingMessage(plugin, "error.invalid-arguments").send(sender);
                return;
            }

            new InventoryManager(target1.getInventory()).swap(target2.getInventory());
            new AnnoyingMessage(plugin, "command.swap")
                    .replace("%player1%", target1.getName())
                    .replace("%player2%", target2.getName())
                    .send(sender);
            return;
        }

        new AnnoyingMessage(plugin, "error.invalid-arguments").send(sender);
    }

    @Override @Nullable
    public Collection<String> onTabComplete(@NotNull AnnoyingSender sender) {
        if (sender.getArgs().length == 1) return Arrays.asList("randomize", "swap", "reload");
        if (sender.argEquals(0, "randomize", "swap")) return AnnoyingUtility.getOnlinePlayerNames();
        return null;
    }
}
