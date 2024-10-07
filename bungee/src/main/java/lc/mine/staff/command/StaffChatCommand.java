package lc.mine.staff.command;

import java.util.Map;
import java.util.UUID;

import lc.mine.staff.config.messages.Messages;
import lc.mine.staff.data.Staff;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {

    private final Map<UUID, Staff> onlineStaffs;

    public StaffChatCommand(Map<UUID, Staff> onlineStaffs) {
        super("staffchat");
        this.onlineStaffs = onlineStaffs;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("You need be a player");
            return;
        }
        final ProxiedPlayer player = (ProxiedPlayer)sender;
        final Staff staff = onlineStaffs.get(player.getUniqueId());
        if (staff == null) {
            Messages.send(sender, "no-permission");
            return;
        }
        if (staff.isStaffChat()) {
            staff.setStaffChat(false);
            Messages.send(sender, "staff-chat-off");
            return;
        }
        staff.setStaffChat(true);
        Messages.send(sender, "staff-chat-on");
        return;
    }    
}
