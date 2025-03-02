// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.event.potion;

import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;

public class PotionEffectRemoveEvent extends PotionEffectEvent implements Cancellable
{
    private static final HandlerList HANDLER_LIST;
    private boolean cancelled;
    
    public PotionEffectRemoveEvent(final LivingEntity entity, final PotionEffect effect) {
        super(entity, effect);
        this.cancelled = false;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
    
    public HandlerList getHandlers() {
        return PotionEffectRemoveEvent.HANDLER_LIST;
    }
    
    public static HandlerList getHandlerList() {
        return PotionEffectRemoveEvent.HANDLER_LIST;
    }
    
    static {
        HANDLER_LIST = new HandlerList();
    }
}
