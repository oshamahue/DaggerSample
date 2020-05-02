package com.oshamahue.repository.di

import com.oshamahue.api.LoginApi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Scope


@Scope
@Retention
annotation class RepositoryScope

@Component(modules = [RepositoryModule::class])
@RepositoryScope
interface RepositoryComponent {
    val loginApi: LoginApi
}

@Module
class RepositoryModule {

    @Provides
    fun provideLoginApi(url: String): LoginApi {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().client(client).baseUrl(url).build()
        return retrofit.create()

    }
    @Provides
    fun provideBaseUrl() = "https://myprodurl.com"
}







