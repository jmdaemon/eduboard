package test.com.github.jmd.connect
import com.github.jmd.connect.*

import java.io.FileNotFoundException
import kotlin.collections.map

import okhttp3.mockwebserver.*
import okhttp3.Request
import okhttp3.Request.Builder

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test

import kotlin.test.assertFailsWith

class HttpsUrlConnectionTests {
  private var url = "http://127.0.0.1:8080"
  private val mockWebServer = MockWebServer()
  val Http = Http()
  
  @BeforeEach
  fun setUp() {  
    mockWebServer.start(8080)
  }

  @AfterEach
  fun tearDown() {  
    mockWebServer.shutdown()  
  }

  fun createGETExpectation() {
    mockWebServer.enqueue(MockResponse().setBody("GET request received by server"));
  }
  
  fun createPOSTExpectation() {
    mockWebServer.enqueue(MockResponse().setBody("POST request received by server"));
  }

  fun createPOSTFormExpectation() {
    mockWebServer.enqueue(MockResponse().setBody("POST request with Form request body received by server"));
  }

  fun createPostParamsExpectation() {
    mockWebServer.enqueue(MockResponse().setBody("POST request with params received by server"));
  }

  fun createPostHeaderExpectation() {
    mockWebServer.enqueue(MockResponse().setBody("POST request with header received by server"));
  }

  @Test
  fun GETReturnsResponse() {
    createGETExpectation()
    val response = Http.GET(url)
    assertEquals("GET request received by server", response)
  }

  @Test
  fun POSTReturnsResponse() {
    createPOSTExpectation()
    val response = Http.POST(url, createPostBody("Sending Post Request...".trimMargin(), MEDIA_TYPE_PLAIN))
    assertEquals("POST request received by server", response)
  }

  @Test
  fun POSTForm_WithFakeFileReturnsFileNotFound() {
    createPOSTFormExpectation()
    assertFailsWith<FileNotFoundException> { Http.POST(url, createFileBody("ImaginaryFile.txt", MEDIA_TYPE_PLAIN)) }
  }

  @Test
  fun POSTParamsReturnsResponse() {
    createPostParamsExpectation()
    val response = Http.POST(url, createFormBody(mapOf("search" to "Jurassic Park")))
    assertEquals("POST request with params received by server", response)
  }

  @Test
  fun POSTHeadersReturnsResponse() {
    createPostHeaderExpectation()
    val request = createHeader(url, mapOf("Content-Type" to "text/plain"))
    val response = Http.POST(request)
    assertEquals("text/plain", request.header("Content-Type"))
    assertEquals("POST request with header received by server", response)
  }
}
