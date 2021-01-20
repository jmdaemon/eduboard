package test.com.github.jmd.connect
import com.github.jmd.connect.*

import java.io.FileNotFoundException

import okhttp3.mockwebserver.*

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

  @Test
  fun GETReturnsResponse() {
    createGETExpectation()
    val response = Http.GET(url)
    assertEquals("GET request received by server", response)
  }

  @Test
  fun POSTReturnsResponse() {
    createPOSTExpectation()
    val response = Http.POST(url, Http.createPostBody("Sending Post Request...".trimMargin()))
    assertEquals("POST request received by server", response)
  }

  @Test
  fun POSTForm_WithFakeFileReturnsFileNotFound() {
    createPOSTFormExpectation()
    assertFailsWith<FileNotFoundException> { Http.POST(url, Http.createFileBody("ImaginaryFile.txt")) }
  }

  @Test
  fun POSTParamsReturnsResponse() {
    createPostParamsExpectation()
    val response = Http.POST(url, Http.createFormBody())
    assertEquals("POST request with params received by server", response)
  }
}
