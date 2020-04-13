package com.oshamahue.repository.di

import dagger.Component
import dagger.Module

@Module
class FakeBaseUrlModule {
    fun provideFakeUrl() = "https://127.0.0.1:8080"
}

@Component(modules = [RepositoryModule::class, FakeBaseUrlModule::class])
interface FakeRepositoryComponent : RepositoryComponent