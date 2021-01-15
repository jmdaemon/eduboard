package com.github.jmd.connect
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

private val client = OkHttpClient()

fun run() {
  val request = Request.Builder()
      .url("https://publicobject.com/helloworld.txt")
      .build()

  client.newCall(request).execute().use { response ->
    if (!response.isSuccessful) throw IOException("Unexpected code $response")

    for ((name, value) in response.headers) {
      println("$name: $value")
    }

    println(response.body!!.string())
  }
} 
