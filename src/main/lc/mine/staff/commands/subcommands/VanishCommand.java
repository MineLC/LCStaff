package lc.mine.staff.commands.subcommands;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import lc.mine.staff.commands.SubCommand;
import lc.mine.staff.messages.Messages;
import lc.mine.staff.storage.StaffData;

public final class VanishCommand implements SubCommand {

    @Override
    public void handle(StaffData data, Player player, String[] args) {
        final Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        if (data.getEnableVanish()) {
            data.setVanish(false);

            for (final Player otherPlayer : players) {
                otherPlayer.showPlayer(player);
            }
            Messages.send(player, "vanish-off");
            return;
        }

        data.setVanish(true);

        for (final Player otherPlayer : players) {
            if (otherPlayer.hasPermission("lcstaff")) {
                continue;
            }
            otherPlayer.hidePlayer(player);
        }
        Messages.send(player, "vanish-on");
    }
}