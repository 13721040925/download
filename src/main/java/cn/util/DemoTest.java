package cn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;

import cn.pojo.Linshi;

public class DemoTest {

	private int a;

	public void setVal() {
		Timer timer = new Timer();
		// 每隔一秒生成一个[1,100)内的随机整数，赋给成员a
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Random rand = new Random();
				setA(rand.nextInt(100));
				System.out.println(getA());
			}
		}, 1000, 1000);
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public static void main(String[] args) {
		HashMap<String, String> headers = MapUtil.newHashMap();
		headers.put("User-Agent",
				"Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Mobile Safari/537.36");
		String redirectUrl = HttpUtil.createGet(url).addHeaders(headers).execute().header("Location");
		Document doc = null;
		try {
			doc = Jsoup.connect(redirectUrl).headers(headers).timeout(5000).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Elements select1 = doc.select("div[id=hide-pagedata]");
		String resultJson = select1.get(0).attr("data-pagedata");
		System.out.println(resultJson);
		JSON json = JSONUtil.parse(resultJson);
		String photoId = json.getByPath("photoId").toString();

		String param = "client_key=56c3713c&photoIds=" + photoId;

		String replace = StrUtil.replace(param, "&", "")
				+ new String(new byte[] { 50, 51, 99, 97, 97, 98, 48, 48, 51, 53, 54, 99 });
		String sig = SecureUtil.md5().digestHex(replace);
		HttpResponse execute = HttpUtil.createPost("http://api.gifshow.com/rest/n/photo/info?" + param + "&sig=" + sig)
				.contentType("application/x-www-form-urlencoded").contentType("kwai-android").addHeaders(headers)
				.execute();
		String body = execute.body();
		System.out.println(body);

	}

	public static void testLinshi() {
		String menu = "";
		try {
			menu = java.net.URLEncoder.encode("", "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String urls = "http://apis.juhe.cn/cook/query.php?menu=" + menu
				+ "&dtype=json&pn=1&rn=30&key=752bf1edacbcb1e52ae7921b634e0ea6";
		String str = StringUnicode.getRequest(urls);
		String arr[] = str.split("id");
		for (String s : arr) {
			Linshi linshi = new Linshi();
			linshi.setDetails(s);
			ToSqlUtil.LinshiToSql(linshi);
		}
	}

	public static void testNews() {
		BufferedReader in = null;
		try {
			String urls = "http://v.juhe.cn/toutiao/index?type=guoji&key=9337c05768e14b283f7d786982f3ea7f";
			String news = "";
			URL realUrl = new URL(urls);
			URLConnection conn = realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(4000);
			conn.connect();// 建立实际的连接
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				news += line;
			}
			String str = StringUnicode.decodeUnicode(news);
			System.out.println(str);
			str = str.substring(str.indexOf("data") + 8, str.indexOf("error_code") - 5);
			System.out.println(str);
			// str = str.substring(8, str.length() - 5);
			// String arr[] = str.split("[}][,][{]");
			// String arr1[] = arr[0].split(",");
			// String title = arr1[1].substring(9, arr1[1].length() - 1);
			// String category = arr1[3].substring(12, arr1[3].length() - 1);
			// String author_name = arr1[4].substring(15, arr1[4].length() - 1);
			// String url = arr1[5].substring(7, arr1[5].length() - 1);
			// String thumbnail_pic_s = arr1[6].substring(19, arr1[6].length() - 1);
			// String thumbnail_pic_s02 = arr1[7].substring(21, arr1[7].length() - 1);
			// String thumbnail_pic_s03 = arr1[8].substring(21, arr1[8].length() - 1);
			// for (int i = 0; i <= arr.length - 1; i++) {
			// System.out.println(arr[i]);
			//
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭流异常");
			}
		}
	}

	public static void testDriving() {
		String urls = "http://v.juhe.cn/jztk/query?subject=4&model=&testType=rand&key=f8611399d4b2c345946175054f678040";
		String order = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(urls);
			URLConnection conn = realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(4000);
			conn.connect();// 建立实际的连接
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				order += line;
			}
			String str = StringUnicode.decodeUnicode(order);
			if (str.substring(str.indexOf("error_code") + 12, str.length() - 1).equals("10012")) {
				System.out.println("超过每日可允许请求次数!");
				return;
			}
			str = str.substring(str.indexOf("result") + 10, str.indexOf("error_code") - 4);
			String arr[] = str.split("[}][,][{]");
			String arr1[] = arr[0].split(",");
			for (String s : arr1) {
				System.out.println(s);
			}
			// for (String s : arr) {
			// JSONObject jsonObj = JSONObject.parseObject("{" + s + "}");
			// String question = jsonObj.getString("question");
			// String answer = jsonObj.getString("answer");
			// String item1 = jsonObj.getString("item1");
			// String item2 = jsonObj.getString("item2");
			// String item3 = jsonObj.getString("item3");
			// String item4 = jsonObj.getString("item4");
			// String explains = jsonObj.getString("explains");
			// String url = jsonObj.getString("url");
			// String subject = "4";
			// String model = "";
			// System.out.println(question);
			// System.out.println(answer);
			// System.out.println(item1);
			// System.out.println(item2);
			// System.out.println(item3);
			// System.out.println(item4);
			// System.out.println(explains);
			// System.out.println(url);
			// System.out.println(subject);
			// System.out.println(model);
			// System.out.println("");
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭流异常");
			}
		}
	}

	public static void testArticles() {
		String urls = "http://v.juhe.cn/weixin/query?pno=1&ps=50&dtype=json&key=bfb02a241c6c05cb496a9c6752d3123c";
		String articles = "";
		BufferedReader in = null;

		try {
			URL realUrl = new URL(urls);
			URLConnection conn = realUrl.openConnection();// 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*");// 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setConnectTimeout(4000);
			conn.connect();// 建立实际的连接
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				articles += line;
			}
			String str = StringUnicode.decodeUnicode(articles);
			if (!str.substring(str.indexOf("error_code") + 12, str.length() - 1).equals("0")) {
				System.out.println("超过每日可允许请求次数!");
				return;
			}
			str = str.substring(str.indexOf("list") + 8, str.indexOf("totalPage") - 4);
			String arr[] = str.split("[}][,][{]");
			String arr1[] = arr[0].split(",");
			String title = arr1[1].substring(arr1[1].indexOf(":") + 2, arr1[1].length() - 1);
			String source = arr1[2].substring(arr1[2].indexOf(":") + 2, arr1[2].length() - 1);
			String firstImg = arr1[3].substring(arr1[3].indexOf(":") + 2, arr1[3].length() - 1);
			String url = arr1[5].substring(arr1[5].indexOf(":") + 2, arr1[5].length() - 1);
			System.out.println(title);
			System.out.println(source);
			System.out.println(firstImg);
			System.out.println(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				System.out.println("关闭流异常");
			}
		}
	}

}
