package com.oshamahue.daggerpg.di

import com.oshamahue.api.LoginApi
import com.oshamahue.repository.LoginRepository
import com.oshamahue.repository.di.RepositoryComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention
annotation class ApplicationScope

@Component(modules = [ApplicationModule::class], dependencies = [RepositoryComponent::class])
@ApplicationScope
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(repositoryComponent: RepositoryComponent): ApplicationComponent
    }

    val loginRepository: LoginRepository
}

@Module
class ApplicationModule {
    @Provides
    fun provideLoginRepository(loginApi: LoginApi) = LoginRepository(loginApi)
}