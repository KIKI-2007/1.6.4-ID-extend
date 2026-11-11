# Better ID Extender 1.6.4

A compatibility-focused project for diagnosing and managing legacy mod ID allocation on Minecraft 1.6.4 Forge.

- Minecraft: 1.6.4
- Forge: 9.11.1.964
- Java source/target: 7
- Build toolchain: ForgeGradle 1.0 / Gradle 2.14.1
- Required prerequisite: Lemon API 0.1.0 or newer

## Downloads and installation

The build produces two separate mods:

1. **LemonAPI-0.1.0.jar** — prerequisite library. Install this first (or alongside the main mod).
2. **BetterIDExtender-0.2.0.jar** — the Coremod and FML mod. It declares a required dependency on `lemonapi`.

Both JARs belong in the same Minecraft 1.6.4 Forge `mods` directory. Do not merge the JARs or extract their contents into one another.

## What Lemon API provides

Lemon API is a small, separately loadable compatibility library for legacy Forge mods. Its initial API includes:

- Stable mod ID `lemonapi` and API level reporting.
- Minecraft-version compatibility metadata.
- Inclusive ID ranges (`IdRange`).
- A thread-safe, registry-neutral in-memory ID reservation helper (`IdAllocator`).
- A stable API entry point (`LemonAPI`) for dependent mods.

Example:

```java
IdAllocator allocator = LemonAPI.createIdAllocator(2000, 2099);
int id = allocator.allocateFirstFree("examplemod");
```

Reservations are process-local helper data only. Lemon API does not currently hook Forge registration, remap existing mod IDs, patch Anvil storage, or make IDs above the vanilla block storage limit safe.

## Current Better ID Extender status

- `BetterIDLoadingPlugin`: Forge 1.6.4 Coremod entry point.
- `BetterIDTransformer`: diagnostic-only ASM transformer; returns original class bytes.
- `BetterIDMod`: declares and checks the required Lemon API dependency during pre-initialization.
- `IdRangeAllocator`: legacy registry-neutral helper retained in the main mod source.
- GitHub Actions: builds and structurally verifies both JARs.

**This is still an early development build, not a finished ID-expansion implementation.** No live Minecraft integration test is claimed.

## Compatibility limits

Minecraft 1.6.4 Anvil chunk storage represents block IDs using the legacy 12-bit block-ID layout (8 low bits plus 4 high bits), giving a persistent block-ID ceiling of 4095 in that format. Increasing an in-memory array or registry alone does not make larger IDs safe to save, load, or exchange over the network.

Item IDs and other registries have separate serialization, mapping, and compatibility constraints. They must be audited independently. True expansion beyond the world-format limit would require a deliberate save-format and network compatibility design, plus migration and interoperability tests.

## Build

With Java 8 and network access:

```sh
gradle --no-daemon clean build lemonApiJar
```

The output JARs are written to `build/libs/`.

## Safety

Back up worlds before testing experimental builds. Do not use this development build on an important save without a separate backup.
