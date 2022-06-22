# Instaflix

## Description

Instaflix is a simple aplication that consumes the services provided by the TheMovieDB open API

The project was developed in Android Native with Kotlin using:

- Feature oriented architecture with Clean Architecture in each feature
- The app is relatively small, so, it was not "necessary" to implement a module for movie and for tvshow, because they are very likely, but the main
objective is show the advantges of the modularization and thinking in a future development where each module will have a lot of new implementations

The app has the following modules: 

- app: The main module
- home: where movies and series are listed
- movie: the movie detail
- tvshow: the serie detail
- core: a module for common functions
- testutils: for utilities used in the unit and ui tests
- db: database implementation
- networkhelper: helper to detect network connectivity

Clean Arch Layers: 

- [x] Presentation: A layer that interacts with the UI.
- [x] Domain: Contains the business logic of the app, models and usecases.
- [x] Data: Abstract definition of all the data sources and repositories


<img src= "https://user-images.githubusercontent.com/13776168/174953832-7e2c7934-63d9-43ba-9a4b-cb5bee6230f6.png" width=400px>

- Model-View-ViewModel as pattern for the presentation layer

![image](https://www.journaldev.com/wp-content/uploads/2018/04/android-mvvm-pattern.png)


- Gradle dependencies were located with a TOML file
- Repository pattern for the data layer
- Coroutines with Flow for the background tasks
- Dagger Hilt for Dependency Injection
- Retrofit to consume the API Rest+
- Room to persist data locally
- Navigation is done with Navigation Component and DeepLinks

## CI/CD

The Github Repository has two pipelines with Github Actions (One that check the Lint and the other one executes the unit tests), each of them are executed when a PR is raised
pointing to main and develop branches, and when a merge is done to main branch

## Testing

- Unit tests were made with Mockk 
- UI tests were made with Espresso, Mockk and Hilt, the UI tests do not consume network services, all the services are mocked, and each test launch the
needed fragment and not all the activty, this makes the test easier to mantain, faster and better organized
- Page Object Pattern was implemented to UI tests so, the UI test are easier to read, and the implementation is encapsulated in the Pages or Screens

## TO IMPROVE
 
 - Find a better the way to create the Deep Links, after the implementation I realized it is not mantainable and can be exhausting add or edit a destination
 - Migrate to Jetpack Compose
 - Migrate to Kotlin DSL and implement buildSrc module
 - Create a own implementation to load images and not Glide, to improve the way this is being shown
 - Improve the way of handling Errors, with custom Domain Errors and not just Custom exceptions
 - Create integration tests, because the ui tests are being launched in each fragment so, the navigation is not being done, so this should be tested too


### Dependencies used

- [x] Kotlin v1.6.21
- [x] Dagger Hilt v2.42
- [x] Retrofit2 v2.9.0
- [x] Coroutines v1.6.2
- [x] Glide v4.12.0
- [x] Android Navigation v2.4.2
- [x] Safe Args v2.4.2
- [x] MockK v1.12.2
- [x] Espresso v3.4.0
- [x] Room v2.4.2 

## Requirements

- [x] Minimum version: Android 7 - API level 24

### Made by Brayan Arias - stevenson.arias@gmail.com - www.linkedin.com/in/bsav157

 
