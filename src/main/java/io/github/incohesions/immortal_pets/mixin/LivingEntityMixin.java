package io.github.incohesions.immortal_pets.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class TameableEntityMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void onApplyDamage(
        final ServerWorld world,
        final DamageSource source,
        final float amount,
        final CallbackInfoReturnable<Boolean> cir
    ) {
        if ((Object) this instanceof TameableEntity) {
            cir.cancel();
        }
    }
}
