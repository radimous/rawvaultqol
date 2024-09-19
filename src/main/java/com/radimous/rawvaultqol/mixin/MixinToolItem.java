package com.radimous.rawvaultqol.mixin;

import com.radimous.rawvaultqol.Rawvaultqol;
import iskallia.vault.item.tool.ToolItem;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(value = ToolItem.class, remap = false)
public class MixinToolItem {
    @Inject(method = "damageItem", at = @At("HEAD"), cancellable = true)
    private <T extends LivingEntity> void noDmg(ItemStack stack, int amount, T entity, Consumer<T> onBroken, CallbackInfoReturnable<Integer> cir){
        Level world = entity.level;
        ServerVaults.get(world).ifPresent(vault -> {
            if (Rawvaultqol.isRawVault(vault)) {
                cir.setReturnValue(0);
            }
        });
    }
}
