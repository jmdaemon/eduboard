package com.github.jmd.connect

import java.io.FileNotFoundException;
import java.io.File
import okhttp3.RequestBody
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody

val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
val MEDIA_TYPE_PLAIN = "text/plain; charset=utf-8".toMediaType()

fun createPostBody(postBody: String, mediaType: MediaType): RequestBody { 
  return postBody.toRequestBody(mediaType)
}

fun createFileBody(fileName: String, mediaType: MediaType): RequestBody {
  val file = File(fileName)
  if (!file.exists() || file.isDirectory()) {
    val errorMsg = "$fileName not found"
    println(errorMsg)
    throw FileNotFoundException("$fileName not found")
  }
  //return file.asRequestBody(MEDIA_TYPE_PLAIN)
  return file.asRequestBody(mediaType)
}

fun createFormBody(): RequestBody {
  val formBody = FormBody.Builder()
      .add("search", "Jurassic Park")
      .build()
  return formBody
}
