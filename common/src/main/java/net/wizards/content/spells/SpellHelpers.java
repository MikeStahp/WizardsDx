package net.wizards.content.spells;

import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.particle.ParticleBatch;
import net.spell_engine.client.gui.SpellTooltip;
import net.spell_engine.client.util.Color;
import net.spell_engine.client.effect.SpellEngineParticles;
import net.spell_power.api.SpellSchools;
import net.wizards.WizardsMod;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared helper methods, constants, and base Entry type for spell creation.
 */
public class SpellHelpers {
    public static final String PRIMARY_GROUP = "primary";
    public static final float BASIC_PROJECTILE_RANGE = 48F;
    public static final Color ARCANE_COLOR = Color.from(SpellSchools.ARCANE.color);
    public static final Color FIRE_COLOR = Color.from(SpellSchools.FIRE.color);
    public static final Color FROST_COLOR = Color.from(SpellSchools.FROST.color);

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

    public static Identifier id(String name) {
        return new Identifier(WizardsMod.ID + ":" + name);
    }

    public static Spell activeSpellBase() {
        var spell = new Spell();
        spell.type = Spell.Type.ACTIVE;
        spell.active = new Spell.Active();
        spell.active.cast = new Spell.Active.Cast();
        return spell;
    }

    // ========== ARCANE HELPERS ==========

    public static ParticleBatch arcaneCastingParticles() {
        return new ParticleBatch(
                SpellEngineParticles.MagicParticles.get(
                        SpellEngineParticles.MagicParticles.Shape.SPELL,
                        SpellEngineParticles.MagicParticles.Motion.ASCEND).id().toString(),
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.05F, 0.1F)
                .color(ARCANE_COLOR.toRGBA());
    }

    public static void configureArcaneRuneCost(Spell spell) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        spell.cost.item = new Spell.Cost.Item();
        spell.cost.item.id = "runes:arcane_stone";
    }

    // ========== FIRE HELPERS ==========

    public static ParticleBatch fireCastingParticles() {
        return new ParticleBatch(
                SpellEngineParticles.flame.id().toString(),
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                1, 0.05F, 0.1F);
    }

    public static void configureFireRuneCost(Spell spell) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        spell.cost.item = new Spell.Cost.Item();
        spell.cost.item.id = "runes:fire_stone";
    }

    public static ParticleBatch[] fireImpactParticles() {
        return new ParticleBatch[] {
                new ParticleBatch("smoke",
                        ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                        15, 0.01F, 0.1F),
                new ParticleBatch("flame",
                        ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                        10, 0.01F, 0.1F)
        };
    }

    // ========== FROST HELPERS ==========

    public static ParticleBatch frostCastingParticles() {
        return new ParticleBatch(
                SpellEngineParticles.snowflake.id().toString(),
                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.CENTER,
                0.5F, 0.1F, 0.2F);
    }

    public static void configureFrostRuneCost(Spell spell) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        spell.cost.item = new Spell.Cost.Item();
        spell.cost.item.id = "runes:frost_stone";
    }

    public static ParticleBatch[] frostImpactParticles() {
        return new ParticleBatch[] {
                new ParticleBatch(
                        SpellEngineParticles.MagicParticles.get(
                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                .toString(),
                        ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                        50, 0.2F, 0.7F)
                        .color(FROST_COLOR.toRGBA())
        };
    }

    public static void configureCooldown(Spell spell, float duration) {
        if (spell.cost == null) {
            spell.cost = new Spell.Cost();
        }
        spell.cost.cooldown = new Spell.Cost.Cooldown();
        spell.cost.cooldown.duration = duration;
    }
}
