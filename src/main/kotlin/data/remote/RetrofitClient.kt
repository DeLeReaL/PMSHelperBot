package data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import data.remote.api.ReversedGeocodingApi
import data.remote.api.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val WEATHER_BASE_URL = "http://api.weatherapi.com/v1/"
private const val REVERSE_GEOCODER_BASE_URL = "http://nominatim.openstreetmap.org"
private const val API_KEY = "6b9300887c3feb473c8dedd52564cb2c"


enum class RetrofipType(val baseUrl: String){
    WEATHER(WEATHER_BASE_URL),
    REVERSE_GEOCODER(REVERSE_GEOCODER_BASE_URL);
}

class RetrofitClient {

    fun getClient():OkHttpClient {
        val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
        return okHttpClient.build()
    }

    fun getRetrofit(retrofipType: RetrofipType): Retrofit{
        return Retrofit.Builder()
            .baseUrl(retrofipType.baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getWeatherApi(retrofit: Retrofit):WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }

    fun getReversedGeocodingApi(retrofit: Retrofit):ReversedGeocodingApi{
        return retrofit.create(ReversedGeocodingApi::class.java)
    }
}