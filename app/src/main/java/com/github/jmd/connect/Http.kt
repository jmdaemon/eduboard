package com.github.jmd.connect

import java.io.IOException
import java.io.FileNotFoundException;
import java.io.File

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody;
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

  fun createPostBody(postBody: String): RequestBody { 
    return postBody.toRequestBody(MEDIA_TYPE_PLAIN)
  }

  fun createFileBody(fileName: String): RequestBody {
    val file = File(fileName)
    if (!file.exists() || file.isDirectory()) {
      val errorMsg = "$fileName not found"
      println(errorMsg)
      throw FileNotFoundException("$fileName not found")
      //return errorMsg
    }
    return file.asRequestBody(MEDIA_TYPE_PLAIN)
  }

  fun createFormBody(): RequestBody {
    val formBody = FormBody.Builder()
        .add("search", "Jurassic Park")
        .build()
    return formBody
  }

  fun sendPostRequest(url: String, requestBody: RequestBody): String {
    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()
    return sendRequest(request)
  }

  fun POST(url: String, postBody: String): String {
    return sendPostRequest(url, createPostBody(postBody))
  }

  fun POSTForm(url: String, fileName: String): String {
    return sendPostRequest(url, createFileBody(fileName))
  }

  fun POSTParams(url: String): String {
    return sendPostRequest(url, createFormBody())
  }

  companion object {
    val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    val MEDIA_TYPE_PLAIN = "text/plain; charset=utf-8".toMediaType()
  }
}
