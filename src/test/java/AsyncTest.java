import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class AsyncTest {
	public String doGet(String url,String url1) throws InterruptedException, ExecutionException, IOException {
		String sTotalString="";
		HttpContext localContext = new BasicHttpContext();
		AuthSchemeBase authScheme = new BasicScheme();
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials("configer@hotmail.com", "Configer1"));
		CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
				.setDefaultCredentialsProvider(credentialsProvider).build();
		httpAsyncClient.start();

		Future<HttpResponse> future = httpAsyncClient.execute(new HttpGet(url),
				localContext, null);
		HttpResponse response = future.get();
		if (response != null) {
			StatusLine statusLine = response.getStatusLine();
			if (statusLine != null) {
				System.out.println( statusLine.toString());
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					byte[] bytes = EntityUtils
							.toByteArray(response.getEntity());
//					System.out.println(new String(bytes, "UTF-8"));
					
					System.out.println(">>>>>>>>>>>>>Add good>>>>>>>>>>>>");
//					localContext.
//					testGet(url1);
					
				}
			}
		}
		httpAsyncClient.close();
//		DefaultHttpClient httpclient =new DefaultHttpClient();
		HttpClient client = new DefaultHttpClient();
//		client.
		ResponseHandler<String> rh = new BasicResponseHandler();
		for(int i =0;i<10;i++){
			try {
				client.execute(new HttpGet("http://order.xiaomi.com/cart/add/2140200002"), rh,localContext);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		client.execute(new HttpGet(url1), rh,localContext);
		client.execute(new HttpGet(url1), new BasicResponseHandler(),localContext);
		sTotalString =client.execute(new HttpGet("http://order.xiaomi.com/cart/add/2140200002"), rh,localContext);
//		System.out.println(sTotalString);
//		httpclient.close();
		return sTotalString;
	}
	public String testGet(String urlStr) throws IOException {

		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		String sCurrentLine;
		String sTotalString;
		sCurrentLine = "";
		sTotalString = "";
		InputStream l_urlStream;
		l_urlStream = connection.getInputStream();
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sTotalString += sCurrentLine + "\r\n";

		}
		return sTotalString;
	}

}
