package net.wizards.content;

import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.client.gui.SpellTooltip;
import net.wizards.content.spells.ArcaneSpells;
import net.wizards.content.spells.FireSpells;
import net.wizards.content.spells.FrostSpells;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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
         */
        public record Entry(Identifier id, Spell spell, String title, String description,
                        @Nullable SpellTooltip.DescriptionMutator mutator) {
        }

        /**
         * List of all registered spell entries.
         */
        public static final List<Entry> entries = new ArrayList<>();

        /**
         * Registers a spell entry and returns it.
         * 
         * @param entry The spell entry to register
         * @return The registered entry
         */
        public static Entry add(Entry entry) {
                entries.add(entry);
                return entry;
        }

        // ========== ARCANE SPELLS ==========
        public static Entry arcane_bolt = ArcaneSpells.arcane_bolt;
        public static Entry arcane_blast = ArcaneSpells.arcane_blast;
        public static Entry arcane_missile = ArcaneSpells.arcane_missile;
        public static Entry arcane_beam = ArcaneSpells.arcane_beam;
        public static Entry arcane_blink = ArcaneSpells.arcane_blink;

        // ========== FIRE SPELLS ==========
        public static Entry fire_scorch = FireSpells.fire_scorch;
        public static Entry fireball = FireSpells.fireball;
        public static Entry fire_blast = FireSpells.fire_blast;
        public static Entry fire_breath = FireSpells.fire_breath;
        public static Entry fire_meteor = FireSpells.fire_meteor;
        public static Entry fire_wall = FireSpells.fire_wall;

        // ========== FROST SPELLS ==========
        public static Entry frost_shard = FrostSpells.frost_shard;
        public static Entry frostbolt = FrostSpells.frostbolt;
        public static Entry frost_nova = FrostSpells.frost_nova;
        public static Entry frost_shield = FrostSpells.frost_shield;
        public static Entry frost_blizzard = FrostSpells.frost_blizzard;

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
