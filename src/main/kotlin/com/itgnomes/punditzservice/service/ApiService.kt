package com.itgnomes.punditzservice.service

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class ApiService {
    companion object {
        private val CLIENT = OkHttpClient()
        val BASEURL = "https://api.football-data.org/v2/competitions/"
        private val TOKENKEY = "X-Auth-Token"
        private val TOKENVAL = "10581ec8e3a54e2d876899ed5f33faf3"
        val TEAMS = "/teams"
        val MATCHES = "/matches"

        @JvmStatic
        fun invoke(url: String): String {
            val request = Request.Builder()
                .url(url).addHeader(TOKENKEY, TOKENVAL)
                .build()

            CLIENT.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                return response.body?.string() ?: ""
            }
        }
    }
}