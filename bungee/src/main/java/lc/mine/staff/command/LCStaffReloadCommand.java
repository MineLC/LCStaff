package lc.mine.staff.command;

import lc.mine.staff.LCStaffBungee;
import lc.mine.staff.config.messages.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class LCStaffReloadCommand extends Command {

    private final LCStaffBungee plugin;

    public LCStaffReloadCommand(LCStaffBungee plugin) {
        super("lcstaffreload");
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("lcstaffreload")) {
            plugin.loadConfig();
            sender.sendMessage("Plugin reloaded!");
            return;
        }
        Messages.send(sender, "no-permission");
    }
}
