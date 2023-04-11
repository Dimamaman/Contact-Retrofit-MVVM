package uz.gita.contactappbootcamp5.data.source.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.contactappbootcamp5.app.App

object MyClient {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.instance).build())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://ab74-37-110-214-235.ngrok-free.app")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

}




