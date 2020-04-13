package com.oshamahue.repository

import com.oshamahue.api.LoginApi
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApi: LoginApi) {
    suspend fun login(username: String, password: String) = loginApi.login(username, password)
}