package com.hamann.inpursuitofdiversion.di

import com.hamann.inpursuitofdiversion.ui.GameDetailFragment
import com.hamann.inpursuitofdiversion.ui.GameSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun providesGameSearchFragment(): GameSearchFragment

    @ContributesAndroidInjector
    abstract fun providesGameDetailFragment(): GameDetailFragment
}