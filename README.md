# Weather-App_Task

## OpenWeatherMap-Android-Library  
[![](https://jitpack.io/v/KwabenBerko/OpenWeatherMap-Android-Library.svg)](https://jitpack.io/#KwabenBerko/OpenWeatherMap-Android-Library)


**You need an API Key to use the OpenWeatherMap API. Head on over to their [website](http://openweathermap.org/) if you don't already have one.**


## Download

#### Step 1. Add the JitPack repository to your root ```build.gradle``` file.

``` java
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

#### Step 2 : Download via ```Gradle```:

```java
implementation 'com.github.KunwarManish2008:Weather-App_Task:V1.0.1'

```

**Note: Remember to include the INTERNET permission to your manifest file**

## Usage

#### Instantiate Class With Your OpenWeatherMap Api Key

``` kotlin 
val weatherProvider = WeatherLibHelper(BuildConfig.API_KEY)
```

#### Set your Units (Optional, STANDARD by default)

``` kotlin
weatherProvider.setUnits("imperial")
```

##### Unit Options: 

1. ```imperial (Fahrenheit)```

2. ```metric (Celsius)```
3. ```standard```

#### Set your Language (ENGLISH by default)

``` kotlin
helper.setLanguage("en")
```

## Features


### (1) Current Weather
#### Get current weather by City Name:

```kotlin
 weatherProvider.getCurrentWeatherByCity("delhi", object : WeatherResponseCallback {
                override fun onSuccess(currentWeather: WeatherDto) {
                    Log.d("resp", "$currentWeather")
                }

                override fun onFailure(throwable: Throwable) {
                    throwable.message?.let { it1 -> Log.d("resp", it1) }
                }
            })
```

#### Get current weather by Geographic Coordinates:

```kotlin
weatherProvider.getCurrentWeatherByLocation(139.0,
                35.0,
                object : WeatherResponseCallback {
                    override fun onSuccess(currentWeather: WeatherDto) {
                        Log.d("resp", "$currentWeather")
                    }

                    override fun onFailure(throwable: Throwable) {
                        throwable.message?.let { it1 -> Log.d("resp", it1) }
                    }
                })
```


#### Get forecast for 1-16 days one timestamp per day by City Name (parameters: city name, count of days):

```kotlin
 weatherProvider.getWeatherForecastByCity("Delhi", 6, object : ForecastResponseCallback {
                override fun onSuccess(currentWeather: WeatherForecastDto) {
                    Log.d("resp", "$currentWeather")
                }

                override fun onFailure(throwable: Throwable?) {
                    if (throwable != null) {
                        throwable.message?.let { it1 -> Log.d("resp", it1) }
                    }
                }

            })
```
#### Get forecast for 1-16 days one timestamp per day by geographic coordinates. (parameters: longitude, latitude, count of days)
```kotlin
  weatherProvider.getWeatherForecastByLocation(139.0,
                35.0,
                6,
                object : ForecastResponseCallback {
                    override fun onSuccess(currentWeather: WeatherForecastDto) {
                        Log.d("resp", "$currentWeather")
                    }

                    override fun onFailure(throwable: Throwable?) {
                        if (throwable != null) {
                            throwable.message?.let { it1 -> Log.d("resp", it1) }
                        }
                    }

                })
        }
```


