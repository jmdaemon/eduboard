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
    //assertNotNull(Http.run("https://publicobject.com/helloworld.txt"))
    createGETExpectation()
    val response = Http.GET(url)
    assertNotNull(response)
    assertEquals("GET request received by server", response)
  }

  @Test
  fun POSTReturnsResponse() {
    createPOSTExpectation()
    val response = Http.POST(url, "Sending Post Request...".trimMargin())
    assertNotNull(response)
    assertEquals("POST request received by server", response)
  }

  @Test
  fun POSTForm_WithFakeFileReturnsFileNotFound() {
    createPOSTFormExpectation()
    //val response = Http.POSTForm(url, "ImaginaryFile.txt")
    assertFailsWith<FileNotFoundException> { Http.POSTForm(url, "ImaginaryFile.txt") }
    //assertNotNull(response)
    //assertEquals("ImaginaryFile.txt not found", response)
  }

  @Test
  fun POSTParamsReturnsResponse() {
    //val response = Http.POSTParams("https://en.wikipedia.org/w/index.php")
    createPostParamsExpectation()
    val response = Http.POSTParams(url)
    assertNotNull(response)
    assertEquals("POST request with params received by server", response)
    //println(response)
  }
}
