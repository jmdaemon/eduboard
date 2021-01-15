package com.github.jmd.connect
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class Http {
  private val client = OkHttpClient()

  fun run(url: String): String {
    val request = Request.Builder()
        .url(url)
        .build()
    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Unexpected code $response")
      return response.body!!.string()
    }
  }
}
