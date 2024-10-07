package lc.mine.staff.config.messages;

import java.util.Map;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public final class Messages {
    private static Messages instance;
    private final Map<String, String> parsedMessages;

    Messages(Map<String, String> parsedMessages) {
        this.parsedMessages = parsedMessages;
    }

    public static final void send(final CommandSender sender, final String key) {
        final String message = instance.parsedMessages.get(key);
        if (message != null) {
            sender.sendMessage(TextComponent.fromLegacyText(message));
        }
    }

    public static final String get(final String key) {
        final String message = instance.parsedMessages.get(key);
        return (message == null) ? "Can't found the message " + key : message;
    }

    public static final String color(final String message) {
        return (message == null) ? "" : message.replace('&', ChatColor.COLOR_CHAR);
    }

    static final void update(Messages messages) {
        instance = messages;
    }
}