import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.protocol.HttpContext;

public class Asyn {

	public void doGet(String url,String url1) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().build();
				//new DefaultHttpAsyncClient();
		 httpclient.start();
		try {
			Future<Boolean> future = httpclient.execute(
					HttpAsyncMethods.createGet(url),
					new MyResponseConsumer(), null);
//			httpclient
			Boolean result = future.get();
			if (result != null && result.booleanValue()) {
				System.out.println("Request successfully executed");
			} else {
				System.out.println("Request failed");
			}
			System.out.println("Shutting down");
		} finally {
			 httpclient.close();
		}
		System.out.println("Done");
		String rStr = testGet(url1);
		System.out.println(rStr);
	}
	public String testGet(String urlStr) throws IOException {

		/**
		 * ����Ҫ��URL�µ�URLConnection�Ի��� URLConnection���Ժ����׵Ĵ�URL�õ������磺 // Using
		 * java.net.URL and //java.net.URLConnection
		 */
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		/**
		 * Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ��
		 * ͨ���URLConnection��Ϊ���������԰���������Webҳ���͡��������������
		 */
//		connection.setDoOutput(true);
		/**
		 * ���Ϊ�˵õ�OutputStream����������Լ����Writer���ҷ���POST��Ϣ�У����磺 ...
		 */
//		OutputStreamWriter out = new OutputStreamWriter(connection
//				.getOutputStream(), "8859_1");
//		out.write("user=configer%40hotmail.com&_json=true&pwd=Configer1&sid=passport&_sign=KKkRvCpZoDC%2BgLdeyOsdMhwV0Xg%3D&callback=https%3A%2F%2Faccount.xiaomi.com&qs=%253Fsid%253Dpassport"); // post�Ĺؼ����ڣ�
		// remember to clean up
//		out.flush();
//		out.close();
		/**
		 * ����Ϳ��Է���һ���������������POST�� POST /jobsearch/jobsearch.cgi HTTP 1.0 ACCEPT:
		 * text/plain Content-type: application/x-www-form-urlencoded
		 * Content-length: 99 username=bob password=someword
		 */
		// һ�����ͳɹ��������·����Ϳ��Եõ��������Ļ�Ӧ��
		String sCurrentLine;
		String sTotalString;
		sCurrentLine = "";
		sTotalString = "";
		InputStream l_urlStream;
		l_urlStream = connection.getInputStream();
		// ��˵�е�����װ����
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sTotalString += sCurrentLine + "\r\n";

		}
		return sTotalString;
	}


	static class MyResponseConsumer extends AsyncCharConsumer<Boolean> {

		private int times = 0;

		private String getTimes() {
			return "\n\n### 第" + ++times + "步\n###";
		}

		@Override
		protected void onResponseReceived(final HttpResponse response) {
			System.out.println(getTimes() + "onResponseReceived");
		}

		@Override
		protected void onCharReceived(final CharBuffer buf,
				final IOControl ioctrl) throws IOException {
			System.out.println(getTimes() + "onCharReceived");
			while (buf.hasRemaining()) {
				System.out.print(buf.get());
			}
		}

		@Override
		protected void releaseResources() {
			System.out.println(getTimes() + "releaseResources");
		}

		@Override
		protected Boolean buildResult(final HttpContext context) {
			System.out.println(getTimes() + "buildResult");
			return Boolean.TRUE;
		}
	}
}
