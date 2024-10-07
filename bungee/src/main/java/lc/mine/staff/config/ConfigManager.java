package lc.mine.staff.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import lc.mine.staff.config.messages.MessageConfig;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {

    private final Logger logger;

    public ConfigManager(Logger logger) {
        this.logger = logger;
    }
    
    public void load(final File datafolder) {
        final ConfigurationProvider configProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);       
        final File messagesFile = new File(datafolder, "messages.yml");
        if (!datafolder.exists()) {
            datafolder.mkdirs();
        }
        if (!messagesFile.exists()) {
            write("messages.yml", messagesFile);
        }

        try {
            new MessageConfig().load(configProvider.load(messagesFile));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading configs", e);
        }
    }

    private void write(final String resourcePath, final File outDestination) {
        final InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) {
            logger.warning("The file " + resourcePath + " don't exist in resources folder");
            return;
        }
        try {
            IOUtils.copy(stream, new FileOutputStream(outDestination));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error copying the file " + resourcePath + " into " + outDestination + ". Error -> ", e);
        }
    }
}