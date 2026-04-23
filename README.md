# GitHub Users Browser 📱

An Android app that allows administrators to browse and explore users from the GitHub platform. Built using modern Android development practices and a clean architecture approach.

## 🚀 Features

- 🔍 Browse GitHub users in a paginated list (20 users per fetch).
- 📜 Infinite scrolling to load more users.
- 📄 Detailed user information available on tap.
- ⚡ Users' data loads instantly when app is launched again (cache support or retained state).
- 🧪 Tested loading, error, and UI rendering states.

---

## 📖 User Story

> As an administrator, it is possible to browse all users who are the members of GitHub site then we can see more detailed information about them.

---

## ✅ Acceptance Criteria

- ✅ The administrator can look through fetched users’ information.
- ✅ The administrator can scroll down to see more users’ information with 20 items per fetch.
- ✅ Users’ information must be shown immediately when the administrator launches the application for the second time.
- ✅ Clicking on an item will navigate to the page of user details.

---

## 🧩 Tech Stack

- **Language**: Kotlin
- **UI Toolkit**: Jetpack Compose
- **Dependency Injection**: Koin
- **Image Loading**: Coil
- **Networking**: Retrofit + OkHttp
- **Local Storage**: Datastore
- **Architecture**: MVVM + Clean Architecture
- **Paging**: Jetpack Paging 3
- **Multi-Module Project Structure**: 
  - `shared-test`: Shared base unit test
  - `core`: Shared utilities and UI components
  - `user_data`: Repository and API logic
  - `user-domain`: Business logic and use cases
  - `user_ui`: Feature module for user interface
  - `app`: Main entry point and navigation

---

## 🧪 Unit Testing

- ✅ Paging source tested
- ✅ Datastore source tested
- ✅ Repository logic tested
- ✅ UseCase source tested
- ✅ ViewModel logic tested

---

## 🔧 Setup & Run

1. Clone this repository
2. Open in **Android Studio**
3. Copy and add your Github's PAT to local.properties following the format below
![Add Token Example](images/add_token.png)
4. Add `google-services.json` for package `com.githubusersbrowser`
5. Build and Run on an emulator or real device (API 24+ recommended)

---

## 🔥 Firebase Setup

This project currently uses a single Firebase Android app for package `com.githubusersbrowser`.

Because of that, the project no longer uses `dev` or `staging` product flavors. CI/CD builds:

- `Debug` for non-`release` branches
- `Release` for the `release` branch

### Repository secrets

Add these repository-level secrets:

- `GOOGLE_SERVICES_JSON`
  Store the full `google-services.json` content for `com.githubusersbrowser`.
- `FIREBASE_APP_ID`
  Store the Firebase App ID for `com.githubusersbrowser`.
- `FIREBASE_TOKEN`
  Repository secret used by Firebase App Distribution.
- `ANDROID_KEYSTORE_BASE64`
  Base64-encoded content of the release keystore file.
- `ANDROID_KEYSTORE_PASSWORD`
  Password for the keystore.
- `ANDROID_KEY_PASSWORD`
  Password for alias `upload-key`.

### Branch mapping in CI

- `develop` -> test/distribute `Debug`
- `master` -> test/distribute `Debug`
- `release` -> test/distribute `Release`

---

## 📸 Screenshots

| Home | Detail |
|------|--------|
| ![GitHub Users](./images/home.png) | ![User Detail](./images/detail.png) |

---

## 💡 Future Improvements

- Add search functionality
- Implement user caching for offline mode
- Improve animations and transitions
