package net.wizards.content.spells;

import net.spell_engine.api.datagen.SpellBuilder;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.fx.SpellEngineParticles;
import net.spell_engine.fx.SpellEngineSounds;
import net.spell_power.api.SpellSchools;
import net.wizards.content.WizardsSounds;

import java.util.List;

import static net.wizards.content.spells.SpellHelpers.*;

/**
 * Contains all Fire school spells.
 */
public class FireSpells {

        public static Entry fire_scorch = add(fire_scorch());

        private static Entry fire_scorch() {
                var spellId = id("fire_scorch");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FIRE;
                spell.group = PRIMARY_GROUP;
                spell.tier = 0;
                spell.sub_tier = 0;
                spell.range = 16;

                spell.active.cast.duration = 1.2F;
                spell.active.cast.animation = "spell_engine:one_handed_projectile_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { fireCastingParticles() };

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_projectile_release";
                spell.release.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_RELEASE.id());

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();
                spell.target.aim.required = true;
                spell.target.aim.sticky = true;

                var damage = SpellBuilder.Impacts.damage(0.6F, 0.6F);
                damage.particles = fireImpactParticles();
                damage.sound = new Sound(WizardsSounds.FIRE_SCORCH_IMPACT.id());

                var fire = SpellBuilder.Impacts.fire(3);
                spell.impacts = List.of(damage, fire);

                configureFireRuneCost(spell);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry fireball = add(fireball());

        private static Entry fireball() {
                var spellId = id("fireball");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FIRE;
                spell.group = PRIMARY_GROUP;
                spell.tier = 0;
                spell.range = 64;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                spell.active.cast.duration = 1.5F;
                spell.active.cast.animation = "spell_engine:one_handed_projectile_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { fireCastingParticles() };

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_projectile_release";

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();

                spell.deliver.type = Spell.Delivery.Type.PROJECTILE;
                spell.deliver.projectile = new Spell.Delivery.ShootProjectile();
                spell.deliver.projectile.launch_properties.velocity = 1F;
                spell.deliver.projectile.launch_properties.sound = new Sound(
                                SpellEngineSounds.GENERIC_FIRE_RELEASE.id());

                var projectile = new Spell.ProjectileData();
                projectile.homing_angle = 1F;
                projectile.client_data = new Spell.ProjectileData.Client();
                projectile.client_data.light_level = 12;
                projectile.client_data.travel_particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.flame.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 3, 0, 0.1F, 0),
                                new ParticleBatch(
                                                "smoke",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 1, 0, 0.1F, 0)
                };
                projectile.client_data.model = new Spell.ProjectileModel();
                projectile.client_data.model.model_id = "wizards:projectile/fireball";
                projectile.client_data.model.scale = 0.5F;
                spell.deliver.projectile.projectile = projectile;

                var damage = SpellBuilder.Impacts.damage(0.8F, 0.8F);
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch("smoke",
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                15, 0.01F, 0.1F),
                                new ParticleBatch(
                                                SpellEngineParticles.flame_medium_b.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                15, 0.1F, 0.2F)
                };
                damage.sound = new Sound(WizardsSounds.FIRE_SCORCH_IMPACT.id());

                var fire = SpellBuilder.Impacts.fire(4);
                spell.impacts = List.of(damage, fire);

                configureFireRuneCost(spell);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry fire_blast = add(fire_blast());

        private static Entry fire_blast() {
                var spellId = id("fire_blast");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FIRE;
                spell.group = PRIMARY_GROUP;
                spell.tier = 1;
                spell.range = 64;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                spell.active.cast.duration = 1.5F;
                spell.active.cast.animation = "spell_engine:one_handed_projectile_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { fireCastingParticles() };

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_projectile_release";

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();

                spell.deliver.type = Spell.Delivery.Type.PROJECTILE;
                spell.deliver.projectile = new Spell.Delivery.ShootProjectile();
                spell.deliver.projectile.launch_properties.velocity = 1.25F;
                spell.deliver.projectile.launch_properties.sound = new Sound(
                                SpellEngineSounds.GENERIC_FIRE_RELEASE.id());

                var projectile = new Spell.ProjectileData();
                projectile.homing_angle = 1F;
                projectile.client_data = new Spell.ProjectileData.Client();
                projectile.client_data.light_level = 12;
                projectile.client_data.travel_particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.flame_spark.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 4, 0, 0.1F, 0),
                                new ParticleBatch(
                                                SpellEngineParticles.flame_medium_b.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 3, 0, 0.1F, 0),
                                new ParticleBatch(
                                                "smoke",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 2, 0, 0.1F, 0)
                };
                projectile.client_data.model = new Spell.ProjectileModel();
                projectile.client_data.model.model_id = "wizards:projectile/fire_blast";
                projectile.client_data.model.scale = 0.9F;
                spell.deliver.projectile.projectile = projectile;

                var damage = SpellBuilder.Impacts.damage(1F, 1.1F);
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch("lava",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                15, 0.5F, 3F),
                                new ParticleBatch(
                                                SpellEngineParticles.flame_medium_b.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                10, 0.1F, 0.2F)
                };

                spell.impacts = List.of(damage);

                spell.area_impact = new Spell.AreaImpact();
                spell.area_impact.radius = 2.5F;
                spell.area_impact.area.distance_dropoff = Spell.Target.Area.DropoffCurve.SQUARED;
                spell.area_impact.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.fire_explosion.id().toString(),
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                2, 0.2F, 0.5F)
                };
                spell.area_impact.sound = new Sound(WizardsSounds.FIREBALL_IMPACT.id());

                configureFireRuneCost(spell);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry fire_breath = add(fire_breath());

        private static Entry fire_breath() {
                var spellId = id("fire_breath");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FIRE;
                spell.tier = 2;
                spell.range = 10;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                SpellBuilder.Casting.channel(spell, 5, 4);
                spell.active.cast.animation = "spell_engine:two_handed_channeling";
                spell.active.cast.sound = new Sound(WizardsSounds.FIRE_BREATH_CASTING.id(), 0);
                spell.active.cast.start_sound = new Sound(WizardsSounds.FIRE_BREATH_START.id());
                spell.active.cast.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.flame.id().toString(),
                                                ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT,
                                                ParticleBatch.Rotation.LOOK, 8, 1, 1, 30),
                                new ParticleBatch(
                                                SpellEngineParticles.flame_medium_a.id().toString(),
                                                ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT,
                                                ParticleBatch.Rotation.LOOK, 4, 1, 1, 30),
                                new ParticleBatch(
                                                SpellEngineParticles.flame_medium_b.id().toString(),
                                                ParticleBatch.Shape.CONE, ParticleBatch.Origin.LAUNCH_POINT,
                                                ParticleBatch.Rotation.LOOK, 4, 1, 1, 30)
                };

                spell.release = new Spell.Release();
                spell.release.sound = new Sound(WizardsSounds.FIRE_BREATH_RELEASE.id());

                spell.target.type = Spell.Target.Type.AREA;
                spell.target.area = new Spell.Target.Area();
                spell.target.area.distance_dropoff = Spell.Target.Area.DropoffCurve.SQUARED;
                spell.target.area.angle_degrees = 40;

                var damage = SpellBuilder.Impacts.damage(0.9F, 0.9F);
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch("lava",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                3, 0.5F, 3F)
                };
                damage.sound = new Sound(WizardsSounds.FIRE_BREATH_IMPACT.id());

                var fire = SpellBuilder.Impacts.fire(2);
                spell.impacts = List.of(damage, fire);

                SpellBuilder.Cost.exhaust(spell, 0.2F);
                configureFireRuneCost(spell);
                SpellBuilder.Cost.cooldown(spell, 10);
                spell.cost.cooldown.proportional = true;

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry fire_meteor = add(fire_meteor());

        private static Entry fire_meteor() {
                var spellId = id("fire_meteor");
                var spell = SpellBuilder.createSpellActive();
                spell.school = SpellSchools.FIRE;
                spell.tier = 3;
                spell.range = 32;

                spell.learn = new Spell.Learn();
                spell.active.scroll = new Spell.Active.Scroll();

                spell.active.cast.duration = 1F;
                spell.active.cast.animation = "spell_engine:one_handed_projectile_charge";
                spell.active.cast.sound = new Sound(SpellEngineSounds.GENERIC_FIRE_CASTING.id(), 0);
                spell.active.cast.particles = new ParticleBatch[] { fireCastingParticles() };

                spell.release = new Spell.Release();
                spell.release.animation = "spell_engine:one_handed_area_release";
                spell.release.sound = new Sound(WizardsSounds.FIRE_METEOR_RELEASE.id());

                spell.target.type = Spell.Target.Type.AIM;
                spell.target.aim = new Spell.Target.Aim();
                spell.target.aim.sticky = true;

                spell.deliver.type = Spell.Delivery.Type.METEOR;
                spell.deliver.meteor = new Spell.Delivery.Meteor();
                spell.deliver.meteor.launch_height = 10;
                spell.deliver.meteor.launch_radius = 4;
                spell.deliver.meteor.launch_properties.velocity = 0.8F;
                spell.deliver.meteor.launch_properties.extra_launch_count = 2;
                spell.deliver.meteor.launch_properties.extra_launch_delay = 5;

                var projectile = new Spell.ProjectileData();
                projectile.client_data = new Spell.ProjectileData.Client();
                projectile.client_data.light_level = 12;
                projectile.client_data.travel_particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.flame.id().toString(),
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 3, 0, 0.1F, 0),
                                new ParticleBatch(
                                                "smoke",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 5, 0.1F, 0.3F, 0),
                                new ParticleBatch(
                                                "campfire_cosy_smoke",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                ParticleBatch.Rotation.LOOK, 6, 0, 0.05F, 0)
                };
                projectile.client_data.model = new Spell.ProjectileModel();
                projectile.client_data.model.model_id = "wizards:projectile/fire_meteor";
                spell.deliver.meteor.projectile = projectile;

                var damage = SpellBuilder.Impacts.damage(1F, 2F);
                damage.action.damage.spell_power_coefficient = 1F;
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch("lava",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                3, 0.5F, 3F)
                };
                damage.sound = new Sound(WizardsSounds.FIRE_BREATH_IMPACT.id());
                spell.impacts = List.of(damage);

                spell.area_impact = new Spell.AreaImpact();
                spell.area_impact.radius = 6;
                spell.area_impact.area.distance_dropoff = Spell.Target.Area.DropoffCurve.SQUARED;
                spell.area_impact.particles = new ParticleBatch[] {
                                new ParticleBatch("lava",
                                                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.CENTER,
                                                90, 1.5F, 6F),
                                new ParticleBatch("flame",
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                100, 0.2F, 0.4F),
                                new ParticleBatch("smoke",
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                90, 0.1F, 0.3F)
                };
                spell.area_impact.sound = Sound.withVolume(WizardsSounds.FIRE_METEOR_IMPACT.id(), 1.5F);

                SpellBuilder.Cost.exhaust(spell, 0.3F);
                configureFireRuneCost(spell);
                SpellBuilder.Cost.cooldown(spell, 10);

                return new Entry(spellId, spell, "", "", null);
        }

        public static Entry fire_wall = add(fire_wall());

        private static Entry fire_wall() {
                var spellId = id("fire_wall");
                var name = "Wall of Flames";
                var description = "Creates a wall of fire, lasting {cloud_duration} seconds, dealing up to {damage} fire spell damage continuously to enemies passing thru.";

                var spell = SpellBuilder.createSpellActive();
                spell.range = 0;
                spell.tier = 4;
                spell.school = SpellSchools.FIRE;

                spell.learn = new Spell.Learn();

                SpellBuilder.Casting.instant(spell);
                SpellBuilder.Release.visuals(spell,
                                "spell_engine:one_handed_area_release_ground_left_to_right",
                                null, null);

                spell.deliver.type = Spell.Delivery.Type.CLOUD;

                var cloud = new Spell.Delivery.Cloud();
                cloud.volume.radius = 0.9F;
                cloud.volume.area.vertical_range_multiplier = 4F;
                cloud.volume.sound = new Sound(WizardsSounds.FIRE_SCORCH_IMPACT.id());
                cloud.delay_ticks = 0;
                cloud.impact_tick_interval = 8;
                cloud.time_to_live_seconds = 8;
                cloud.spawn = new Spell.Delivery.Cloud.Spawn();
                cloud.spawn.sound = new Sound(WizardsSounds.FIRE_WALL_IGNITE.id());
                cloud.spawn.particles = new ParticleBatch[] {
                                new ParticleBatch(
                                                SpellEngineParticles.flame.id().toString(),
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                15, 0.1F, 0.5F)
                };
                cloud.client_data = new Spell.Delivery.Cloud.ClientData();
                cloud.client_data.light_level = 15;
                cloud.client_data.particles = new ParticleBatch[] {
                                new ParticleBatch(SpellEngineParticles.flame_ground.id().toString(),
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                2, 0, 0),
                                new ParticleBatch(SpellEngineParticles.flame_medium_a.id().toString(),
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                3, 0.02F, 0.3F),
                                new ParticleBatch(SpellEngineParticles.flame_medium_b.id().toString(),
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                3, 0.01F, 0.35F),
                                new ParticleBatch(SpellEngineParticles.flame_spark.id().toString(),
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                4, 0.05F, 0.3F),
                                new ParticleBatch("campfire_cosy_smoke",
                                                ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.FEET,
                                                0.1F, 0.05F, 0.1F),
                };

                cloud.placement = SpellBuilder.Deliver.placementByLook(4.4f, -64, 0);
                cloud.additional_placements = List.of(
                                SpellBuilder.Deliver.placementByLook(2.8f, -45, 4),
                                SpellBuilder.Deliver.placementByLook(2f, 0, 4),
                                SpellBuilder.Deliver.placementByLook(2.8f, 45, 4),
                                SpellBuilder.Deliver.placementByLook(4.4f, 64, 4));

                spell.deliver.clouds = List.of(cloud);

                var damage = SpellBuilder.Impacts.damage(0.8F, 0.4F);
                damage.particles = new ParticleBatch[] {
                                new ParticleBatch("smoke",
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                15, 0.01F, 0.1F),
                                new ParticleBatch("flame",
                                                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                                                10, 0.01F, 0.1F)
                };
                damage.sound = new Sound(WizardsSounds.FIRE_SCORCH_IMPACT.id());
                var fire = SpellBuilder.Impacts.fire(2);
                spell.impacts = List.of(damage, fire);

                SpellBuilder.Cost.cooldown(spell, 24);
                SpellBuilder.Cost.item(spell, "runes:fire_stone", 1);
                SpellBuilder.Cost.exhaust(spell, 0.4F);

                return new Entry(spellId, spell, name, description, null);
        }

        /**
         * Initialize all Fire spells. Call this from WizardSpells static init.
         */
        public static void register() {
                // Fields are initialized statically, this method ensures the class is loaded
        }
}
