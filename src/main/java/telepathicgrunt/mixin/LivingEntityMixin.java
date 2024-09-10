package telepathicgrunt.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private static final MethodHandle apoli$shouldApplyArmorMethodHandle;

    @Unique
    private static final MethodHandle apoli$shouldDamageArmorMethodHandle;

    static {
        try {
            apoli$shouldApplyArmorMethodHandle = MethodHandles.lookup().findSetter(LivingEntity.class, "apoli$shouldApplyArmor", Optional.class);
            apoli$shouldDamageArmorMethodHandle = MethodHandles.lookup().findSetter(LivingEntity.class, "apoli$shouldDamageArmor", Optional.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void setApoliFields(CallbackInfo ci) throws Throwable {
        apoli$shouldApplyArmorMethodHandle.invoke(this, Optional.empty());
        apoli$shouldDamageArmorMethodHandle.invoke(this, Optional.empty());
    }
}