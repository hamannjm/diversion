package com.hamann.inpursuitofdiversion.di

import com.hamann.inpursuitofdiversion.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMainActivityAndroidInjector(): MainActivity
}