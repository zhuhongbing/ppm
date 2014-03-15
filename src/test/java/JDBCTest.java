import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JDBCTest {
	Connection con = null;
	Statement stm = null;
	ResultSet r = null;

	public static void main(String[] args) throws Exception {

		URL u = new URL("https://ufo.deere.com/servlet/UFO?11990=1");

		JDBCTest jt = new JDBCTest();
		String code = "GB2312";

		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("Dealer", "3B00322");
		pMap.put("Pin", "1YC1213445");
		pMap.put("Location", "LL");
		pMap.put("Time", new Date().toString());
		System.out.println(jt.doPost(u, pMap, code));
		// jt.test();
	}

	void test() throws Exception {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String conStr = "jdbc:sqlserver://GFBEISQLDEV1:1434;instanceName=INST1;DatabaseName=APPCNTEST";
		Class.forName(driverName);
		con = DriverManager.getConnection(conStr, "appcnconn", "wtycn2013");
		stm = con.createStatement();
		String query = "select * from PRODUCT";
		r = stm.executeQuery(query);
		while (r.next()) {
			System.out.println(r.getString(1) + "|" + r.getString(2));
		}
		System.out.println(r.getFetchSize());
	}

	public void processURL(URL url) throws IOException {
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("Dealer", "3B00322");
		pMap.put("Pin", "1YC1213445");
		pMap.put("Location", "LL");
		pMap.put("Time", new Date().toString());
		System.out.println(s2s(is));

	}

	public String s2s(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	public String doPost(URL url, Map<String, String> para, String code) {
		HttpURLConnection conn = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			Set<Entry<String, String>> set = para.entrySet();
			for (Entry<String, String> ent : set) {
				params.append(ent.getKey());
				params.append("=");
				params.append(URLEncoder.encode(ent.getValue(), code));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}
			// URL url = u
			HttpURLConnection url_con = (HttpURLConnection) url
					.openConnection();
			url_con.setRequestMethod("POST");
			// System.setProperty("sun.net.client.defaultConnectTimeout",
			// String
			// .valueOf(HttpRequestProxy.connectTimeOut));//
			// （单位：毫秒）jdk1.4换成这个,连接超时
			// System.setProperty("sun.net.client.defaultReadTimeout",
			// String
			// .valueOf(HttpRequestProxy.readTimeOut)); //
			// （单位：毫秒）jdk1.4换成这个,读操作超时
			url_con.setConnectTimeout(5000);// （单位：毫秒）jdk
			// 1.5换成这个,连接超时
			url_con.setReadTimeout(5000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					code));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return responseContent;
	}

}
