package lc.mine.staff.commands;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import lc.mine.staff.commands.subcommands.FreezeCommand;
import lc.mine.staff.commands.subcommands.InvseeCommand;
import lc.mine.staff.commands.subcommands.TpCommand;
import lc.mine.staff.commands.subcommands.TpHereCommand;
import lc.mine.staff.commands.subcommands.VanishCommand;
import lc.mine.staff.messages.Messages;
import lc.mine.staff.storage.StaffData;

public final class StaffCommand implements TabExecutor {

    private final Map<UUID, StaffData> staffs;
    private final VanishCommand vanish = new VanishCommand();
    private final FreezeCommand freeze = new FreezeCommand();
    private final TpCommand tp = new TpCommand();
    private final TpHereCommand tphere = new TpHereCommand();
    private final InvseeCommand invsee = new InvseeCommand();

    public StaffCommand(Map<UUID, StaffData> staffs) {
        this.staffs = staffs;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You need be a player to execute this command");
            return true;
        }
        if (!player.hasPermission("lcstaff")) {
            Messages.send(sender, "no-permission");
            return true;
        }
        final StaffData data = staffs.get(player.getUniqueId());
        if (data == null) {
            staffs.put(player.getUniqueId(), new StaffData());
        }
        if (args.length < 1) {
            Messages.send(sender, "no-arguments-message");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "freeze":
                freeze.handle(data, player, args);
                break;
            case "vanish", "v":
                vanish.handle(data, player, args);
                break;
            case "tp":
                tp.handle(data, player, args);
                break;
            case "tphere":
                tphere.handle(data, player, args);
                break;
            case "invsee":
                invsee.handle(data, player, args);
                break;
            default:
                Messages.send(sender, "no-arguments-message");
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return List.of("freeze", "vanish", "v", "tp", "tphere", "invsee");
        }
        switch (args[0]) {
            case "freeze": return freeze.tab(sender, args);
            case "tp": return tp.tab(sender, args);
            case "tphere": return tphere.tab(sender, args);
            case "invsee": return invsee.tab(sender, args);
            default: return List.of();
        }
    }
}