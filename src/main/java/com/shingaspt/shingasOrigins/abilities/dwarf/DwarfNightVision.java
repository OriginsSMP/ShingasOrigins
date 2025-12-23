package com.shingaspt.shingasOrigins.abilities.dwarf;

import com.starshootercity.abilities.types.Ability;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class DwarfNightVision implements Ability {

    @Override
    public @NotNull Key getKey() {
        return Key.key("shingasfantasyorigins", "dwarf_nightvision");
    }

    public void startTask(Plugin plugin) {
        Bukkit.getAsyncScheduler().runAtFixedRate(
                plugin,
                scheduledTask -> {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        runForAbility(player, p -> {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 220, 1));
                        });
                    }
                },
                0L,
                200L,
                TimeUnit.MILLISECONDS
        );
    }
}
