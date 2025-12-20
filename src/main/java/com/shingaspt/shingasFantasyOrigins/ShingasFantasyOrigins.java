package com.shingaspt.shingasFantasyOrigins;

import com.shingaspt.shingasFantasyOrigins.abilities.PoisonImmunity;
import com.shingaspt.shingasFantasyOrigins.abilities.dwarf.DwarfHaste;
import com.shingaspt.shingasFantasyOrigins.abilities.dwarf.DwarfNightVision;
import com.shingaspt.shingasFantasyOrigins.abilities.dwarf.DwarfOreFortune;
import com.shingaspt.shingasFantasyOrigins.abilities.dwarf.DwarfSize;
import com.starshootercity.OriginsAddon;
import com.starshootercity.abilities.types.Ability;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class ShingasFantasyOrigins extends OriginsAddon {

    private final DwarfNightVision dwarfNightVision =  new DwarfNightVision();
    private final DwarfHaste dwarfHaste =  new DwarfHaste();

    @Override
    public @NotNull String getNamespace() {
        return "shingasfantasyorigins";
    }

    @Override
    public @NotNull List<Ability> getRegisteredAbilities() {
        return List.of(
                //Dwarf Abilities
                new DwarfOreFortune(),
                new DwarfSize(),
                new PoisonImmunity(),
                dwarfHaste,
                dwarfNightVision
        );
    }

    @Override
    public void onRegister() {
        getLogger().info("Shingas Fantasy Origins addon has been loaded!");

        getLogger().info("Dwarf abilities registered:");
        getLogger().info("  - shingasfantasyorigins:dwarf_size");
        getLogger().info("  - shingasfantasyorigins:dwarf_haste");
        getLogger().info("  - shingasfantasyorigins:dwarf_nightvision");
        getLogger().info("  - shingasfantasyorigins:dwarf_ore_fortune");

        getLogger().info("Abilities registered:");
        getLogger().info("  - shingasfantasyorigins:poisonimmunity");

        // Start tasks for
        dwarfHaste.startTask(this);
        dwarfNightVision.startTask(this);
    }

}
