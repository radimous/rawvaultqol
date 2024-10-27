package com.radimous.rawvaultqol.mixin;

import com.radimous.rawvaultqol.Rawvaultqol;
import iskallia.vault.core.vault.player.ClassicListenersLogic;
import iskallia.vault.item.gear.TrinketItem;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(value = ClassicListenersLogic.class, remap = false)
public class NoTrinketUsage {

    @Redirect(method = "lambda$onJoin$8(Lnet/minecraft/server/level/ServerPlayer;Liskallia/vault/core/vault/Vault;Liskallia/vault/gear/trinket/TrinketHelper$TrinketStack;)V",
        at = @At(value = "INVOKE", target = "Liskallia/vault/item/gear/TrinketItem;addUsedVault(Lnet/minecraft/world/item/ItemStack;Ljava/util/UUID;)V"))
    private static void noTrinketUsage(ItemStack stack, UUID vaultId) {
        if (ServerVaults.get(vaultId).isPresent()) {
            if (Rawvaultqol.isRawVault(ServerVaults.get(vaultId).get())) {
                TrinketItem.addFreeUsedVault(stack, vaultId);
                return;
            }
            if (Rawvaultqol.isParadoxBuild(ServerVaults.get(vaultId).get())) {
                TrinketItem.addFreeUsedVault(stack, vaultId);
                return;
            }
        }
        TrinketItem.addUsedVault(stack, vaultId);
    }

}
