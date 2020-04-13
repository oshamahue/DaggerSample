package com.oshamahue.daggermultimodule

import android.app.Application
import com.oshamahue.daggermultimodule.di.ApplicationComponent
import com.oshamahue.daggermultimodule.di.ApplicationModule
import com.oshamahue.daggermultimodule.di.DaggerApplicationComponent
import com.oshamahue.repository.di.BaseUrlModule
import com.oshamahue.repository.di.DaggerRepositoryComponent
import com.oshamahue.repository.di.RepositoryModule

class DaggerApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        application = this
        buildApiComponent()
    }

    fun buildApiComponent() {
        appComponent = DaggerApplicationComponent.factory()
            .create(
                DaggerRepositoryComponent.factory().create(
                    RepositoryModule(),
                    BaseUrlModule()
                )
                , ApplicationModule()
            )
    }

    companion object {
        lateinit var application: DaggerApplication
    }
}
