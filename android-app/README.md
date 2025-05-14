# Android Food Recognition App

This is a native Android app project for recognizing food components using the device camera or image upload.

## Features
- Capture food images using the camera
- Upload food images from the gallery
- Recognize food components using an external API
- Display recognized components in a clean UI

## Requirements
- Android Studio
- Android SDK
- Internet connection for API calls

## Build and Run
1. Open this project in Android Studio.
2. Sync Gradle and build the project.
3. Run the app on an emulator or physical device.
4. To generate APK, use Build > Build Bundle(s) / APK(s) > Build APK(s).

## API Integration
Replace the placeholder API URL and key in `FoodRecognitionApi.kt` with your actual food recognition API credentials.

## Permissions
The app requests camera and storage permissions at runtime.

## Notes
- This project uses Kotlin and AndroidX libraries.
- UI uses black and white theme for modern look.
