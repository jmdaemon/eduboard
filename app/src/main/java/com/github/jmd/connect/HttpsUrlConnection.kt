package com.github.jmd.connect
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class Http {
  private val client = OkHttpClient()

  fun GET(url: String): String {
    val request = Request.Builder()
        .url(url)
        .build()
    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Unexpected code $response")
      return response.body!!.string()
    }
  }

  fun POST(url: String) {
    val postBody = """
        |Releases
        |--------
        |
        | * _1.0_ May 6, 2013
        | * _1.1_ June 15, 2013
        | * _1.2_ August 11, 2013
        |""".trimMargin()

    val request = Request.Builder()
        //.url("https://api.github.com/markdown/raw")
        .url(url)
        .post(postBody.toRequestBody(MEDIA_TYPE_MARKDOWN))
        .build()

    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Unexpected code $response")

      println(response.body!!.string())
    }
  }

  companion object {
    val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
  }
}
