package lc.mine.staff.commands;

import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lc.lcspigot.commands.Command;
import lc.mine.staff.commands.subcommands.FreezeCommand;
import lc.mine.staff.commands.subcommands.InvseeCommand;
import lc.mine.staff.commands.subcommands.TpCommand;
import lc.mine.staff.commands.subcommands.TpHereCommand;
import lc.mine.staff.commands.subcommands.VanishCommand;
import lc.mine.staff.messages.Messages;
import lc.mine.staff.storage.StaffData;

public final class StaffCommand implements Command {

    private final Map<Player, StaffData> staffs;
    private final VanishCommand vanish;
    private final FreezeCommand freeze = new FreezeCommand();
    private final TpCommand tp = new TpCommand();
    private final TpHereCommand tphere = new TpHereCommand();
    private final InvseeCommand invsee = new InvseeCommand();

    public StaffCommand(Map<Player, StaffData> staffs) {
        this.staffs = staffs;
        vanish = new VanishCommand(staffs);
    }

    @Override
    public void handle(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            send(sender, "You need be a player to execute this command");
            return;
        }
        final StaffData data = staffs.get(player);
        if (data == null) {
            Messages.send(sender, "no-permission");
            return;
        }
        if (args.length < 1) {
            Messages.send(sender, "no-arguments-message");
            return;
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
    }

    @Override
    public String[] tab(CommandSender sender, String[] args) {
        if (args.length == 0) {
            return list("freeze", "vanish", "v", "tp", "tphere", "invsee");
        }
        switch (args[0]) {
            case "freeze": return freeze.tab(sender, args);
            case "tp": return tp.tab(sender, args);
            case "tphere": return tphere.tab(sender, args);
            case "invsee": return invsee.tab(sender, args);
            default: return none();
        }
    }
}