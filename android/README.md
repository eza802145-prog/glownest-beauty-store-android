# GlowNest Beauty Store — Native Android App (Kotlin + Jetpack Compose)

This `/android` directory contains the genuine native Android application project for **GlowNest Beauty Store**, built with **Kotlin**, **Jetpack Compose**, **Material 3**, and **Navigation Compose**.

## Project Highlights
- **4 Native Screens**: `HomeScreen`, `ShopScreen`, `ProductDetailsScreen`, and `ContactOrdersScreen` connected via Jetpack Compose `NavHost` and Material 3 bottom navigation.
- **Offline Bundled Assets**: All 10 product photographs and the hero editorial photograph are bundled directly in `app/src/main/res/drawable-nodpi/`.
- **WhatsApp Ordering**: Product Details automatically calculates the Nigerian Naira (`₦`) order total and opens WhatsApp (`2348060677222`) via Android `ACTION_VIEW` with the pre-filled order message.
- **Verified Store Details**: `125 Itire Road, Lawson, Surulere, Lagos, Nigeria.`, `+234 806 067 7222`, and `ezea80214@gmail.com`.

---

## Option 1: Build the APK in Android Studio (Recommended)
1. Download or export this project and open the **`android/`** folder in **Android Studio** (Ladybug or newer).
2. Allow Gradle Sync to finish downloading the Android SDK and Jetpack Compose dependencies.
3. From the top menu, select **Build > Build App Bundle(s) / APK(s) > Build APK(s)**.
4. Once complete, click **Locate** in the notification banner to open:
   `android/app/build/outputs/apk/debug/app-debug.apk`
5. Transfer `app-debug.apk` to your Android phone via USB or cloud drive and tap it to install (enable **Install unknown apps** if prompted by Android).

## Option 2: Build via Command Line (Gradle)
If you have JDK 17, Gradle, and the Android SDK installed:
```bash
cd android
gradle assembleDebug
```
The installable APK will be output to:
`android/app/build/outputs/apk/debug/app-debug.apk`

For a release-signed APK (for distribution or Play Store):
In Android Studio choose **Build > Generate Signed App Bundle / APK... > APK**, create or select a `.jks` keystore, and select the `release` build variant.

## Option 3: Build & Download the APK via GitHub Actions (No Local Android Studio Needed)
1. Push this repository to GitHub.
2. Open the **Actions** tab and run the **Build Android APK** workflow (or push to `main`).
3. When the job completes, download the **`GlowNest-Beauty-Store-debug-apk`** artifact containing `app-debug.apk` and install it directly on your Android phone.
