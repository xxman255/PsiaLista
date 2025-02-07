package com.example.pieski.di

import android.content.Context
import androidx.room.Room
import com.example.pieski.data.AppDatabase
import com.example.pieski.data.DogDao
import com.example.pieski.data.RemoteDogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
         Room.databaseBuilder(appContext, AppDatabase::class.java, "dog_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDogDao(db: AppDatabase): DogDao = db.dogDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
         Retrofit.Builder()
             .baseUrl("https://dog.ceo/api/")
             .addConverterFactory(MoshiConverterFactory.create())
             .build()

    @Provides
    @Singleton
    fun provideRemoteDogService(retrofit: Retrofit): RemoteDogService =
         retrofit.create(RemoteDogService::class.java)
}
