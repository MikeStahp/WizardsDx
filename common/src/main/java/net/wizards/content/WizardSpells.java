package net.wizards.content;

import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.client.gui.SpellTooltip;
import net.wizards.content.spells.ArcaneSpells;
import net.wizards.content.spells.FireSpells;
import net.wizards.content.spells.FrostSpells;
import net.wizards.content.spells.SpellHelpers;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Main registry for all wizard spells.
 * Spell definitions are organized by school in the spells package:
 * - {@link ArcaneSpells} - Arcane school spells
 * - {@link FireSpells} - Fire school spells
 * - {@link FrostSpells} - Frost school spells
 */
public class WizardSpells {

        /**
         * Represents a spell entry with its metadata.
         * 
         * @deprecated Use {@link SpellHelpers.Entry} instead
         */
        @Deprecated
        public record Entry(Identifier id, Spell spell, String title, String description,
                        @Nullable SpellTooltip.DescriptionMutator mutator) {
        }

        /**
         * Returns the list of all registered spell entries.
         */
        public static List<SpellHelpers.Entry> getEntries() {
                return SpellHelpers.entries;
        }

        // ========== ARCANE SPELLS ==========
        public static SpellHelpers.Entry arcane_bolt = ArcaneSpells.arcane_bolt;
        public static SpellHelpers.Entry arcane_blast = ArcaneSpells.arcane_blast;
        public static SpellHelpers.Entry arcane_missile = ArcaneSpells.arcane_missile;
        public static SpellHelpers.Entry arcane_beam = ArcaneSpells.arcane_beam;
        public static SpellHelpers.Entry arcane_blink = ArcaneSpells.arcane_blink;

        // ========== FIRE SPELLS ==========
        public static SpellHelpers.Entry fire_scorch = FireSpells.fire_scorch;
        public static SpellHelpers.Entry fireball = FireSpells.fireball;
        public static SpellHelpers.Entry fire_blast = FireSpells.fire_blast;
        public static SpellHelpers.Entry fire_breath = FireSpells.fire_breath;
        public static SpellHelpers.Entry fire_meteor = FireSpells.fire_meteor;
        public static SpellHelpers.Entry fire_wall = FireSpells.fire_wall;

        // ========== FROST SPELLS ==========
        public static SpellHelpers.Entry frost_shard = FrostSpells.frost_shard;
        public static SpellHelpers.Entry frostbolt = FrostSpells.frostbolt;
        public static SpellHelpers.Entry frost_nova = FrostSpells.frost_nova;
        public static SpellHelpers.Entry frost_shield = FrostSpells.frost_shield;
        public static SpellHelpers.Entry frost_blizzard = FrostSpells.frost_blizzard;

        /**
         * Initializes all spells. Call this during mod initialization.
         */
        public static void register() {
                // Trigger static initialization of spell classes
                ArcaneSpells.register();
                FireSpells.register();
                FrostSpells.register();
        }
}
