package io.github.incohesions.immortal_pets.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @SuppressWarnings("ConstantConditions")
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    void onApplyDamage(
        final ServerWorld world,
        final DamageSource source,
        final float amount,
        final CallbackInfoReturnable<Boolean> cir
    ) {
        Tameable self;

        try {
            self = (Tameable) this;
        } catch (ClassCastException ignored) { return; }

        if (self.getOwner() != null) cir.cancel();
    }
}
