# Better ID Extender 1.6.4

A compatibility-focused project for diagnosing and managing legacy mod ID allocation on Minecraft 1.6.4 Forge.

- Minecraft: 1.6.4
- Forge: 9.11.1.964
- Java source/target: 7
- Build toolchain: ForgeGradle 1.0 / Gradle 2.14.1

## Mod architecture and required dependencies

The project builds three independent Forge mods:

1. **LemonAPI-0.1.0.jar** — base compatibility API.
2. **LemonsGUI-0.1.0.jar** — reusable GUI framework; requires Lemon API.
3. **BetterIDExtender-0.3.0.jar** — main Coremod and FML mod; requires both Lemon API and Lemon's GUI.

Dependency graph:

```text
Lemon API
   |
   v
Lemon's GUI
   |
   v
Better ID Extender (also declares Lemon API directly)
```

Install all three JARs into the Minecraft 1.6.4 Forge `mods` directory. Do not merge the JARs or extract their contents.

## Keyboard shortcuts

- **F7** — open the Better ID Extender dashboard.
- **F8** — open the Lemon's GUI welcome screen.

The shortcuts are client-side only. The server-side proxies do not load Minecraft client GUI classes.

## Lemon API

Lemon API provides stable mod/API version metadata, Minecraft version metadata, inclusive ID ranges (`IdRange`), and a thread-safe in-memory ID reservation helper (`IdAllocator`). Reservations are registry-neutral and process-local.

## Lemon's GUI API

The standalone GUI library provides:

- `LemonGuiAPI`: GUI API version checks and screen-opening helper.
- `LemonsGuiScreen`: reusable base screen, framed panel helpers, title rendering, and standard Done button.
- A client-only welcome screen and F8 shortcut to verify the library is loaded.

Other mods can depend on `lemonsgui` and extend `LemonsGuiScreen` without bundling Lemon's GUI classes.

## Better ID Extender dashboard

The F7 dashboard is built on Lemon's GUI and currently has Overview, ID ranges, and Diagnostics tabs. It reports the two prerequisite versions and explicitly identifies which registry functionality is not active yet.

## Current implementation limits

- `BetterIDLoadingPlugin`: Forge 1.6.4 Coremod entry point.
- `BetterIDTransformer`: diagnostic-only ASM transformer; returns original class bytes.
- The ID allocator remains in-memory and is not connected to Forge's real block/item registration pipeline.
- The dashboard is an initial status/configuration UI; it does not claim to expand registries.

Minecraft 1.6.4 Anvil chunk storage uses a legacy 12-bit block-ID layout, so the persistent block-ID ceiling is 4095 in that format. Raising an in-memory registry limit alone does not make larger IDs safe to save, load, or exchange over the network. True expansion requires a save-format and network compatibility design, migration, and integration testing.

## Build

With Java 8 and network access:

```sh
gradle --no-daemon clean build lemonApiJar lemonsGuiJar
```

The three output JARs are written to `build/libs/`.

## Testing and safety

GitHub Actions checks compilation, mod metadata, dependency declarations, class presence, and Java 7 bytecode compatibility. A successful CI build is not a substitute for launching the full modpack in Minecraft. Back up worlds before testing experimental builds.
