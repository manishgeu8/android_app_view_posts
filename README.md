## Description

 

* UI

    * XML UI framework

    * Material design

 

* Tech/Tools

    * [Kotlin](https://kotlinlang.org/) to be 100% coverage

    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for async operations

    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection

    * [Jetpack](https://developer.android.com/jetpack)

        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state

    * [Retrofit](https://square.github.io/retrofit/) for networking

 

* Modern Architecture

    * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs

    * MVVM for presentation layer

    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))

    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions

 

 

* MVVM (Model-View-ViewModel)

 

<br/>

<img width="546" alt="Model-View-ViewModel" src="https://github.com/manishgeu8/android_app_view_posts/assets/7812837/7633d040-d749-4fd7-b2be-b5c8af85109a">

 

Architecture comprises three main components:

1. Model: Represents the data and business logic of the application. This includes data objects, data access layers, and repositories. In an Android 

app, it typically includes classes such as data classes, APIs, and local data sources.

 

2. View: Represents the user interface and is responsible for displaying the data. In an Android app, it includes XML layouts, activities, and 

fragments. The view should be as simple as possible and should not contain any business logic.

 

3. ViewModel: Acts as a bridge between the Model and the View, providing a communication layer between the two. The ViewModel exposes the data from 

 

the Model and any other necessary information to the View. It handles UI-related tasks, like handling user input and managing the state of the UI components.

## Presentation patterns layers

* View - Composable screens that consume state, apply effects and delegate events upstream.

* ViewModel - [AAC ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that manages and set the state of the corresponding screen. Additionally, it intercepts UI events as callbacks and produces side-effects. The ViewModel is scoped to the lifetime of the corresponding screen composable in the backstack.

* Model - Data source classes that retrieve content. In a Clean architecture context, one could use UseCases or Interactors that tap into repositories or data sources directly.

 

Ktlint setup (Android Studio - Android Studio Dolphin | 2021.3.1+)

==================================================================

 

1. File -> Settings -> Editor -> Code Style -> General Tab -> Change Hard wrap at from 100-150 and enable wrap on typing.

2. File -> Settings -> Editor -> Expand Code Style and select kotlin -> select imports -> Select single name import for (Top-Level Symbols and Java Statics and Enum memebers)

3. File -> Settings -> Editor -> Expand Code Style and select kotlin -> select imports -> Remove package "import.java.util." from Packages to use imports with ''

4. File -> Settings -> Editor -> Inspections -> search("redundant import") -> Change severity of kotlin redundant imports from warning to error.
