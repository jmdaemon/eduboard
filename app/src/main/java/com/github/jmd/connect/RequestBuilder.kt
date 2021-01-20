package com.github.jmd.connect

import java.io.FileNotFoundException;
import java.io.File
import kotlin.collections.map
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
  return file.asRequestBody(mediaType)
}

fun createFormBody(formParams: Map<String, String> ): RequestBody {
  val formBuilder = FormBody.Builder()
  formParams.forEach() { k, v -> formBuilder.add(k, v) }
  val formBody: RequestBody = formBuilder.build()
  return formBody
}
