package com.radimous.rawvaultqol.mixin;

import com.radimous.rawvaultqol.Rawvaultqol;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.item.tool.ToolItem;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(value = ToolItem.class, remap = false)
public class MixinToolItem {
    @Redirect(method = "damageItem", at = @At(value = "INVOKE", target = "Ljava/util/Optional;isEmpty()Z"))
    private boolean noDmg(Optional<Vault> instance){
        if (instance.isEmpty()){
            System.out.println("EMPTY");
            return true; // not in vault
        }
        if (Rawvaultqol.isRawVault(instance.get())){
            System.out.println("RAW");
            return true; // raw vault treated as not in vault
        }
        System.out.println("IN VAULT");
        return false; // in vault
    }
}
