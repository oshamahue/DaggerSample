package com.oshamahue.login.di

import com.oshamahue.daggermultimodule.di.ApplicationComponent
import com.oshamahue.login.LoginFragment
import com.oshamahue.login.viewmodel.LoginViewModel
import dagger.Component
import javax.inject.Scope


@Component(dependencies = [ApplicationComponent::class])
@LoginScope
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
}

@Scope
@Retention
annotation class LoginScope