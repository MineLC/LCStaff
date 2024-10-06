package lc.mine.staff.commands.subcommands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lc.mine.staff.commands.SubCommand;
import lc.mine.staff.messages.Messages;
import lc.mine.staff.storage.StaffData;

public final class TpHereCommand implements SubCommand {

    @Override
    public void handle(StaffData data, Player player, String[] args) {
        if (args.length != 2) {
            Messages.send(player, "tp-format");
            return;
        }
        final Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            player.sendMessage(Messages.get("target-no-exist").replace("%player%", args[1]));
            return;
        }
        target.teleport(player);
    }

    @Override
    public List<String> tab(CommandSender sender, String[] args) {
        return (args.length == 0) ? playersNames() : List.of();
    }
}
