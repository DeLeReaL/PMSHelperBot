package data.remote.repository

import data.remote.api.ReversedGeocodingApi
import data.remote.api.WeatherApi
import data.remote.models.CurrentWeather
import data.remote.models.ReversedCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val reversedGeocodingApi: ReversedGeocodingApi
) {

    suspend fun getCurrentWeather(apiKey: String, coutryName:String, airQualityData:String):CurrentWeather{
        return withContext(Dispatchers.IO){
            weatherApi.getCurrentWeather(apiKey,coutryName,airQualityData)
        }.await()
    }

    suspend fun getReverseGeocodingCountryName(latitude:String, longtitude:String, format : String):ReversedCountry{
        return withContext(Dispatchers.IO){
            reversedGeocodingApi.getCountryNameByCoordinates(latitude,longtitude, format)
        }.await()
    }

}