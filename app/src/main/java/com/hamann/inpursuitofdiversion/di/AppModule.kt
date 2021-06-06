package com.hamann.inpursuitofdiversion.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.hamann.inpursuitofdiversion.DiversionApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: DiversionApp) {

    @Singleton
    @Provides
    fun providesApp(): DiversionApp = app

    @Singleton
    @Provides
    fun providesAppContext(): Context = app
}