package com.evgeny.goncharov.catapp.di.module.app

import android.content.Context
import com.evgeny.goncharov.catapp.BuildConfig
import com.evgeny.goncharov.catapp.consts.BASE_URL
import com.evgeny.goncharov.catapp.consts.CONNECTION_TIMEOUT
import com.evgeny.goncharov.catapp.consts.READ_TIMEOUT
import com.evgeny.goncharov.catapp.consts.WRITE_TIMEOUT
import com.evgeny.goncharov.catapp.di.qualified.AppContext
import com.evgeny.goncharov.catapp.di.scope.AppScope
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object RestModule {

    @Provides
    @AppScope
    @JvmStatic
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    )

    @Provides
    @AppScope
    @JvmStatic
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @AppContext context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(ChuckInterceptor(context))
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @AppScope
    @JvmStatic
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}