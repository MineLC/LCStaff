package lc.mine.staff.messages;

import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

import lc.mine.staff.LCStaffPlugin;

import java.util.HashMap;
import java.util.List;

public class StartMessages {

    public void load(LCStaffPlugin plugin) {
        final FileConfiguration config = plugin.loadConfig("messages");

        final Set<String> messages = config.getKeys(false);
        final Map<String, String> parsedMessages = new HashMap<>(messages.size());

        for (final String key : messages) {
            final Object object = config.get(key);
            if (object instanceof String) {
                parsedMessages.put(key, Messages.color(object.toString()));
                continue;
            }
            if (!(object instanceof List<?>)) {
                parsedMessages.put(key, object.toString());
                continue;
            }
            final StringBuilder builder = new StringBuilder();
            int index = 0;
            final List<?> list = (List<?>)object;
            for (final Object objectList : list) {
                builder.append(Messages.color(objectList.toString()));
                if (++index != list.size()) {
                    builder.append('\n');
                }
            }
            parsedMessages.put(key, builder.toString());
        }
        Messages.update(new Messages(parsedMessages));
    }
}