//import javax.net.ssl.HttpsURLConnection;
//import java.io.InputStream;
//import java.io.IOException;
//import java.net.URL;

//@Throws(IOException::class)
//private fun downloadUrl(url: URL): String? {
    //var connection: HttpsURLConnection? = null
    //return try {
        //connection = (url.openConnection() as? HttpsURLConnection)
        //connection?.run {
            //readTimeout = 3000
            //connectTimeout = 3000
            //requestMethod = "GET"
            //doInput = true
            //connect()
            ////publishProgress(CONNECT_SUCCESS)
            //if (responseCode != HttpsURLConnection.HTTP_OK) {
                //throw IOException("HTTP error code: $responseCode")
            //}
            ////publishProgress(GET_INPUT_STREAM_SUCCESS, 0)
            ////inputStream?.let { stream ->
                ////readStream(stream, 500)
            ////}
        //}
    //} finally {
        //connection?.inputStream?.close()
        //connection?.disconnect()
    //}
//}

//import khttp.get;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

private fun downloadURL(): String {
  return ""
}
