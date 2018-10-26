# Demo MVVM

This app uses a Model-View-ViewModel (MVVM) architecture for the presentation layer.

Presentation layer

A main activity that handles navigation.
A fragment to display the list of products.

Fragments :: corresponds to a MVVM View. The View and ViewModel communicate using LiveData.

ViewModel :: objects expose data using LiveData objects. LiveData allows you to observe changes to data across multiple components of your app without creating explicit and rigid dependency paths between them.

Views :: including the fragments used in this sample, subscribe to corresponding LiveData objects. Because LiveData is lifecycle-aware, it doesn’t push changes to the underlying data if the observer is not in an active state, and this helps to avoid many common bugs.

Data binding :: data is shown using data binding.

Other component used :: Retrofit,Okhttp,Cache for off line mode,  rxJava to observe network changes.

