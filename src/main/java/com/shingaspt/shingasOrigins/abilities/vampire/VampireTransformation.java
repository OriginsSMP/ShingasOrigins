package com.shingaspt.shingasOrigins.abilities.vampire;

import com.shingaspt.shingasOrigins.ShingasOrigins;
import com.shingaspt.shingasOrigins.data.DataConfig;
import com.starshootercity.abilities.types.VisibleAbility;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class VampireTransformation implements VisibleAbility, Listener {


    @Override
    public String description() {
        return "Transform other players into vampires by killing them.";
    }

    @Override
    public String title() {
        return "Vampire Transformation";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("shingasorigins", "vampire_transformation");
    }

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Player killed)) return;
        Player killer = killed.getKiller();
        runForAbility(killer, player -> {
            DataConfig dataConfig = ShingasOrigins.getDataConfig();
            if (dataConfig.get(player.getUniqueId())) {
                Bukkit.getLogger().info("" + dataConfig.get(player.getUniqueId()));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "origin set " + killed.getName() + " origin vampire");
            }
        });
    }
}
