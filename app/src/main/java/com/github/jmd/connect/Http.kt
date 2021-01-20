package com.github.jmd.connect

import java.io.IOException

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody;
import okhttp3.Response

class Http {
  private val client = OkHttpClient()

  fun sendRequest(request: Request): String {
    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Unexpected code $response")
      return response.body!!.string()
    }
  }

  fun GET(url: String): String {
    val request = Request.Builder()
        .url(url)
        .build() 
    return sendRequest(request)
  }

  fun POST(url: String, requestBody: RequestBody): String {
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()
    return sendRequest(request)
  } 
}
