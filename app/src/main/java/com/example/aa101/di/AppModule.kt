package com.example.aa101.di

import android.content.Context
import com.example.aa101.AAApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): AAApplication {
        return app as AAApplication
    }

    @Singleton
    @Provides
    fun getRandomString(): String {
        return "NOIDFNGKRJHCGKWFNVSDFKHGIR"
    }
}