package com.github.jmd.connect
import okhttp3.mockwebserver.*;

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

//import kotlin.test.junit5
//import kotlin.test.Test
//import kotlin.test.assertTrue
//import kotlin.test.assertEquals
//import kotlin.test.assertNotNull

class HttpsUrlConnectionTests {
  val Http = Http()

  @Test
  fun runReturnsResponse() {
    println(Http.run())
    assertNotNull(Http.run())
  }
}
