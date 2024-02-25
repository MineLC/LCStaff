package lc.mine.staff.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lc.mine.staff.commands.SubCommand;
import lc.mine.staff.messages.Messages;
import lc.mine.staff.storage.StaffData;

public final class TpCommand implements SubCommand {

    @Override
    public void handle(StaffData data, Player player, String[] args) {
        if (args.length != 1) {
            Messages.send(player, "tp-format");
            return;
        }
        final Player target = Bukkit.getPlayer(player.getName());
        if (target == null) {
            send(player, Messages.get("target-no-exist").replace("%player%", args[0]));
            return;
        }
        player.teleport(target);
    }

    @Override
    public String[] tab(CommandSender sender, String[] args) {
        return (args.length == 0) ? (String[])Bukkit.getOnlinePlayers().toArray() : none();
    }
}