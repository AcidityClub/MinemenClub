// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.event.potion;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.event.entity.EntityEvent;

public abstract class PotionEffectEvent extends EntityEvent
{
    private final PotionEffect effect;
    
    public PotionEffectEvent(final LivingEntity what, final PotionEffect effect) {
        super((Entity)what);
        this.effect = effect;
    }
    
    public LivingEntity getEntity() {
        return (LivingEntity)super.getEntity();
    }
    
    public PotionEffect getEffect() {
        return this.effect;
    }
}
