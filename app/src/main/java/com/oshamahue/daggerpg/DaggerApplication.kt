package com.oshamahue.daggerpg

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.oshamahue.daggerpg.di.ApplicationComponent
import com.oshamahue.daggerpg.di.DaggerApplicationComponent
import com.oshamahue.repository.di.DaggerRepositoryComponent

class DaggerApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        buildApiComponent()
    }

    private fun buildApiComponent() {
        val repositoryComponent = DaggerRepositoryComponent.create()
        appComponent = DaggerApplicationComponent.factory().create(repositoryComponent)
    }
}

val Activity.appComponent get() = (applicationContext as DaggerApplication).appComponent
val Fragment.appComponent get() = requireActivity().appComponent