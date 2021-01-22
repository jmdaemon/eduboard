package com.github.jmd.connect

import java.io.FileNotFoundException
import java.io.File
import kotlin.collections.map
import kotlin.collections.MutableMap
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Request.Builder
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
  formParams.forEach() { (k, v) -> formBuilder.add(k, v) }
  val formBody: RequestBody = formBuilder.build()
  return formBody
}

fun createHeader(url: String, header: Map<String, String>): Request {
  val request = Request.Builder().url(url)
  header.forEach() { (k,v) -> request.addHeader(k, v) }
  return request.build()
}

fun createHeader(params: Array<String>): Map<String, String> {
  var result: MutableMap<String, String> = mutableMapOf<String, String>()
  result.put("Accept"           , params.get(0))
  result.put("Cookie"           , params.get(1))
  result.put("Host"             , params.get(2))
  result.put("Referer"          , params.get(3))
  result.put("User-Agent"       , params.get(4))
  return result
}

