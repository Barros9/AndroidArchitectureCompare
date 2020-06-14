# Architectures Compare on Android

Simple comparison between the android architectures: MVC, MVP, MVVM.
The app uses retrofit to perform a network call and shows the results according the architecture.

The API is "https://www.reddit.com/r/{SEARCH_PARAM}/top.json"

## MVC
* Model — the data layer, responsible for managing the business logic and handling network or database API.
* View — the UI layer, a visualisation of the data from the Model.
* Controller — the logic layer, gets notified of the user’s behavior and updates the Model as needed.

## MVP
* Model — the data layer, responsible for managing the business logic and handling network or database API.
* View — the UI layer, displays the data and notifies the Presenter about user actions.
* Presenter — the logic layer, retrieves the data from the Model, applies the UI logic and manages the state of the View, decides what to display and reacts to user input notifications from the View.

## MVVM
* Model — the data layer, abstracts the data source. The ViewModel works with the Model to get and save the data.
* View — the UI layer, that informs the ViewModel about the user’s actions.
* ViewModel — the logic layer, exposes streams of data relevant to the View.

## Libraries

|Name|Version|
|----|-------|
|Retrofit|2.8.1|
|RxJava|2.1.1|
|Moshi|2.6.1|
|Glide|4.11.0|
|Navigation|2.2.2|
|SafeArgs|1.0.0|
|Ktlint|9.2.1|
