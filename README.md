# Architectures Compare on Android

Simple comparison between the android architectures: MVC, MVP, MVVM, MVI.
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

## MVI
* Model — the data layer, abstracts the data source. The ViewModel works with the Model to get and save the data.
* View — the UI layer, receives states and update the UI.
* Intent — the action layer, user’s intent when interacting with the UI, for example, a click on a button.
* ViewModel — the logic layer, accepts user's intent as input and produces states as output


## Libraries

|Name|Version|
|----|-------|
|Retrofit|2.9.0|
|Coroutine|1.3.7|
|RxJava|3.0.0|
|Moshi|2.9.0|
|Glide|4.11.0|
|Navigation|2.3.0|
|SafeArgs|1.0.0|
|Ktlint|9.2.1|
