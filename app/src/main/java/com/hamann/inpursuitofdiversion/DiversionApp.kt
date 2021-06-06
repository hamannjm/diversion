package com.hamann.inpursuitofdiversion

import android.app.Application
import com.hamann.inpursuitofdiversion.di.AppModule
import com.hamann.inpursuitofdiversion.di.ApplicationComponent
import com.hamann.inpursuitofdiversion.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DiversionApp: Application(), HasAndroidInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}