# Smart Pantry Manager

<div align="center">
  <img src="app/src/main/res/drawable/nxg_logo.png" alt="NxG Technologies logo" width="120">
  <p><strong>A project by NxG Technologies</strong></p>
</div>

A simple Android **Java** application to track leftover ingredients and suggest only recipes whose full ingredient requirements are met.

## Database
Local SQLite via `SQLiteOpenHelper`; pantry records persist across restarts. On first installation, the database is seeded with 20 recipes and ingredient quantities.

## Setup and run
1. Open the `SmartPantryManager` folder in Android Studio (not an individual Java file).
2. Use a JDK 17 Android Studio Gradle JDK, install Android SDK 34 and sync Gradle.
3. Select the `app` run configuration and an emulator/device (Android 7.0/API 24 or later), then press Run.
4. Add pantry ingredients including a positive numeric quantity and a unit (`pcs`, `g`, `kg`, `ml`, `L`, `slices`). Expiry dates are optional, but if provided use `yyyy-MM-dd`.
5. Visit Pantry for edit/delete, and Suggested Recipes to see only complete matches. Tap Recipe Details for the method.

## Screen arrangement
The original home buttons remain: Add Ingredient, View Pantry, View Recipes, Settings. The same screen information and simple vertical layouts are retained, implemented in Java with native Android views instead of the original Kotlin Compose code as required by the assignment. Recipe Details is a separate screen.

## Matching behavior
Names are case insensitive with basic plural normalization. Quantities must meet or exceed requirements and equivalent units are converted (`kg`/`g`, `L`/`ml`). Incompatible units never match; quantities in multiple pantry rows are summed when units are compatible. Recipe ingredients have defined quantities.

## Limitations
The Settings alert switch saves its preference, but no automatic expiry notification is implemented: notifications were not required and the supplied app only exposed a switch. Recipe ingredients are seeded as fixed records; CRUD operations apply to user pantry items.

## Evidence
Submit an authentic GitHub development history, a 5–7 minute narrated demonstration and a written report separately as required by the assignment. These personal assessment deliverables have not been fabricated.
