package com.github.jmd.connect
import okhttp3.mockwebserver.*;

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HttpsUrlConnectionTests {
  val Http = Http()

  @Test
  fun runReturnsResponse() {
    assertNotNull(Http.run())
  }
}
