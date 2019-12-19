package cn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.pojo.Driving;
import cn.pojo.Heatline;
import cn.pojo.Movie;
import cn.pojo.Wechatarticle;
import cn.pojo.Zongyi;
import cn.service.DrivingService;
import cn.service.HeatlineService;
import cn.service.MovieService;
import cn.service.WechatarticleService;
import cn.service.ZongyiService;
import cn.util.CreateQRCode;
import cn.util.StringUnicode;
import cn.util.ToSqlUtil;
@Controller
@RequestMapping("/ice")
public class MainController {
	@Resource
	private MovieService movieService;
	@Resource
	private ZongyiService zongyiService;
	@Resource
	private DrivingService drivingService;
	@Resource
	private HeatlineService heatlineService;
	@Resource
	private WechatarticleService wechatarticleService;

	static {
		// setVal();
		// WechatArticleToSql();
	}

	public static void setVal() {
		Timer timer = new Timer();
		// 每隔一秒生成一个[1,100)内的随机整数，赋给成员a
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// getNewsToSql();
				// drivingTestToSql();
			}
		}, 1000);
	}

	public static void WechatArticleToSql() {
		try {
			for (int i = 186; i <= 200; i++) {
				String urls = "http://v.juhe.cn/weixin/query?pno=" + i
						+ "&ps=50&dtype=json&key=bfb02a241c6c05cb496a9c6752d3123c";
				String wechat = StringUnicode.getRequest(urls);
				if (!wechat.substring(wechat.indexOf("error_code") + 12, wechat.length() - 1).equals("0")) {
					System.out.println("超过每日可允许请求次数!");
					return;
				}
				wechat = wechat.substring(wechat.indexOf("list") + 8, wechat.indexOf("totalPage") - 4);
				String arr[] = wechat.split("[}][,][{]");
				for (int j = 0; j <= arr.length - 1; j++) {
					String arr1[] = arr[j].split(",");
					String title = arr1[1].substring(arr1[1].indexOf(":") + 2, arr1[1].length() - 1);
					String source = arr1[2].substring(arr1[2].indexOf(":") + 2, arr1[2].length() - 1);
					String firstImg = arr1[3].substring(arr1[3].indexOf(":") + 2, arr1[3].length() - 1);
					String url = arr1[5].substring(arr1[5].indexOf(":") + 2, arr1[5].length() - 1);
					Wechatarticle wechatarticle = new Wechatarticle();
					wechatarticle.setTitle(title);
					wechatarticle.setSource(source);
					wechatarticle.setFirstImg(firstImg);
					wechatarticle.setUrl(url);
					ToSqlUtil.AtarticleToSql(wechatarticle);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void drivingTestToSql() {
		String urls = "http://v.juhe.cn/jztk/query?subject=4&model=&testType=rand&key=f8611399d4b2c345946175054f678040";
		String models[] = { "a1", "a2", "b1", "b2", "c1", "c2", "a1", "a2", "b1" };
		try {
			for (int i = 0; i <= 1; i++) {
				if (i == 0) {
					// 科目1
					for (int j = 0; j <= models.length - 1; j++) {
						urls = "http://v.juhe.cn/jztk/query?subject=1&model=" + models[j]
								+ "&testType=rand&key=f8611399d4b2c345946175054f678040";
						String order = StringUnicode.getRequest(urls);
						if (order.substring(order.indexOf("error_code") + 12, order.length() - 1).equals("10012")) {
							System.out.println("超过每日可允许请求次数!");
							return;
						}
						order = order.substring(order.indexOf("result") + 10, order.indexOf("error_code") - 4);
						String arr[] = order.split("[}][,][{]");
						for (String s : arr) {
							JSONObject jsonObj = JSONObject.parseObject("{" + s + "}");
							String question = jsonObj.getString("question");
							String answer = jsonObj.getString("answer");
							String item1 = (jsonObj.getString("item1") == null || jsonObj.getString("item1").equals(""))
									? "正确"
									: jsonObj.getString("item1");
							String item2 = (jsonObj.getString("item2") == null || jsonObj.getString("item2").equals(""))
									? "错误"
									: jsonObj.getString("item2");
							String item3 = jsonObj.getString("item3");
							String item4 = jsonObj.getString("item4");
							String explains = jsonObj.getString("explains");
							String url = jsonObj.getString("url");
							String subject = "1";
							String model = models[j];
							Driving driving = new Driving();
							driving.setQuestion(question);
							driving.setAnswer(answer);
							driving.setItem1(item1);
							driving.setItem2(item2);
							driving.setItem3(item3);
							driving.setItem4(item4);
							driving.setExplains(explains);
							driving.setUrl(url);
							driving.setSubject(subject);
							driving.setModel(model);
							ToSqlUtil.DrivingToSql(driving);
						}
					}
				} else {
					// 科目4
					urls = "http://v.juhe.cn/jztk/query?subject=4&model=&testType=rand&key=f8611399d4b2c345946175054f678040";
					String order = StringUnicode.getRequest(urls);
					if (order.substring(order.indexOf("error_code") + 12, order.length() - 1).equals("10012")) {
						System.out.println("超过每日可允许请求次数!");
						return;
					}
					order = order.substring(order.indexOf("result") + 10, order.indexOf("error_code") - 4);
					String arr[] = order.split("[}][,][{]");
					for (String s : arr) {
						JSONObject jsonObj = JSONObject.parseObject("{" + s + "}");
						String question = jsonObj.getString("question");
						String answer = jsonObj.getString("answer");
						String item1 = (jsonObj.getString("item1") == null || jsonObj.getString("item1").equals(""))
								? "正确"
								: jsonObj.getString("item1");
						String item2 = (jsonObj.getString("item2") == null || jsonObj.getString("item2").equals(""))
								? "错误"
								: jsonObj.getString("item2");
						String item3 = jsonObj.getString("item3");
						String item4 = jsonObj.getString("item4");
						String explains = jsonObj.getString("explains");
						String url = jsonObj.getString("url");
						String subject = "4";
						String model = "4";
						Driving driving = new Driving();
						driving.setQuestion(question);
						driving.setAnswer(answer);
						driving.setItem1(item1);
						driving.setItem2(item2);
						driving.setItem3(item3);
						driving.setItem4(item4);
						driving.setExplains(explains);
						driving.setUrl(url);
						driving.setSubject(subject);
						driving.setModel(model);
						ToSqlUtil.DrivingToSql(driving);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getNewsToSql() {
		try {
			String[] types = { "top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing",
					"shishang" };
			for (int i = 0; i <= types.length - 1; i++) {
				String urls = "http://v.juhe.cn/toutiao/index?type=" + types[i]
						+ "&key=9337c05768e14b283f7d786982f3ea7f";
				String news = StringUnicode.getRequest(urls);
				if (news.substring(news.indexOf("error_code") + 12, news.length() - 1).equals("10012")) {
					System.out.println("超过每日可允许请求次数!");
					return;
				}
				news = news.substring(news.indexOf("data") + 8, news.indexOf("error_code") - 5);
				String arr[] = news.split("[}][,][{]");
				for (int j = 0; j <= arr.length - 1; j++) {
					String arr1[] = arr[j].split(",");
					if (arr1.length < 7) {
						continue;
					}
					if (!arr1[1].contains("title") || !arr1[3].contains("title") || !arr1[4].contains("title")
							|| !arr1[5].contains("title") || !arr1[6].contains("title")) {
						continue;
					} else if (arr1.length >= 8) {
						if (!arr1[7].contains("thumbnail_pic_s02")) {
							continue;
						} else if (arr1.length >= 9) {
							if (!arr1[8].contains("thumbnail_pic_s03")) {
								continue;
							}
						}
					}
					String title = arr1[1].substring(9, arr1[1].length() - 1);
					String category = arr1[3].substring(12, arr1[3].length() - 1);
					String author_name = arr1[4].substring(15, arr1[4].length() - 1);
					String url = arr1[5].substring(7, arr1[5].length() - 1);
					String thumbnail_pic_s = arr1[6].substring(19, arr1[6].length() - 1);
					String thumbnail_pic_s02 = (arr1.length >= 8) ? arr1[7].substring(21, arr1[7].length() - 1) : "";
					String thumbnail_pic_s03 = (arr1.length >= 9) ? arr1[8].substring(21, arr1[8].length() - 1) : "";
					Heatline heatline = new Heatline();
					heatline.setTitle(title);
					heatline.setCategory(category);
					heatline.setAuthor_name(author_name);
					heatline.setUrl(url);
					heatline.setThumbnail_pic_s(thumbnail_pic_s);
					heatline.setThumbnail_pic_s02(thumbnail_pic_s02);
					heatline.setThumbnail_pic_s03(thumbnail_pic_s03);
					ToSqlUtil.HeatToSql(heatline);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// @CrossOrigin
	// @RequestMapping(value = "/qqmusic", produces =
	// "application/json;charset=utf-8")
	// @ResponseBody
	// public String qqmusic(HttpServletRequest request, HttpServletResponse
	// response, String urls) {
	// try {
	// String finalUrl = "http://www.douqq.com/qqmusic/qqapi.php?mid=" + urls;
	// String htmls =
	// Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();
	// String st1 = htmls.split("lrc")[1];
	// String st2 = st1.substring(5, st1.length() - 4);
	// String[] arr = st2.split("\\\\");
	// StringBuilder sb = new StringBuilder();
	// sb.append(arr[0].trim());
	// for (int i = 0; i <= arr.length - 1; i++) {
	// if (i == 0) {
	// continue;
	// }
	// if (arr[i].length() <= 0) {
	// continue;
	// }
	// sb.append((char) 92).append(arr[i]);
	// }
	// sb.append((char) 92);
	// String str4 = sb.toString();
	// StringBuilder sb1 = new StringBuilder();
	// int j = -10;
	// for (int i = 0; i < str4.length() - 1; i++) {
	// if (str4.charAt(i) == 92) {
	// if (str4.charAt(i + 1) == 'u') {
	// j = i;
	// String s = str4.substring(i, i + 6);
	// sb1.append(StringUnicodeTest.unicodeToString(s));
	// }
	// if (str4.charAt(i + 1) == '/') {
	// continue;
	// }
	// }
	// if (i < j + 6) {
	// continue;
	// }
	// sb1.append(str4.charAt(i));
	// }
	//
	// String lrc = sb1.toString();
	// // j = -10;
	// // StringBuilder sbs = new StringBuilder();
	// // for (int i = 0; i < lrc.length() - 1; i++) {
	// // if (lrc.charAt(i) == 92) {
	// // if (lrc.charAt(i + 1) == 'n') {
	// // j = i;
	// // sbs.append("<br/>");
	// // }
	// //
	// // }
	// // if (i < j + 2) {
	// // continue;
	// // }
	// // sbs.append(lrc.charAt(i));
	// // }
	// // lrc = sbs.toString();// 歌词
	// String[] arr2 = htmls.split(",");
	// String str5 = arr2[1];
	// String str6 = str5.substring(10, str5.length() - 2);
	// StringBuilder sb2 = new StringBuilder();
	// for (int i = 0; i <= str6.length() - 1; i++) {
	// if (str6.charAt(i) == 92) {
	// continue;
	// }
	// sb2.append(str6.charAt(i));
	// }
	// sb2.append('/');
	// String m4a = sb2.toString();// 歌曲
	// String str7 = arr2[9];
	// String str8 = str7.substring(10, str7.length() - 2);
	// StringBuilder sb3 = new StringBuilder();
	// for (int i = 0; i <= str8.length() - 1; i++) {
	// if (str8.charAt(i) == 92) {
	// continue;
	// }
	// sb3.append(str8.charAt(i));
	// }
	// sb3.append('/');
	// String pic = sb3.toString();// 图片
	// String str9 = arr2[6];
	// String str10 = str9.substring(15, str9.length() - 2);
	// StringBuilder sb4 = new StringBuilder();
	// for (int i = 0; i <= str10.length() - 1; i++) {
	// if (str10.charAt(i) == 92 && str10.charAt(i + 1) == 92) {
	// continue;
	// }
	// sb4.append(str10.charAt(i));
	// }
	// String songname = StringUnicodeTest.unicodeToString(sb4.toString());// 歌名
	// String str11 = arr2[8];
	// String str12 = str11.substring(17, str11.length() - 2);
	// StringBuilder sb5 = new StringBuilder();
	// j = -10;
	// for (int i = 0; i < str12.length() - 1; i++) {
	// if (str12.charAt(i) == 92) {
	// if (str12.charAt(i + 1) == 'u') {
	// j = i;
	// String s = str12.substring(i, i + 6);
	// sb5.append(StringUnicodeTest.unicodeToString(s));
	// }
	// }
	// if (i < j + 6) {
	// continue;
	// }
	// sb5.append(str12.charAt(i));
	// }
	// String str13 = sb5.toString();
	// StringBuilder sb6 = new StringBuilder();
	// for (int i = 0; i <= str13.length() - 1; i++) {
	// if (str13.charAt(i) == 92) {
	// continue;
	// }
	// sb6.append(str13.charAt(i));
	// }
	// String singername = sb6.toString();// 歌手
	// long timetmp = new Date().getTime();
	// String fileAddress =
	// request.getSession().getServletContext().getRealPath("/") + "music/" +
	// timetmp
	// + ".mp3";
	// URL url = new URL(m4a);
	// URLConnection connection = url.openConnection();
	// InputStream inputStream = connection.getInputStream();
	// StringUnicodeTest.download(fileAddress, inputStream);
	// Music music = new Music();
	// music.setLrc(lrc);
	// music.setM4a(m4a);
	// music.setPic(pic);
	// music.setSingername(singername);
	// music.setSongname(songname);
	// music.setMp3(timetmp + ".mp3");
	// return JSON.toJSONString(music);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return JSON.toJSONString(null);
	// }

	public static String decodeHttpUrl(String url) {
		int start = url.indexOf("http");
		int end = url.lastIndexOf("/");
		String decodeurl = url.substring(start, end);
		return decodeurl;
	}

	@CrossOrigin
	@RequestMapping(value = "/magic", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String magic(HttpServletRequest request, HttpServletResponse response, String content, String type) {
		Long timetmp = null;
		try {
			// 过滤链接，获取http连接地址
			System.out.println(">>>>>>>>>>>>>>" + content);
			String finalUrl = decodeHttpUrl(content);
			System.out.println(finalUrl);

			// 1.利用Jsoup抓取抖音链接#在抖音，记录美好生活#你们是这样的么
			// 抓取抖音网页
			String htmls = Jsoup.connect(finalUrl).ignoreContentType(true).execute().body();
			System.out.println(htmls); // 做测试时使用
			String matchUrl = "";// 图片
			String matchUrl1 = "";// 视频
			switch (type) {
			case "1": {
				System.out.println(1);
				Pattern patternCompile = Pattern.compile("(?<=cover: \")https?://.+(.\")");
				Pattern patternCompile1 = Pattern.compile("(?<=playAddr: \")https?://.+(?=\",)");
				Matcher m = patternCompile.matcher(htmls);
				Matcher m1 = patternCompile1.matcher(htmls);
				while (m.find()) {
					matchUrl = m.group(0);
				}
				matchUrl = matchUrl.substring(0, matchUrl.length() - 1);
				while (m1.find()) {
					matchUrl1 = m1.group(0).replaceAll("playwm", "play");
				}
				break;
			}
			case "2": {
				System.out.println(2);
				Pattern patternCompile = Pattern.compile("(?<=poster\":\")https?:.+(u002F)");// 图片
				Pattern patternCompile1 = Pattern.compile("(?<=playUrl\":\")https?:.+(u002F)");// 视频
				Matcher m = patternCompile.matcher(htmls);
				Matcher m1 = patternCompile1.matcher(htmls);
				while (m.find()) {
					matchUrl = m.group(0);
				}
				matchUrl = matchUrl.split("\",\"")[0].replaceAll("u002F", "/");
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < matchUrl.length(); i++) {
					if ((matchUrl.charAt(i)) == 92) {
						continue;
					}
					sb.append(matchUrl.charAt(i));
				}
				matchUrl = sb.toString();

				while (m1.find()) {
					matchUrl1 = m1.group(0);
				}
				matchUrl1 = matchUrl1.split("\",\"")[0].replaceAll("u002F", "/");
				sb = new StringBuilder();
				for (int i = 0; i < matchUrl1.length(); i++) {
					if ((matchUrl1.charAt(i)) == 92) {
						continue;
					}
					sb.append(matchUrl1.charAt(i));
				}
				matchUrl1 = sb.toString();
				break;
			}
			case "3": {
				System.out.println(3);
				String[] arr = htmls.split("url_list");

				matchUrl = arr[8].split("\",\"")[1];
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < matchUrl.length(); i++) {
					if ((matchUrl.charAt(i)) == 92) {
						continue;
					}

					sb.append(matchUrl.charAt(i));
				}
				matchUrl = sb.toString();

				matchUrl1 = arr[9].split("\",\"")[0].substring(4).replaceAll("app_id=0", "app_id=1").replaceAll("u0026",
						"&");
				sb = new StringBuilder();
				for (int i = 0; i < matchUrl1.length(); i++) {
					if ((matchUrl1.charAt(i)) == 92) {
						continue;
					}

					sb.append(matchUrl1.charAt(i));
				}
				matchUrl1 = sb.toString();
				break;
			}
			}
			System.out.println("matchUrl=" + matchUrl);
			System.out.println("matchUrl1=" + matchUrl1);
			// 将链接封装成流
			Map<String, String> headers = new HashMap<>();
			headers.put("Connection", "keep-alive");
			headers.put("Host", "aweme.snssdk.com");
			headers.put("User-Agent",
					"Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");

			// 6.利用Joup获取视频对象,并作封装成一个输入流对象
			BufferedInputStream in = Jsoup.connect(matchUrl).headers(headers).timeout(10000).ignoreContentType(true)
					.execute().bodyStream();
			BufferedInputStream in1 = Jsoup.connect(matchUrl1).headers(headers).timeout(10000).ignoreContentType(true)
					.execute().bodyStream();
			timetmp = new Date().getTime();
			String fileAddress = request.getSession().getServletContext().getRealPath("/") + "img/" + timetmp + ".jpg";
			String fileAddress1 = request.getSession().getServletContext().getRealPath("/") + "video/" + timetmp
					+ ".mp4";

			// 7.封装一个保存文件的路径对象
			File fileSavePath = new File(fileAddress);
			File fileSavePath1 = new File(fileAddress1);
			// 注:如果保存文件夹不存在,那么则创建该文件夹
			File fileParent = fileSavePath.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
			File fileParent1 = fileSavePath1.getParentFile();
			if (!fileParent1.exists()) {
				fileParent1.mkdirs();
			}
			// 8.新建一个输出流对象
			OutputStream out = new BufferedOutputStream(new FileOutputStream(fileSavePath));
			OutputStream out1 = new BufferedOutputStream(new FileOutputStream(fileSavePath1));
			// 9.遍历输出文件
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}

			out.close();// 关闭输出流
			in.close(); // 关闭输入流

			int b1;
			while ((b1 = in1.read()) != -1) {
				out1.write(b1);
			}
			out1.close();// 关闭输出流
			in1.close(); // 关闭输入流
			// 注:打印获取的链接
			System.out.println("-----去水印图片链接-----\n" + matchUrl);
			System.out.println("\n-----视频图片保存路径-----\n" + fileSavePath.getAbsolutePath());
			System.out.println("-----去水印链接-----\n" + matchUrl1);
			System.out.println("\n-----视频保存路径-----\n" + fileSavePath1.getAbsolutePath());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON.toJSONString(timetmp);
	}

	@CrossOrigin
	@RequestMapping(value = "/getMovieList", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getMovieList(@RequestParam(name = "type") Integer type) {
		List<Movie> movieList = movieService.selMovieByType(type);
		return JSON.toJSONString(movieList);
	}

	@CrossOrigin
	@RequestMapping(value = "/getZongyiList", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getZongyiList(@RequestParam(name = "typenum") Integer typenum) {
		List<Zongyi> zongyiList = zongyiService.selZongyis(typenum);
		System.out.println(zongyiList);
		return JSON.toJSONString(zongyiList);
	}

	@CrossOrigin
	@RequestMapping(value = "/getMovieDetails", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getMovieDetails(@RequestParam(name = "id") Integer id) {
		Movie movie = movieService.getMovieById(id);
		return JSON.toJSONString(movie);
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/moviedetails")
	public ModelAndView moviedetails(ModelAndView md, @RequestParam(name = "id") Integer id) {
		Movie movie = movieService.getMovieById(id);
		md.addObject("movie", movie);
		md.setViewName("movieDetails");
		return md;
	}

	@RequestMapping("/playvideo")
	public ModelAndView playvideo(ModelAndView md, @RequestParam(name = "id") Integer id) {
		Movie movie = movieService.getMovieById(id);
		md.addObject("movie", movie);
		md.setViewName("playVideo");
		return md;
	}
	@RequestMapping("/video")
	public ModelAndView video(ModelAndView md, @RequestParam(name = "type") String type) {
		String typename = "";
		switch (type) {
		case "1": {
			typename = "抖音";
			break;
		}
		case "2": {
			typename = "快手";
			break;
		}
		case "3": {
			typename = "火山";
			break;
		}
		}
		md.addObject("type", type);
		md.addObject("typename", typename);
		md.setViewName("video");
		return md;
	}

	@RequestMapping("/teach")
	public ModelAndView teach(ModelAndView md, @RequestParam(name = "type") String type) {
		String typename = "";
		switch (type) {
		case "1": {
			typename = "抖音";
			break;
		}
		case "2": {
			typename = "快手";
			break;
		}
		case "3": {
			typename = "火山";
			break;
		}
		}
		md.addObject("type", type);
		md.addObject("typename", typename);
		md.setViewName("teach");
		return md;
	}

	// 生成二维码功能，返回生成的二维码图片地址
	// 参考资料：https://blog.csdn.net/weixin_33755554/article/details/86215443
	// 解决 maven项目启动 提示 class not find
	@RequestMapping("/createQRCode")
	@ResponseBody
	public String createQRCode(HttpServletRequest request, HttpServletResponse response, String qrData) {
		try {
			System.out.println(qrData);
			long timetmp = new Date().getTime();
			String path = request.getSession().getServletContext().getRealPath("/") + "QRcode\\" + timetmp + ".png";
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <= path.length() - 1; i++) {
				if (path.charAt(i) == 92) {
					sb.append('/');
				} else {
					sb.append(path.charAt(i));
				}
			}
			path = sb.toString();
		
			if (CreateQRCode.create(path, qrData)) {
				return JSON.toJSONString(timetmp + ".png");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 向指定的URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式
	 * @return 远程资源的响应结果
	 */
	public static String sendPost() {
		String url = "http://open.iciba.com/dsapi";
		// String param="";
		String result = "";
		BufferedReader bufferedReader = null;
		PrintWriter out = null;
		try {
			// 1、2、读取并将url转变为URL类对象
			URL realUrl = new URL(url);

			// 3、打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 4、设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 发送POST请求必须设置如下两行
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// 5、建立实际的连接
			// connection.connect();
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送请求参数
			// out.print(param);
			// flush输出流的缓冲
			out.flush();
			//

			// 6、定义BufferedReader输入流来读取URL的响应内容
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while (null != (line = bufferedReader.readLine())) {
				result += line;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("发送POST请求出现异常！！！" + e);
			e.printStackTrace();
		} finally { // 使用finally块来关闭输出流、输入流
			try {
				if (null != out) {
					out.close();
				}
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return result;
	}

	@RequestMapping("/ajaxProxy")
	@ResponseBody
	public String ajaxProxy(String url, HttpServletRequest req) {
		JSONObject node = new JSONObject();
		// Integer param1, String param2,
		// try {
		// node.put("param1", param1);
		// node.put("param2", param2);
		// } catch (JSONException e1) {
		// e1.printStackTrace();
		// }

		// 使用POST方式向目的服务器发送请求
		URL connect;
		StringBuffer data = new StringBuffer();
		try {
			connect = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			paramout.write(node.toString());
			paramout.flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
			paramout.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}

	public static String PostRequest(String URL) {
		String obj = "subject=1&model=c1&key=f8611399d4b2c345946175054f678040appKey&testType=rand";
		// http://v.juhe.cn/jztk/query?
		String jsonString = "";
		try {
			// 创建连接
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST"); // 设置请求方法
			connection.setRequestProperty("Charsert", "UTF-8"); // 设置请求编码
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream()); // 关键的一步

			out.writeBytes(obj);
			out.flush();
			out.close();

			// 读取响应
			if (connection.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String lines;
				StringBuffer sb = new StringBuffer("");
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
				}
				jsonString = sb.toString();
				reader.close();
			} // 返回值为200输出正确的响应信息

			if (connection.getResponseCode() == 400) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				String lines;
				StringBuffer sb = new StringBuffer("");
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
				}
				jsonString = sb.toString();
				reader.close();
			} // 返回值错误，输出错误的返回信息
				// 断开连接
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

	public static String reEncoding(String text) {
		String str = null;
		try {
			str = new String(text.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	@CrossOrigin
	@RequestMapping(value = "/drivingTest", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String drivingTest(String model) {
		try {
			List<Driving> tests = drivingService.getDrivingQuestions(model);
			return JSON.toJSONString(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/getWechats", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getWechats() {
		try {
			List<Wechatarticle> articles = wechatarticleService.getWechatNews();
			return JSON.toJSONString(articles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/getNews", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getNews(String type) {
		try {
			String[] types = { "top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing",
					"shishang" };
			String[] categorys = { "头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚" };
			int index = 0;
			for (int i = 0; i <= types.length - 1; i++) {
				if (types[i].equals(type)) {
					index = i;
					break;
				}
			}
			List<Heatline> heatNews = heatlineService.getNewsByType(categorys[index]);
			return JSON.toJSONString(heatNews);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@CrossOrigin
	@RequestMapping(value = "/getWeather", produces = "application/json;charset=utf-8")
	@ResponseBody
	public static String getWeather(String city) {
		String urlTypeName = "";
		try {
			urlTypeName = java.net.URLEncoder.encode(city, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String url = "http://apis.juhe.cn/simpleWeather/query?city=" + urlTypeName
					+ "&key=70eb92d5cb94430efa1ad0e2e02beb7f";
			String weather = StringUnicode.getRequest(url);
			JSONObject jsonObj = JSONObject.parseObject(weather);
			return JSON.toJSONString(jsonObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
