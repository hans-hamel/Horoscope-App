package com.horoscopeapp.data.core.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {
    companion object {
        const val HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .header(HEADER, tokenManager.getToken())
            .build()

        return chain.proceed(request)
    }
}

/* Example of how to get a token*/
class TokenManager @Inject constructor() {
    companion object {
        const val HEADER_VALUE = "token"
    }


    /* Get the value from database for example */
    fun getToken(): String = HEADER_VALUE
}