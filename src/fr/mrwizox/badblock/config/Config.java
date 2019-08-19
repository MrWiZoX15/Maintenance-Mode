package fr.mrwizox.badblock.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.mrwizox.badblock.Main;

public enum Config {

        MAINTENANCE("Enabled", false),
        KICKMESSAGE("KickMessage", "&c&n[BadMaintenance] Le server est actuellement en maintenance"),
        VERSION("Version", "Maintenance"),
        KICK("KickMaintenance", true);

        private final Object value;
        private final String path;
        private static YamlConfiguration cfg;
        private static final File f = new File(Main.INSTANCE.getDataFolder(), "config.yml");

        private Config(String path, Object val) {
                this.path = path;
                this.value = val;
        }

        public String getPath() {
                return path;
        }

        public Object getDefaultValue() {
                return value;
        }

        public boolean getBoolean() {
                return cfg.getBoolean(path);
        }

        public String getString() {
                String s = cfg.getString(path);
                return s.replaceAll("&", "§");
        }

        public static void load() {
                Main.INSTANCE.getDataFolder().mkdirs();
                reload(false);
                for (Config c : values()) {
                        if (!cfg.contains(c.getPath())) {
                                c.set(c.getDefaultValue());
                        }
                }
                try {
                        cfg.save(f);
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }

        public void set(Object value) {
                cfg.set(path, value);
        }

        public static void save() {
                try {
                        cfg.save(f);
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
                reload(false);
        }

        public static void reload(boolean complete) {
                if (!complete) {
                        cfg = YamlConfiguration.loadConfiguration(f);
                        return;
                }
                load();
        }
}
