// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot;

import java.util.List;
import org.bukkit.configuration.InvalidConfigurationException;
import com.google.common.base.Throwables;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class ClubSpigotConfig
{
    private static final String HEADER = "This is the main configuration file for the Spigot.\nModify with extreme caution, and make sure you know what you are doing.\n";
    private File configFile;
    private YamlConfiguration config;
    private double knockbackHorizontal;
    private double knockbackVertical;
    private int defaultNoDamageTicks;
    private boolean hidePlayersFromTab;
    private boolean firePlayerMoveEvent;
    private boolean fireLeftClickAir;
    private boolean fireLeftClickBlock;
    private boolean entityActivation;
    private boolean invalidArmAnimationKick;
    private boolean mobAIEnabled;
    private boolean enabled1_8Stuff;
    private boolean doChunkUnload;
    
    public ClubSpigotConfig() {
        this.configFile = new File("settings.yml");
        this.config = new YamlConfiguration();
        try {
            this.config.load(this.configFile);
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        catch (final InvalidConfigurationException ex2) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not load settings.yml, please correct your syntax errors", (Throwable)ex2);
            throw Throwables.propagate((Throwable)ex2);
        }
        this.config.options().header("This is the main configuration file for the Spigot.\nModify with extreme caution, and make sure you know what you are doing.\n");
        this.config.options().copyDefaults(true);
        this.loadConfig();
    }
    
    private void loadConfig() {
        this.knockbackHorizontal = this.getDouble("knockback.horizontal", 1.0);
        this.knockbackVertical = this.getDouble("knockback.vertical", 1.0);
        this.defaultNoDamageTicks = this.getInt("default-no-damage-ticks", 20);
        this.hidePlayersFromTab = this.getBoolean("hide-players-from-tab", true);
        this.firePlayerMoveEvent = this.getBoolean("fire-player-move-event", false);
        this.fireLeftClickAir = this.getBoolean("fire-left-click-air", false);
        this.fireLeftClickBlock = this.getBoolean("fire-left-click-block", false);
        this.entityActivation = this.getBoolean("entity-activation", false);
        this.invalidArmAnimationKick = this.getBoolean("invalid-arm-animation-kick", false);
        this.mobAIEnabled = this.getBoolean("mob-ai", true);
        this.enabled1_8Stuff = this.getBoolean("1-8-enabled", false);
        this.doChunkUnload = this.getBoolean("do-chunk-unload", true);
        try {
            this.config.save(this.configFile);
        }
        catch (final IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save " + this.configFile, ex);
        }
    }
    
    public void set(final String path, final Object val) {
        this.config.set(path, val);
    }
    
    public void setKnockbackHorizontal(final double knockbackHorizontal) {
        this.knockbackHorizontal = knockbackHorizontal;
    }
    
    public void setKnockbackVertical(final double knockbackVertical) {
        this.knockbackVertical = knockbackVertical;
    }
    
    public boolean getBoolean(final String path, final boolean def) {
        this.config.addDefault(path, (Object)def);
        return this.config.getBoolean(path, this.config.getBoolean(path));
    }
    
    public double getDouble(final String path, final double def) {
        this.config.addDefault(path, (Object)def);
        return this.config.getDouble(path, this.config.getDouble(path));
    }
    
    public float getFloat(final String path, final float def) {
        return (float)this.getDouble(path, def);
    }
    
    public int getInt(final String path, final int def) {
        this.config.addDefault(path, (Object)def);
        return this.config.getInt(path, this.config.getInt(path));
    }
    
    public <T> List getList(final String path, final T def) {
        this.config.addDefault(path, (Object)def);
        return this.config.getList(path, this.config.getList(path));
    }
    
    public String getString(final String path, final String def) {
        this.config.addDefault(path, (Object)def);
        return this.config.getString(path, this.config.getString(path));
    }
    
    public double getKnockbackHorizontal() {
        return this.knockbackHorizontal;
    }
    
    public int getDefaultNoDamageTicks() {
        return this.defaultNoDamageTicks;
    }
    
    public double getKnockbackVertical() {
        return this.knockbackVertical;
    }
    
    public boolean isHidePlayersFromTab() {
        return this.hidePlayersFromTab;
    }
    
    public boolean isFirePlayerMoveEvent() {
        return this.firePlayerMoveEvent;
    }
    
    public boolean isFireLeftClickAir() {
        return this.fireLeftClickAir;
    }
    
    public boolean isFireLeftClickBlock() {
        return this.fireLeftClickBlock;
    }
    
    public boolean isEntityActivation() {
        return this.entityActivation;
    }
    
    public boolean isInvalidArmAnimationKick() {
        return this.invalidArmAnimationKick;
    }
    
    public boolean isMobAIEnabled() {
        return this.mobAIEnabled;
    }
    
    public boolean is18Enabled() {
        return this.enabled1_8Stuff;
    }
    
    public boolean isDoChunkUnload() {
        return this.doChunkUnload;
    }
}
