package com.example.officeapp.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BaseUrlInterceptor @Inject constructor() : Interceptor {

    @Volatile
    private var baseUrl: String = "https://default.com" // Убираем аргумент конструктора

    fun updateBaseUrl(newUrl: String) {
        baseUrl = newUrl
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = baseUrl + originalRequest.url.encodedPath // Берём обновлённый baseUrl
        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}
