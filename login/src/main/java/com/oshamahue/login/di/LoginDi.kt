package com.oshamahue.login.di

import com.oshamahue.daggerpg.di.ApplicationComponent
import com.oshamahue.login.LoginFragment
import dagger.Component
import javax.inject.Scope

@Scope
@Retention
annotation class FeatureScope

@Component(dependencies = [ApplicationComponent::class])
@FeatureScope
interface LoginComponent {
    @Component.Factory
    interface Factory {
        fun create(applicationComponent: ApplicationComponent): LoginComponent
    }
    fun inject(loginFragment: LoginFragment)
}
