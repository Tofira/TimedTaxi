## TimedTaxi
TimedTaxi is an app for providing the most accurate ETAs for Taxi companies.

You can download the APK [here](https://github.com/Tofira/TimedTaxi/blob/master/TimedTaxi.apk?raw=true)


![Alt Text](https://raw.githubusercontent.com/Tofira/TimedTaxi/master/app_gif.gif)


### Architecture
The app was built using an MVP architecture. For its Model implementation, the app uses the Repository pattern.
The general app architecture can be seen in the below diagram -


![Alt Text](https://raw.githubusercontent.com/Tofira/TimedTaxi/master/timedtaxi_diagram.png)

 

### Data Logic
The app uses two API (currently mock data) calls - 
1. Fetch available Taxi companies list - this returns a JSON of Taxi objects.
2. Fetch updated ETAs for the provided taxis - this returns a JSON of ETAs and Taxi IDs.

The logic for data fetching is explained below - 
* The Presenter is in charge of fetching the updated ETAs periodically from the Repository. When the View alerts the Presenter to start monitoring, the presenter queries the Repository for available taxis, and then queries the Repository periodically for updated ETAs.
* In the Model layer, the Repository uses a Map to manage its Taxis. Whenever there's a need to fetch new ETAs, the Repository queries the RemoteDataSource. When the RemoteDataSource returns an answer, the Repository updates the Map with the newly fetched ETAs, and updates the Presenter.
* The presenter, in turn, alerts the View that the new list of Taxis, with updates ETAs, is ready.

### How to Replace the Mock Data with Real Data
In order to use 'real' data, simply change the implementation of the class TaxisAPI (which is being used by the TaxisRemoteDataSource), to use actual data (fetch the info from a remote server using Retrofit, for example).

### How to Add Local Storage to the App
If you wish to add local storage to the app, it's easy as adding a LocalDataSource, that implements the TaxisDataSource interface (For example, using Room).
Then, change the logic of the Repository.

### Libraries Used
 * Core - RxJava 
 * Parsing - GSON
 * Networking - Glide
 * UI - RecyclerViewItemAnimators