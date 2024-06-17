package com.android.deepdweller.maprouteapp_nojetpack.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.android.deepdweller.maprouteapp_nojetpack.data.source.local.AppPrefsDatastore
import com.android.deepdweller.maprouteapp_nojetpack.presentation.map.MapViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "internal_audit_datastore"
    )

    @Provides
    @Singleton
    fun providePrefsDataStore(@ApplicationContext context: Context) = AppPrefsDatastore(context.dataStore)


    @Provides
    @Singleton
    fun provideMapViewModel(): MapViewModel {
        return MapViewModel()
    }
}