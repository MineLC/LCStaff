package lc.mine.staff.config.messages;

import java.util.Map;
import net.md_5.bungee.config.Configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MessageConfig {

    public void load(final Configuration config) {

        final Collection<String> messages = config.getKeys();
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