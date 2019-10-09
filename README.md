# Home assignment - [Peakon](https://peakon.com/)

<img src="./graphics/logo.jpg" alt="Logo" height="100" />

# Notes
- Code is separated into multiple modules
    - Core
    - Search
    - App(Demo)
- To Build
    - `./gradlew clean assembleDebug`
- To Test
    - `./gradlew clean testDebugUnitTest connectedDebugAndroidTest --full-stacktrace`
- To Test with code coverage
    - `./gradlew testDebugUnitTest connectedDebugAndroidTest -PenableCodeCoverage=true --full-stacktrace`

## Required
- [x] Code Quality
- [x] SOLID Principles 
- [x] Coding Standards
- [x] Testability
- [x] Unit Tests
- [ ] ~~Git Flow~~

## TODO
- [ ] Remove small hack, to get data on first query
- [ ] Move Source of Truth to Persistance

## Addons
- [x] UI Tests
- [x] Gradle Setup
- [x] Dependency Injection
- [ ] ~~Git Rebase~~
- [ ] ~~Git Squash~~
- [x] Git Verified Commits
- [x] Git Release Tags
- [ ] T.D.D
- [x] Architecture Components
- [x] Documentation
- [x] Inline Comments
- [x] Modular
- [x] Scalable
- [x] Mock API Calls
- [x] Code Coverage
- [ ] Sonarqube/CodeClimate

# Clean Up
- [x] Parcelables
- [ ] Remove unused libraries
