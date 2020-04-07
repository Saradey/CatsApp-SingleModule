package com.evgeny.goncharov.catapp.di.module.app

import android.content.Context
import com.evgeny.goncharov.catapp.BuildConfig
import com.evgeny.goncharov.catapp.consts.*
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
import javax.inject.Named

@Module
class RestModule {

    @Provides
    @AppScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    )


    @Provides
    @AppScope
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(TAG_APPLICATION_CONTEXT) context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(ChuckInterceptor(context))
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @AppScope
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

}