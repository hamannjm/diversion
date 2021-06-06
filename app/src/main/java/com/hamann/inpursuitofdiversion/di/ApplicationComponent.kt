package com.hamann.inpursuitofdiversion.di

import com.hamann.inpursuitofdiversion.DiversionApp
import com.hamann.inpursuitofdiversion.ui.GameSearchFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ViewModelModule::class,
    PersistenceModule::class,
    FragmentModule::class,
    WebModule::class]
)
interface ApplicationComponent {
    fun inject(app: DiversionApp)
    fun inject(gameSearchFragment: GameSearchFragment)
}