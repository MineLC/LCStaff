package lc.mine.staff.listener;

import java.util.Map;
import java.util.UUID;

import lc.mine.staff.config.messages.Messages;
import lc.mine.staff.data.Staff;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public final class StaffChatListener implements Listener {
    
    private final Map<UUID, Staff> onlineStaffs;

    public StaffChatListener(Map<UUID, Staff> onlineStaffs) {
        this.onlineStaffs = onlineStaffs;
    }

    @EventHandler
    public void onChat(final ChatEvent event) {
        if (event.getMessage().charAt(0) == '/' || !(event.getSender() instanceof ProxiedPlayer)) {
            return;
        }
        final ProxiedPlayer sender = (ProxiedPlayer)event.getSender();
        final Staff senderStaff = onlineStaffs.get(sender.getUniqueId());
        if (senderStaff == null || !senderStaff.isStaffChat()) {
            return;
        }
        event.setCancelled(true);

        final BaseComponent[] staffChatMessage = TextComponent.fromLegacyText(Messages.get("staff-chat")
            .replace("%server%", sender.getServer().getInfo().getName())
            .replace("%player%", sender.getName())
            .replace("%message%", event.getMessage()));
    
        for (final Staff staff : onlineStaffs.values()) {
            staff.getStaff().sendMessage(staffChatMessage);
        }
    }
}
