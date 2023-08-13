# KIllTheInterview (🚧 IN PROGRESS 🚧)

"KillTheInterview" is a mobile application which purpose is to help users prepare for a job interview. It is also my current hobby/playground project. It is build with:
- [KMM (Kotlin Multiplatform Mobile)][kmm]
- [Jetpack Compose][compose]
- [OpenAI API Kotlin client][openAiClient]. 

It was build in [Android Studio Giraffe | 2022.3.1 Beta 4][androidStudio].


# 🚧🚧🚧

"KillTheInterview" is still in the early stages of development.

## Features Overview

"KillTheInterview" purpose is to help users prepare for a technical job interview. App main features are:
- Interview simulation, using curated questions pool or AI powered
- Learning with curated questions & answers pool

Capabilities: 
- Fetching curated questions
- Generating questions using Open AI API
- Persisting bookmarked questions in local database

## Architecture

Project is built out of [KaMPKit][kampkit], which is a starter project for Kotlin Multiplatform Mobile apps.
The app is written in MVVM/MVI, aiming to follow [Modern App Architecture][modernAppArchitecture] guidelines throughout both, Android and KMM modules.

Objectives:
- Layered architecture with data, domain & UI layers
- Unidireactional Data Flow, Single Soure Of Truth
- Sharing as much code as possible in KMM module, so it can be reused throughout platforms
- Testability

Android module follows single-Activity architecture with Compose Navigation, where each screen has its own ViewModel from KMM module. iOS module development is not yet started.




 [modernAppArchitecture]: https://developer.android.com/topic/architecture#modern-app-architecture
 [androidStudio]: https://androidstudio.googleblog.com/2023/05/android-studio-giraffe-beta-4-now.html
 [openAiClient]: https://github.com/aallam/openai-kotlin
 [compose]: https://developer.android.com/jetpack/compose
 [kmm]: https://kotlinlang.org/docs/multiplatform-mobile-getting-started.html
 [kampkit]: https://github.com/touchlab/KaMPKit


