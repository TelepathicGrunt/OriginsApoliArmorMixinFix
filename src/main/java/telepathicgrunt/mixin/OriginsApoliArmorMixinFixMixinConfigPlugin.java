package telepathicgrunt.mixin;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OriginsApoliArmorMixinFixMixinConfigPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        if (mixinClassName.contains("telepathicgrunt.mixin.LivingEntityMixin")) {
            for (FieldNode fieldNode : targetClass.fields) {
                if (fieldNode.name.equals("apoli$shouldApplyArmor") || fieldNode.name.equals("apoli$shouldDamageArmor")) {
                    for (MethodNode methodNode : targetClass.methods) {
                        if (methodNode.name.equals("<init>")) {
                            methodNode.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/util/Optional", "empty", "()Ljava/util/Optional;"));
                            methodNode.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC, "net/minecraft/entity/LivingEntity", "apoli$shouldApplyArmor", "Ljava/util/Optional;"));
                            methodNode.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/util/Optional", "empty", "()Ljava/util/Optional;"));
                            methodNode.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC, "net/minecraft/entity/LivingEntity", "apoli$shouldDamageArmor", "Ljava/util/Optional;"));
                        }
                    }
                }
            }
        }
    }
}
