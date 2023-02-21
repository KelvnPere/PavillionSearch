# PavilionSearch

Welcome 👋 

This is an Android Application that leverages the Github Search User Api for finding various users based on the criteria given. In other words, you input a
user on Github and click on search and it then brings the result for you to select from.

The app consists of 3 componets: 

1) A search component
2) A Result Component and lastly
3) A Detail Component. 


<p float="left">
  <img src=https://user-images.githubusercontent.com/36895007/220397030-8fef1a0d-ec6a-4b9b-a0b3-6a160ad618b1.png width = 35%
 >
 <img src=https://user-images.githubusercontent.com/36895007/220397071-4c27ae37-3527-4a8d-a46f-37a74eecdbce.png width= 35%>
</p>

<p float="left">
<img src=https://user-images.githubusercontent.com/36895007/220397122-0cecd142-0379-4292-9ba2-e4c3d3fc7831.png width= 30%>
<img src=https://user-images.githubusercontent.com/36895007/220397139-37862b30-d6fd-4765-ad75-870a672ae7c1.png width= 30%> 
</p>

Below is more information about the flow, architecture and libraries used for the project.


* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Retrofit](https://square.github.io/retrofit/)
* ReyclerView
* ViewBinding
* Navigation Components
* Dagger-Hilt
* Glide

# Flow of the Application

The user clicks on the app from the launcher which then allows the Android Operating System to execute the application. The 
user waits for a short time as a progress bar is shown informing the users that some form of data is being loaded and will 
be displayed soon.  After data has been loaded from the Github Api it then displays some information:

# Requirements

* JavaVersion.VERSION_1_8
* Latest Android Studio 4.0 above
* Latest Android SDK and Build Tools


# Architecture

Rather than implement everything in a Single Activity or Fragment decided to go with the MVVM Repository Pattern. There were many 
options to follow, I decided on building the app using the MVVM Design Pattern which helps in separation of concerns.

M = Model
V = View
VM = ViewModel

