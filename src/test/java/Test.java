

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t= new Test();
//		Asyn A = new Asyn();
		AsyncTest at= new AsyncTest();
		String rStr="";
		try {
			rStr=t.testPost("https://account.xiaomi.com/pass/serviceLoginAuth2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result="";
		try {
			System.out.println(rStr);
//			JSONArray json= new JSONArray();
			result=rStr.replace("&&&START&&&", "");
			System.out.println(result);
			JSONObject jo = new JSONObject(result);
			result = jo.getString("location");
			System.out.println(result);
//			rSt.testGet(result);
			rStr =at.doGet(result,"http://order.xiaomi.com/cart/add/2134900439-0-1");
			
			System.out.println(rStr);
//			A.doGet("http://order.xiaomi.com/cart");
//			rStr=t.testGet("http://order.xiaomi.com/cart?user_id=258998042");
//			System.out.println("next:"+rStr);
			
//			System.out.println(jo.get("location"));
//			
//			result = URLEncoder.encode(rStr, "UTF-8");
			/*System.out.print(URLDecoder.decode(result,"UTF-8"));
			System.out.println(URLDecoder.decode("%D6%D0%CE%C4%B9%FA%BC%CA","GBK")); GBK编码转中文 
            System.out.println(URLEncoder.encode("中文国际","GBK"));  中文编码转GBK 
            System.out.println(URLDecoder.decode("%E4%B8%AD%E6%96%87%E5%9B%BD%E9%99%85","UTF-8")); UTF-8编码转中文 
            System.out.println(URLEncoder.encode("中文国际","UTF-8"));  UTF-8编码转中文 
*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		


		
	}
	
	public static String getUTF8XMLString(String xml) { 
	     // A StringBuffer Object 
	     StringBuffer sb = new StringBuffer(); 
	     sb.append(xml); 
	     String xmString = ""; 
	     String xmlUTF8=""; 
	     try {
	     xmString = new String(sb.toString().getBytes("UTF-8")); 
	     xmlUTF8 = URLEncoder.encode(xmString, "UTF-8"); 
	     System.out.println("utf-8 编码：" + xmlUTF8) ; 
	     } catch (UnsupportedEncodingException e) { 
	     // TODO Auto-generated catch block 
	     e.printStackTrace(); 
	     } 
	     // return to String Formed 
	     return xmlUTF8; 
	     } 

	
	public String testPost(String urlStr) throws IOException {

		/**
		 */
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		/**
		 */
		connection.setDoOutput(true);
		/**
		 */
		OutputStreamWriter out = new OutputStreamWriter(connection
				.getOutputStream(), "8859_1");
		out.write("user=31686465%40qq.com&_json=true&pwd=Configer1&sid=passport&_sign=KKkRvCpZoDC%2BgLdeyOsdMhwV0Xg%3D&callback=https%3A%2F%2Faccount.xiaomi.com&qs=%253Fsid%253Dpassport"); // post�Ĺؼ����ڣ�
		// remember to clean up
		out.flush();
		out.close();
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
	
}
