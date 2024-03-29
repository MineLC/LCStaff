package lc.mine.staff.commands.subcommands;

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
            send(player, Messages.get("target-no-exist").replace("%player%", args[1]));
            return;
        }
        target.teleport(player);
    }

    @Override
    public String[] tab(CommandSender sender, String[] args) {
        return (args.length == 0) ? (String[])Bukkit.getOnlinePlayers().toArray() : none();
    }
}
