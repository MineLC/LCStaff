package lc.mine.staff.command;

import java.util.Map;
import java.util.UUID;

import lc.mine.staff.config.messages.Messages;
import lc.mine.staff.data.Staff;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {

    private final Map<UUID, Staff> onlineStaffs;

    public ReportCommand(Map<UUID, Staff> onlineStaffs) {
        super("report");
        this.onlineStaffs = onlineStaffs;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("You need be a player");
            return;
        }

        if (args.length < 2) {
            Messages.send(sender, "report-format");
            return;
        }
        if (onlineStaffs.isEmpty()) {
            Messages.send(sender, "cant-found-staffs");
            return;
        }

        final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Messages.get("cant-found-player").replace("%player%", args[0]));
            return;
        }
        if (target.equals(sender)) {
            Messages.send(sender, "cant-report-yourself");
            return;
        }

        final StringBuilder reason = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            reason.append(args[i]);
        }

        final String reasonMessage = reason.toString();
        final TextComponent component = new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', Messages.get("report-recive")
            .replace("%accused%", args[0])
            .replace("%reason%", reasonMessage)
            .replace("%victim%", sender.getName())
            .replace("%server%", target.getServer().getInfo().getName())
        )));

        component.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/server " + ((ProxiedPlayer)sender).getServer().getInfo().getName()));
        for (final Staff staff : onlineStaffs.values()) {
            staff.getStaff().sendMessage(component);
        }

        sender.sendMessage(Messages.get("report-send")
            .replace("%accused%", args[0])
            .replace("%reason%", reasonMessage)
        );
    }
}
