// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.event.potion;

import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.LivingEntity;

public class PotionEffectExpireEvent extends PotionEffectRemoveEvent
{
    private int duration;
    
    public PotionEffectExpireEvent(final LivingEntity entity, final PotionEffect effect) {
        super(entity, effect);
        this.duration = 0;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(final int duration) {
        this.duration = Math.max(0, duration);
    }
    
    @Override
    public boolean isCancelled() {
        return this.duration > 0;
    }
    
    @Override
    public void setCancelled(final boolean cancel) {
        this.duration = (cancel ? Integer.MAX_VALUE : 0);
    }
}
