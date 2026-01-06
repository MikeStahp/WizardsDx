package net.wizards.content.spells;

import net.spell_engine.api.datagen.SpellBuilder;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
import net.spell_power.api.SpellSchools;
import net.wizards.content.WizardsSounds;
import net.wizards.effect.WizardsEffects;

import java.util.List;

import static net.wizards.content.spells.SpellHelpers.*;

/**
 * Contains all Frost school spells.
 */
public class FrostSpells {

        public static Entry frost_shard = add(frost_shard());

        private static Entry frost_shard() {
                var spellId = id("frost_shard");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FROST;
                spell.group = PRIMARY_GROUP;
                spell.tier = 0;
                spell.range = 48;

                spell.active.cast.duration = 1F;
                spell.active.cast.animation = "spell_engine:one_handed_projectile_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FROST_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { frostCastingParticles() };

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_projectile_release";
                spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FROST_RELEASE.id());

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();

                spell.deliver.type = Spell.Delivery.Type.PROJECTILE;
                spell.deliver.projectile = new Spell.Delivery.ShootProjectile();
                spell.deliver.projectile.launch_properties.velocity = 1.2F;

                var projectile = new Spell.ProjectileData();
                projectile.perks.bounce = 2;
                projectile.client_data = new Spell.ProjectileData.Client();
                projectile.client_data.travel_particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 1, 0.1F, 0.2F, 0)
                                                .color(FROST_COLOR.toRGBA())
                };
                projectile.client_data.model = new Spell.ProjectileModel();
                projectile.client_data.model.model_id = "wizards:projectile/frost_shard";
                projectile.client_data.model.scale = 0.75F;
                spell.deliver.projectile.projectile = projectile;

                var damage = SpellBuilder.Impacts.damage(0.6F, 1F);
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                25, 0.2F, 0.7F)
                                                .color(FROST_COLOR.toRGBA())
                };
                damage.sound = new Sound(WizardsSounds.FROST_SHARD_IMPACT.id());
                spell.impacts = List.of(damage);

                configureFrostRuneCost(spell);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry frostbolt = add(frostbolt());

        private static Entry frostbolt() {
                var spellId = id("frostbolt");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FROST;
                spell.group = PRIMARY_GROUP;
                spell.tier = 1;
                spell.sub_tier = 2;
                spell.range = 64;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                spell.active.cast.duration = 1.1F;
                spell.active.cast.animation = "spell_engine:one_handed_projectile_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FROST_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { frostCastingParticles() };

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_projectile_release";

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();

                spell.deliver.type = Spell.Delivery.Type.PROJECTILE;
                spell.deliver.projectile = new Spell.Delivery.ShootProjectile();
                spell.deliver.projectile.launch_properties.velocity = 1.2F;
                spell.deliver.projectile.launch_properties.sound = new Sound(
                                SpellEngineSounds.GENERIC_FROST_RELEASE.id());

                var projectile = new Spell.ProjectileData();
                projectile.homing_angle = 2F;
                projectile.perks.ricochet = 2;
                projectile.perks.bounce = 2;
                projectile.client_data = new Spell.ProjectileData.Client();
                projectile.client_data.light_level = 12;
                projectile.client_data.travel_particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 4, 0, 0.1F, 0),
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 1, 0.1F, 0.2F, 0)
                                                .color(FROST_COLOR.toRGBA())
                };
                projectile.client_data.model = new Spell.ProjectileModel();
                projectile.client_data.model.model_id = "wizards:projectile/frostbolt";
                projectile.client_data.model.scale = 0.5F;
                spell.deliver.projectile.projectile = projectile;

                var damage = SpellBuilder.Impacts.damage(0.8F, 1F);
                damage.particles = frostImpactParticles();
                damage.sound = new Sound(SpellEngineSounds.GENERIC_FROST_IMPACT.id());

                var slowness = SpellBuilder.Impacts.effectAdd(WizardsEffects.frostSlowness.id.toString(), 5, 0, 1);
                slowness.action.status_effect.apply_limit = new Spell.Impact.Action.StatusEffect.ApplyLimit();
                slowness.action.status_effect.apply_limit.health_base = 100;
                slowness.action.status_effect.apply_limit.spell_power_multiplier = 4;
                slowness.action.status_effect.show_particles = false;
                slowness.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                25, 0.1F, 0.4F)
                };

                spell.impacts = List.of(damage, slowness);

                configureFrostRuneCost(spell);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry frost_nova = add(frost_nova());

        private static Entry frost_nova() {
                var spellId = id("frost_nova");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FROST;
                spell.tier = 2;
                spell.range = 6;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                spell.active.cast.duration = 0.5F;
                spell.active.cast.animation = "spell_engine:one_handed_area_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FROST_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { frostCastingParticles() };

                spell.target.type = Spell.Target.Type.AREA;
                spell.target.area = new Spell.Target.Area();
                spell.target.area.vertical_range_multiplier = 0.5F;

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_area_release";
                spell.release.sound = new Sound(WizardsSounds.FROST_NOVA_RELEASE.id());
                spell.release.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                130, 0.2F, 0.6F),
                                new ParticleBatch(
                                                SpellEngineParticles.frost_shard.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                130, 0.5F, 0.9F)
                };
                spell.release.particles_scaled_with_ranged = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.area_effect_293.id().toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.GROUND,
                                                1, 0, 0)
                                                .scale(0.8F)
                                                .color(0x99E6FFFFL)
                };

                var damage = SpellBuilder.Impacts.damage(0.5F, 0.8F);
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                30, 0.2F, 0.7F)
                                                .color(FROST_COLOR.toRGBA())
                };
                damage.sound = new Sound(WizardsSounds.FROST_NOVA_DAMAGE_IMPACT.id());

                var frozen = SpellBuilder.Impacts.effectAdd(WizardsEffects.frozen.id.toString(), 6, 1, 9);
                frozen.action.status_effect.apply_mode = Spell.Impact.Action.StatusEffect.ApplyMode.ADD;
                frozen.action.status_effect.apply_limit = new Spell.Impact.Action.StatusEffect.ApplyLimit();
                frozen.action.status_effect.apply_limit.health_base = 60;
                frozen.action.status_effect.apply_limit.spell_power_multiplier = 4;
                frozen.action.status_effect.show_particles = false;
                frozen.sound = new Sound(WizardsSounds.FROST_NOVA_EFFECT_IMPACT.id());

                spell.impacts = List.of(damage, frozen);

                SpellBuilder.Cost.exhaust(spell, 0.2F);
                configureFrostRuneCost(spell);
                SpellBuilder.Cost.cooldown(spell, 10);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry frost_shield = add(frost_shield());

        private static Entry frost_shield() {
                var spellId = id("frost_shield");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FROST;
                spell.tier = 3;
                spell.range = 0;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                SpellBuilder.Casting.instant(spell);

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_area_release";
                spell.release.sound = new Sound(WizardsSounds.FROST_SHIELD_RELEASE.id());
                spell.release.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                90, 0.1F, 0.35F),
                                new ParticleBatch(
                                                SpellEngineParticles.frost_shard.id().toString(),
                                                ParticleBatch.Shape.WIDE_PIPE, ParticleBatch.Origin.FEET,
                                                50, 0.1F, 0.3F)
                };

                var shield = SpellBuilder.Impacts.effectSet(WizardsEffects.frostShield.id.toString(), 8, 0);
                shield.action.status_effect.show_particles = false;
                spell.impacts = List.of(shield);

                SpellBuilder.Cost.exhaust(spell, 0.3F);
                configureFrostRuneCost(spell);
                SpellBuilder.Cost.cooldown(spell, 30);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry frost_blizzard = add(frost_blizzard());

        private static Entry frost_blizzard() {
                var spellId = id("frost_blizzard");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FROST;
                spell.tier = 4;
                spell.range = 32;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                SpellBuilder.Casting.channel(spell, 8, 12);
                spell.active.cast.animation = "spell_engine:one_handed_sky_charge";
                spell.active.cast.sound = new Sound(WizardsSounds.FROST_BLIZZARD_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { frostCastingParticles() };

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();
                spell.target.aim.sticky = true;

                spell.deliver.type = Spell.Delivery.Type.METEOR;
                spell.deliver.meteor = new Spell.Delivery.Meteor();
                spell.deliver.meteor.launch_radius = 3;
                spell.deliver.meteor.launch_properties.velocity = 1F;
                spell.deliver.meteor.launch_properties.extra_launch_count = 3;
                spell.deliver.meteor.launch_properties.extra_launch_delay = 4;

                var projectile = new Spell.ProjectileData();
                projectile.divergence = 8;
                projectile.client_data = new Spell.ProjectileData.Client();
                projectile.client_data.travel_particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 3, 0, 0.1F, 0),
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 1, 0.1F, 0.2F, 0)
                                                .color(FROST_COLOR.toRGBA())
                };
                projectile.client_data.model = new Spell.ProjectileModel();
                projectile.client_data.model.model_id = "wizards:projectile/frost_shard";
                projectile.client_data.model.scale = 0.8F;
                spell.deliver.meteor.projectile = projectile;

                spell.release = new Spell.Release();
                spell.release.sound = new Sound(WizardsSounds.FIRE_BREATH_RELEASE.id());

                var damage = SpellBuilder.Impacts.damage(0.7F, 0.2F);
                damage.sound = new Sound(SpellEngineSounds.GENERIC_FROST_IMPACT.id());

                var slowness = SpellBuilder.Impacts.effectAdd(WizardsEffects.frostSlowness.id.toString(), 3, 0, 1);
                slowness.action.status_effect.apply_limit = new Spell.Impact.Action.StatusEffect.ApplyLimit();
                slowness.action.status_effect.apply_limit.health_base = 80;
                slowness.action.status_effect.apply_limit.spell_power_multiplier = 4;
                slowness.action.status_effect.show_particles = false;
                slowness.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                15, 0.2F, 0.7F)
                                                .color(FROST_COLOR.toRGBA()),
                                new ParticleBatch(
                                                SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                5, 0.1F, 0.4F)
                };

                spell.impacts = List.of(damage, slowness);

                spell.area_impact = new Spell.AreaImpact();
                spell.area_impact.radius = 3;
                spell.area_impact.area.distance_dropoff = Spell.Target.Area.DropoffCurve.SQUARED;
                spell.area_impact.particles = new ParticleBatch[] {
                                new ParticleBatch(SpellEngineParticles.snowflake.id().toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                20, 0.1F, 0.3F),
                                new ParticleBatch(
                                                SpellEngineParticles.MagicParticles.get(
                                                                SpellEngineParticles.MagicParticles.Shape.FROST,
                                                                SpellEngineParticles.MagicParticles.Motion.BURST).id()
                                                                .toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                15, 0.2F, 0.4F)
                                                .color(FROST_COLOR.toRGBA()),
                                new ParticleBatch(
                                                SpellEngineParticles.frost_shard.id().toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                15, 0.2F, 0.4F)
                };
                spell.area_impact.sound = Sound.withVolume(WizardsSounds.FROST_SHARD_IMPACT.id(), 1.5F);

                SpellBuilder.Cost.exhaust(spell, 0.4F);
                configureFrostRuneCost(spell);
                SpellBuilder.Cost.cooldown(spell, 16);
                spell.cost.cooldown.proportional = true;

                return new Entry(spellId, spell, "", "", null);
        }

        /**
         * Initialize all Frost spells. Call this from WizardSpells static init.
         */
        public static void register() {
                // Fields are initialized statically, this method ensures the class is loaded
        }
}
