package com.shingaspt.shingasOrigins;

import com.shingaspt.shingasOrigins.abilities.PoisonImmunity;
import com.shingaspt.shingasOrigins.abilities.dragonborn.DragonFireBall;
import com.shingaspt.shingasOrigins.abilities.dwarf.DwarfHaste;
import com.shingaspt.shingasOrigins.abilities.dwarf.DwarfNightVision;
import com.shingaspt.shingasOrigins.abilities.dwarf.DwarfOreFortune;
import com.shingaspt.shingasOrigins.abilities.dwarf.DwarfSize;
import com.shingaspt.shingasOrigins.abilities.vampire.*;
import com.shingaspt.shingasOrigins.commands.VampTransformToggle;
import com.shingaspt.shingasOrigins.data.DataConfig;
import com.starshootercity.OriginsAddon;
import com.starshootercity.abilities.types.Ability;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class ShingasOrigins extends OriginsAddon {

    private final DwarfNightVision dwarfNightVision = new DwarfNightVision();
    private final DwarfHaste dwarfHaste = new DwarfHaste();
    private final DaylightWeakness dailyLightWeakness = new DaylightWeakness();
    private static DataConfig dataConfig = null;
    private static ShingasOrigins instance = null;

    @Override
    public @NotNull String getNamespace() {
        return "shingasorigins";
    }

    @Override
    public @NotNull List<Ability> getRegisteredAbilities() {
        return List.of(
                //Dwarf Abilities
                new DwarfOreFortune(),
                new DwarfSize(),
                dwarfHaste,
                dwarfNightVision,
                new Carnivorous(),
                new SunProtection(),
                //new RainProtection(),
                new VampireTransformation(),
                dailyLightWeakness,
                new DragonFireBall(),
                new PoisonImmunity()
        );
    }

    @Override
    public void onRegister() {
        getLogger().info("Shingas Fantasy Origins addon has been loaded!");
        instance = this;

        getLogger().info("Loading config...");
        dataConfig = new DataConfig(this);
        getLogger().info("Loaded config!");

        getLogger().info("Registering commands:");
        getCommand("vamptoggle").setExecutor(new VampTransformToggle());
        getLogger().info(" - ");

        getLogger().info("Dwarf abilities registered:");
        getLogger().info("  - shingasorigins:dwarf_size");
        getLogger().info("  - shingasorigins:dwarf_haste");
        getLogger().info("  - shingasorigins:dwarf_nightvision");
        getLogger().info("  - shingasorigins:dwarf_ore_fortune");

        getLogger().info("Vampire abilities registered:");
        getLogger().info("  - shingasorigins:carnivorous");
        getLogger().info("  - shingasorigins:daylight_weakness");
        getLogger().info("  - shingasorigins:sun_protection");
        getLogger().info("  - shingasorigins:vampire_transformation");
        //getLogger().info("  - shingasorigins:rain_protection");

        getLogger().info("Dragonborn abilities registered:");
        getLogger().info("  - shingasorigins:dragon_fireball");

        getLogger().info("Abilities registered:");
        getLogger().info("  - shingasorigins:poisonimmunity");

        // Start tasks for
        dwarfHaste.startTask(this);
        dwarfNightVision.startTask(this);
        dailyLightWeakness.startTask(this);
    }

    @Override
    public void onDisable() {
        dataConfig.save();
    }

    public static DataConfig getDataConfig() { return dataConfig; }

    public static ShingasOrigins getInstance() { return instance; }

}
