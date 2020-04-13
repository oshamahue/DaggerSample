package com.oshamahue.repository.di

import com.oshamahue.api.LoginApi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Scope


@Module
class BaseUrlModule {
    @Provides
    fun provideBaseUrl() = "https://myprodurl.com"
}

@Module
class RepositoryModule {

    @Provides
    fun provideLoginApi(url: String): LoginApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .build()
        return retrofit.create(LoginApi::class.java)
    }
}

@Component(modules = [RepositoryModule::class, BaseUrlModule::class])
@RepositoryScope
interface RepositoryComponent {
    fun loginApi(): LoginApi

    @Component.Factory
    interface Factory {
        fun create(
            repositoryModule: RepositoryModule,
            baseUrlModule: BaseUrlModule
        ): RepositoryComponent
    }
}

@Scope
@Retention
annotation class RepositoryScope

