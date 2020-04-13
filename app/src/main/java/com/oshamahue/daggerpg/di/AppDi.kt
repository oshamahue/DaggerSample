package com.oshamahue.daggermultimodule.di

import com.oshamahue.api.LoginApi
import com.oshamahue.repository.LoginRepository
import com.oshamahue.repository.di.RepositoryComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class ApplicationModule {

    @Provides
    fun provideLoginRepository(loginApi: LoginApi) = LoginRepository(loginApi)
}

@Component(modules = [ApplicationModule::class], dependencies = [RepositoryComponent::class])
@ApplicationScope
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            repositoryComponent: RepositoryComponent,
            module: ApplicationModule
        ): ApplicationComponent
    }

    fun loginRepository(): LoginRepository
}

@Scope
@Retention
annotation class ApplicationScope
