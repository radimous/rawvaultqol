package com.radimous.rawvaultqol;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.WorldManager;
import iskallia.vault.core.vault.objective.Objectives;
import iskallia.vault.core.vault.objective.ParadoxObjective;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("rawvaultqol")
public class Rawvaultqol {
    
    public Rawvaultqol() {}

    public static boolean isRawVault(Vault vault) {
        if (vault == null) {
            return false;
        }
        WorldManager wm = vault.get(Vault.WORLD);
        if (wm == null) {
            return false;
        }
        var theme = wm.get(WorldManager.THEME);
        if (theme == null) {
            return false;
        }
        return theme.toString().contains("the_vault:raw_vault");
    }
    public static boolean isParadoxBuild(Vault vault) {
        if (vault == null) {
            return false;
        }
        Objectives obj = vault.get(Vault.OBJECTIVES);
        if (obj == null) {
            return false;
        }
        for (ParadoxObjective o: obj.getAll(ParadoxObjective.class)){
            if (o != null && o.get(ParadoxObjective.TYPE) == ParadoxObjective.Type.BUILD) {
                return true;
            }
        }
        return false;
    }
}
