// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.event.potion;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

public class PotionEffectExtendEvent extends PotionEffectAddEvent
{
    private final PotionEffect oldEffect;
    
    public PotionEffectExtendEvent(final LivingEntity entity, final PotionEffect effect, final PotionEffect oldEffect) {
        super(entity, effect);
        this.oldEffect = oldEffect;
    }
    
    public PotionEffect getOldEffect() {
        return this.oldEffect;
    }
}
