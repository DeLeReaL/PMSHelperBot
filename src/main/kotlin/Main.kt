import bot.WeatherBot
import data.remote.RetrofipType
import data.remote.RetrofitClient
import data.remote.repository.WeatherRepository

fun main() {
    val weatherRetrofit = RetrofitClient().getRetrofit(RetrofipType.WEATHER)
    val reverseRetrofit = RetrofitClient().getRetrofit(RetrofipType.REVERSE_GEOCODER)
    val reverseApi = RetrofitClient().getWeatherApi(weatherRetrofit)
    val weatherApi = RetrofitClient().getReversedGeocodingApi(reverseRetrofit)
    val weatherRepository = WeatherRepository(reverseApi, weatherApi)
    val weatherBot = WeatherBot(weatherRepository).createBot()
    weatherBot.startPolling()
}