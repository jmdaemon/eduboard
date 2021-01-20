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
  
  fun createPOSTExpectation() {
    mockWebServer.enqueue(MockResponse().setBody("POST request received by server"));
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
    val postBody = """
        |Releases
        |--------
        |
        | * _1.0_ May 6, 2013
        | * _1.1_ June 15, 2013
        | * _1.2_ August 11, 2013
        |""".trimMargin()

    val response = Http.POST(url, postBody)
    assertNotNull(response)
    assertEquals("POST request received by server", response)
  }
}
