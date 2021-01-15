package test.com.github.jmd.connect
import com.github.jmd.connect.*

import okhttp3.mockwebserver.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test

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

  @Test
  fun runReturnsResponse() {
    //assertNotNull(Http.run("https://publicobject.com/helloworld.txt"))
    createGETExpectation()
    val response = Http.run(url)
    assertNotNull(response)
    assertEquals("GET request received by server", response)
  }
}
