package com.github.jmd.connect

import java.io.IOException
import java.io.File

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody

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

  fun POST(url: String, postBody: String): String {
    val request = Request.Builder()
        .url(url)
        .post(postBody.toRequestBody(MEDIA_TYPE_PLAIN))
        .build()
    return sendRequest(request)
  }

  fun POSTForm(url: String, fileName: String): String {
    val file = File(fileName)
    if (!file.exists() || file.isDirectory()) {
      val errorMsg = "$fileName not found"
      println(errorMsg)
      return errorMsg
    }

    val request = Request.Builder()
        .url(url)
        .post(file.asRequestBody(MEDIA_TYPE_PLAIN))
        .build()
    return sendRequest(request)
  }

  fun POSTParams(url: String): String {
    val formBody = FormBody.Builder()
        .add("search", "Jurassic Park")
        .build()
    val request = Request.Builder()
        .url(url)
        .post(formBody)
        .build()

    return sendRequest(request)
  }

  companion object {
    val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    val MEDIA_TYPE_PLAIN = "text/plain; charset=utf-8".toMediaType()
  }
}
