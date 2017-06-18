# WeatherAppTest
Displays five days / 3 hour weather forecast from OpenWeatherMap api.

## Project Setup
Clone project into your workspace and open it via Android Studio. You can also directly clone it into Android Studio via 
"Project from Version Control" wizard. Make sure you have the necessary sdk/lib dependencies installed - Android Studio 
will report you if there has been anything missing, Please follow the instructions and install them. 

## Build & Installation
Once the project sets up in Android Studio, you can launch a simulator via AVD wizard or directly run it on the device
connected to your computer. To build the project from command line run the following command inside your project root

#### ./gradlew

Once it finishes, get the builds from <project root>/app/build/generated/

## Run Unit Test
You can run the unit test via Android Studio or via running the following command inside your project root

#### ./gradlew test

Once it finishes, see the test results at <project root>/app/build/test-results

