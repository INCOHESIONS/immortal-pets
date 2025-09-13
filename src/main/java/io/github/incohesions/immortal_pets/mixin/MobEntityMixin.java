package io.github.incohesions.immortal_pets.mixin;

import io.github.incohesions.immortal_pets.ImmortalPets;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) { super(entityType, world); }

    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    private void interact(
        final PlayerEntity player,
        final Hand hand,
        final CallbackInfoReturnable<ActionResult> cir
    ) {
        final var self = tameable();

        if (
            self != null &&
            self.getOwner() == player &&
            getWorld() instanceof ServerWorld server &&
            Registries.ITEM.getEntry(player.getStackInHand(hand).getItem()).isIn(ImmortalPets.PET_POISON)
        ) {
            kill(server);
            cir.setReturnValue(ActionResult.PASS);
        }
    }

    @Override
    public boolean damage(
        final ServerWorld world,
        final DamageSource source,
        final float amount
    ) {
        final var self = tameable();

        return (
            (self == null || self.getOwner() == null || source.isOf(DamageTypes.GENERIC_KILL)) &&
            super.damage(world, source, amount)
        );
    }

    @Unique
    private @Nullable Tameable tameable() {
        return this instanceof Tameable ? (Tameable) this : null;
    }
}
