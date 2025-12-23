package com.shingaspt.shingasOrigins.abilities.vampire;

import com.starshootercity.abilities.types.Ability;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class RainProtection implements Ability, Listener {

    @Override
    public @NotNull Key getKey() {
        return Key.key("shingasorigins", "rain_protection");
    }

    private boolean isDamageFreeze(EntityDamageEvent.DamageCause cause) {
        return EntityDamageEvent.DamageCause.FREEZE ==  cause;
    }

    private boolean isWearingUmbrella(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null || helmet.getType() != Material.COPPER_HELMET) return false;
        ItemMeta meta = helmet.getItemMeta();
        return meta.getCustomModelDataComponent().getFloats().contains(1000.0f);
    }

    private boolean isInPowderSnow(Player p) {
        return p.getLocation().getBlock().getType() == Material.POWDER_SNOW;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (isDamageFreeze(event.getCause())) {
                runForAbility(player, p -> {
                    if (isWearingUmbrella(p) && !isInPowderSnow(p)) {
                        event.setCancelled(true);
                    }
                });
            }
        }
    }

}
