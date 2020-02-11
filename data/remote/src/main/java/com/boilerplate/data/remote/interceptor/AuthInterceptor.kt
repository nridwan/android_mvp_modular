package com.boilerplate.data.remote.interceptor

import com.boilerplate.data.local.UserRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(val repo: UserRepository): Interceptor {

    val AUTHORIZATION = "Authorization"

    override fun intercept(chain: Interceptor.Chain): Response {
        val oriResponse = chain.request()

        if(repo.user != null){
            var response = chain.proceed(addHeaderAuth(oriResponse))
            if (response.code() != 401) {
                return response
            } else {
                newRefreshToken()
                response = chain.proceed(addHeaderAuth(oriResponse))
                doWhenForbidden(response)
            }
            return response
        }
        else {
            return chain.proceed(oriResponse)
        }
    }

    @Throws(IOException::class)
    fun newRefreshToken() {

    }

    private fun doWhenForbidden(response: Response) {
    }

    private fun addHeaderAuth(oriRequest: Request): Request {
        return oriRequest.newBuilder()
            .addHeader("TEST","Test")
            .build()
    }
}