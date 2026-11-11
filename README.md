# Better ID Extender 1.6.4

A compatibility-focused project for diagnosing and managing legacy mod ID allocation on Minecraft 1.6.4 Forge.

- Minecraft: 1.6.4
- Baseline Forge target: 9.11.1.964 (verify against the exact modpack)
- Java source/target: 7
- Coremod entry point: enabled
- Current status: scaffold / diagnostic-only

## Important compatibility limits

This project does not currently provide unlimited block IDs or claim that every registry can use an arbitrary expanded range.

Minecraft 1.6.4 Anvil chunk storage represents block IDs using the legacy 12-bit block-ID layout (8 low bits plus 4 high bits), giving a persistent block-ID ceiling of 4095 in that format. Increasing an in-memory array or registry alone does not make larger IDs safe to save, load, or exchange over the network.

Item IDs and other registries have separate serialization, mapping, and compatibility constraints. They must be audited independently. True expansion beyond the world-format limit would require a deliberate save-format and network compatibility design, plus migration and interoperability tests.

## Current source contents

- BetterIDLoadingPlugin: Forge 1.6.4 coremod entry point.
- BetterIDTransformer: diagnostic-only ASM transformer; returns original class bytes.
- IdRangeAllocator: registry-neutral ID reservation utility. It is not yet wired into Forge registration.
- BetterIDConfig: minimal Java properties configuration.
- GitHub Actions workflow: attempts a Java 8 / Gradle 2.14.1 build.

## Build

The project uses the legacy ForgeGradle 1.2 toolchain. That toolchain is old and may require repository or Gradle-resolution adjustments as upstream services change.

From a Java 8 environment with network access, run:

    gradle --no-daemon clean build

The output JAR, if legacy dependencies resolve and compilation succeeds, is written to build/libs/.

## Development roadmap

1. Verify the exact Forge 1.6.4 and Java runtime baseline.
2. Audit block, item, biome, potion, enchantment, entity and Tile Entity registration paths independently.
3. Add collision detection and deterministic remapping where the target registry and save format permit it.
4. Add ASM patches only after inspecting exact target bytecode and proving patch invariants.
5. Test fresh worlds, existing worlds, multiplayer handshakes, and mixed-mod environments.
6. Design any beyond-4095 block-ID support as an explicit alternate save/network format rather than pretending the vanilla Anvil format supports it.

## Safety

Do not use an experimental build on an important world without a backup. This initial commit is a development scaffold, not a finished ID-expansion release.
