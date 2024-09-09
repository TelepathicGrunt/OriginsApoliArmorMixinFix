package telepathicgrunt;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class OriginsApoliArmorMixinFixCanceller implements MixinCanceller {
	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		if (mixinClassName.contains("io.github.apace100.apoli.mixin.LivingEntityMixin")) {
			OriginsApoliArmorMixinFixMod.LOGGER.info("Cancelled mixin: {}", mixinClassName);
			return true;
		}

		return false;
	}
}