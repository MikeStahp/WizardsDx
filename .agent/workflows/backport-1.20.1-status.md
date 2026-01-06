---
description: WizardsDx 1.20.1 Backport Status and Remaining Tasks
---

# WizardsDx 1.20.1 Backport - Current Status

## Summary
Backporting WizardsDx from 1.21.1 to 1.20.1 Fabric. Several fixes have been applied, but significant API compatibility issues remain.

## Completed Fixes ✅

### 1. Identifier Constructor Fix
**Problem:** `The constructor Identifier(String, String) is not visible`
**Solution:** Changed all `new Identifier(namespace, path)` to `new Identifier(namespace + ":" + path)` format.
**Files Fixed:**
- `common/src/main/java/net/wizards/content/spells/ArcaneSpells.java` (6 occurrences)

**Note:** Other files still need the same fix - search for `new Identifier(` with two arguments.

### 2. TinyConfig Dependency Fix
**Problem:** `maven.modrinth:tiny-config:2.3.2-fabric` doesn't exist (Modrinth version only supports 1.21+)
**Solution:** Changed to JitPack source: `com.github.ZsoltMolnarrr:TinyConfig:2.3.2`
**Files Fixed:**
- `common/build.gradle` - Added JitPack repo, changed dependency
- `fabric/build.gradle` - Added JitPack repo, changed dependency

### 3. Accessories Dependency Fix
**Problem:** `io.wispforest:accessories-common` doesn't exist for 1.20.1 (it's 1.21+ only)
**Solution:** Commented out accessories-common in common/build.gradle (use Trinkets instead, which is already configured in fabric/build.gradle)
**Files Fixed:**
- `common/build.gradle` - Commented out accessories lines

### 4. NeoForge Module Disabled
**Problem:** NeoForge didn't exist in 1.20.1 (started with 1.20.2+)
**Solution:** Commented out `include 'neoforge'` in settings.gradle
**Files Fixed:**
- `settings.gradle`

## Remaining Issues ❌

### 1. Spell Engine API Changes (MAJOR)
The code uses 1.21+ spell-engine API which has different package structure than 1.20.1.

**Missing/Changed Packages:**
- `net.spell_engine.api.datagen` - SpellBuilder may be in different location
- `net.spell_engine.api.spell.fx` - ParticleBatch, Sound classes
- `net.spell_engine.fx` - SpellEngineParticles, SpellEngineSounds
- `net.spell_engine.api.config` - ConfigFile class

**Files Affected:**
- `ArcaneSpells.java`, `FrostSpells.java`, `FireSpells.java`, `SpellHelpers.java`
- `WizardsEffects.java`
- `Default.java` (config)
- `WizardsMod.java`
- `FrozenParticles.java`

**Action Required:** 
1. Check the 1.20.1 branch of SpellEngine GitHub for correct package paths
2. May need to rewrite spell definitions to match 1.20.1 API
3. Consider using JSON spell definitions instead of programmatic SpellBuilder if API is too different

### 2. AzureLib Armor API Changes
**Missing Package:** `mod.azure.azurelibarmor.common.render.armor`

**Files Affected:**
- `WizardArmorRenderer.java`
- `WizardsClientMod.java`

**Action Required:** Check AzureLib Armor 1.20.1 API for correct imports

### 3. Additional Identifier Fixes Needed
Search for remaining `new Identifier(` with two arguments in these files:
- `WizardSpells.java` (line 436 has `new Identifier("minecraft", "entity.enderman.teleport")`)
- Any other files not yet fixed

## Quick Commands

```powershell
// turbo-all
# Build to check for errors
./gradlew :common:compileJava --console=plain 2>&1

# Find all two-argument Identifier usages
grep -rn "new Identifier(" --include="*.java" | grep -v "+ \":\""
```

## Recommended Approach

1. **Option A (Easier):** Find the original Wizards mod source code for 1.20.1 from ZsoltMolnarrr's GitHub and use that as reference
2. **Option B (Harder):** Manually adapt all API calls to 1.20.1 versions

## Reference Links
- SpellEngine 1.20.1 branch: https://github.com/ZsoltMolnarrr/SpellEngine/tree/1.20.1
- SpellEngine 1.20.1 gradle.properties shows: `spell_engine_version=0.15.12`
- Original Wizards mod: https://github.com/ZsoltMolnarrr/Wizards
