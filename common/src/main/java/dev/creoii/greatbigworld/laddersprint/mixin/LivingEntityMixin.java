package dev.creoii.greatbigworld.laddersprint.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyExpressionValue(method = "handleOnClimbable", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(DD)D"))
    private double gbw$handleClimbSprinting(double g) {
        LivingEntity living = (LivingEntity) (Object) this;

        Direction moveDir = g < 0d ? Direction.DOWN : Direction.UP;
        if (living.isSprinting() && moveDir == Direction.getFacingAxis(living, Direction.Axis.Y)) {
            g += moveDir == Direction.DOWN ? -.1d : .1d;
        }

        return g;
    }
}
