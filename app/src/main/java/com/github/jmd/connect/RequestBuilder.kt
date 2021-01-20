package com.github.jmd.connect
import java.io.IOException
import java.io.FileNotFoundException;
import java.io.File
import okhttp3.RequestBody;
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody

fun createPostBody(postBody: String): RequestBody { 
  return postBody.toRequestBody(MEDIA_TYPE_PLAIN)
}

fun createFileBody(fileName: String): RequestBody {
  val file = File(fileName)
  if (!file.exists() || file.isDirectory()) {
    val errorMsg = "$fileName not found"
    println(errorMsg)
    throw FileNotFoundException("$fileName not found")
  }
  return file.asRequestBody(MEDIA_TYPE_PLAIN)
}

fun createFormBody(): RequestBody {
  val formBody = FormBody.Builder()
      .add("search", "Jurassic Park")
      .build()
  return formBody
}
//object {
val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
val MEDIA_TYPE_PLAIN = "text/plain; charset=utf-8".toMediaType()
//}

//companion object {
  //val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
  //val MEDIA_TYPE_PLAIN = "text/plain; charset=utf-8".toMediaType()
//}
