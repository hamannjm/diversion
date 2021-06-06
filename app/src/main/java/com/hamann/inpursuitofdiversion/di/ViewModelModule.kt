package com.hamann.inpursuitofdiversion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hamann.inpursuitofdiversion.ui.GameDetailFragment
import com.hamann.inpursuitofdiversion.viewmodels.GameDetailViewModel
import com.hamann.inpursuitofdiversion.viewmodels.GameSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameSearchViewModel::class)
    abstract fun bindsGameSearchViewModel(gameSearchViewModel: GameSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameDetailViewModel::class)
    abstract fun bindsGameDetail(gameDetailViewModel: GameDetailViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}